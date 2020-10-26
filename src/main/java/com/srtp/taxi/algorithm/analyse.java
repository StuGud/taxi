package com.srtp.taxi.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.srtp.taxi.entity.*;
import com.srtp.taxi.service.*;
import com.srtp.taxi.utils.RoadDetailUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("DispatchAlg")
public class analyse {
    final
    DispatchService dispatchService;
    final
    ReservationService reservationService;
    final
    DriverService driverService;

    public analyse(DispatchService dispatchService, ReservationService reservationService, DriverService driverService) {
        this.dispatchService = dispatchService;
        this.reservationService = reservationService;
        this.driverService = driverService;
    }

    @Scheduled(fixedRate = 8*60*60*1000)
    public void execute1() throws InterruptedException {
        int time = (int) (new Date().getTime()/1000);//秒
        execute(time);
    }

    public void execute2() throws ParseException, InterruptedException {
        String time = "20-10-15 08:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd H:m:s");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = format.parse(time);
        execute((int) (date.getTime()/1000));
    }

    public void execute(int time) throws InterruptedException {


        ArrayList<double []> timeList=new ArrayList<>();
        ArrayList<double []> disList=new ArrayList<>();
        var sList=reservationService.listAll();
        int size=sList.size();
        double slat=0.0;
        double slng=0.0;
        double [] elat=new double[size];
        double [] elng=new double[size];
        for (Reservation reservation : sList) {
            slng = reservation.getStart_lng();
            slat = reservation.getStart_lat();
            for (int i = 0; i < size; i++) {
                elng[i] = sList.get(i).getEnd_lng();
                elat[i] = sList.get(i).getEnd_lat();
            }
            disList.addAll(RoadDetailUtils.getDistance(slng, slat, elng, elat));
            timeList.addAll(RoadDetailUtils.getTime(slng, slat, elng, elat));
        }
            double pos1=110.3645;
            double pos2=20.0353;
            for (int i = 0; i<size; i++) {
                elng[i]=sList.get(i).getEnd_lng();
                elat[i]=sList.get(i).getEnd_lat();
            }
            disList.addAll(RoadDetailUtils.getDistance(pos1,pos2,elng,elat));
            timeList.addAll(RoadDetailUtils.getTime(pos1,pos2,elng,elat));

            for (var i = 0; i<size; i++) {
                elng[i]=sList.get(i).getStart_lng();
                elat[i]=sList.get(i).getStart_lat();
            }
            disList.addAll(RoadDetailUtils.getDistance(pos1,pos2,elng,elat));
            timeList.addAll(RoadDetailUtils.getTime(pos1,pos2,elng,elat));



        HashMap<Long,Integer> ordernumMap=new HashMap<>();
        ArrayList<ReservationA> unselectorder = new ArrayList<>();
        ArrayList<driverSelect> alldriver = new ArrayList<>();
//        ArrayList<driverSelect> tempdriver = new ArrayList<>();
        Comparator<driverSelect> driverSelectComparator=new Comparator<driverSelect>() {
            @Override
            public int compare(driverSelect o1, driverSelect o2) {
                return o1.getNexttime()-o2.getNexttime();
            }
        };
        Queue<driverSelect> driverSelectQueue=new PriorityQueue<>(driverSelectComparator);
        //访问数据库初始化以上变量
        List<Reservation> listofReservation=reservationService.listAllNotDispatchedInEightHours();
        List<OnlineDriver> listofDriver=driverService.listAllOnline();

        for(Reservation r : listofReservation){
            unselectorder.add(new ReservationA(r));
            ordernumMap.put(r.getId(),r.getNum());
        }
        for(OnlineDriver d : listofDriver){
            Driver dd=driverService.findDriverById(d.getId());
            alldriver.add(new driverSelect(disList,timeList,dd,time,new Position(d.getLng(),d.getLat())));
        }
        driverSelectQueue.addAll(alldriver);

        while(!unselectorder.isEmpty()) {
            //下一个到达的driver
            driverSelect driver=driverSelectQueue.poll();
            assert driver != null;
            if(driver.getNexttime()>time){
                time = driver.getNexttime();
            }
            else {
                time+=5*60;
            }
            driver.select(unselectorder,ordernumMap,time);
            driverSelectQueue.add(driver);
            System.out.println(time);// for debug only
            System.out.println(driver.qureytime+"/"+driver.totaltime);// for debug only
        }
        //向数据库中写入所有司机的所有（按顺序）的乘客ID，路线（节点位置）信息
        for(driverSelect ds:alldriver) {
            Dispatch dispatch=new Dispatch();
            dispatch.setDriverId(ds.getDriver().getId());
            List<ReservationDispatched> reservationList=new ArrayList<ReservationDispatched>();
            ArrayList<Long> route=ds.getRoutefordb();
            route.addAll(ds.getTemproutefordb());

            for(Long id:route){
                ReservationDispatched reservationDispatched=new ReservationDispatched();
                reservationDispatched.setId(id);
                reservationList.add(reservationDispatched);
            }

            dispatch.setReservationList(reservationList);
            dispatchService.saveDispatch(dispatch);
        }
       }

}
