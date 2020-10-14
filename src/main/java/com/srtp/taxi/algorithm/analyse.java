package com.srtp.taxi.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.srtp.taxi.entity.*;
import com.srtp.taxi.service.*;
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
    public void execute1(){
        int time = (int) (new Date().getTime()/1000);//秒
        execute(time);
    }

    public void execute2() throws ParseException {
        String time = "17-10-14 23:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd H:m:s");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = format.parse(time);
        execute((int) (date.getTime()/1000));
    }

    public void execute(int time){
        HashMap<Long,Integer> ordernumMap=new HashMap<Long,Integer>();
        ArrayList<ReservationA> unselectorder = new ArrayList<ReservationA>();
        ArrayList<driverSelect> alldriver = new ArrayList<>();
        ArrayList<driverSelect> tempdriver = new ArrayList<driverSelect>();
        //访问数据库初始化以上变量
        List<Reservation> listofReservation=reservationService.listAllNotDispatchedInEightHours();
        List<Driver> listofDriver=driverService.listAll();

        for(Reservation r : listofReservation){
            unselectorder.add(new ReservationA(r));
            ordernumMap.put(r.getId(),r.getNum());
        }
        for(Driver d : listofDriver){
            alldriver.add(new driverSelect(d));
        }

        while(!unselectorder.isEmpty()) {
            //筛选到达且不满载的车辆
            tempdriver.clear();
            for (driverSelect value : alldriver) {
                if (value.getNexttime() == time)
                    tempdriver.add(value);
            }
            //对每个符合条件的车辆规划
            for (driverSelect driver : tempdriver) {
                driver.select(unselectorder,ordernumMap,time);
            }
            System.out.println(time);
            time+=60;
        }
        //向数据库中写入所有司机的所有（按顺序）的乘客ID，路线（节点位置）信息
        for(driverSelect ds:alldriver) {
            Dispatch dispatch=new Dispatch();
            dispatch.setDriverId(ds.getDriver().getId());
            List<ReservationDispatched> reservationList=new ArrayList<ReservationDispatched>();
            for(Long id:ds.getRoutefordb()){
                ReservationDispatched reservationDispatched=new ReservationDispatched();
                reservationDispatched.setId(id);
                reservationList.add(reservationDispatched);
            }

            dispatch.setReservationList(reservationList);
            dispatchService.saveDispatch(dispatch);
        }
    }
}
