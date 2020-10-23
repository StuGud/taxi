package com.srtp.taxi.algorithm;
import com.srtp.taxi.entity.*;
import com.srtp.taxi.utils.RoadDetailUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class driverSelect {
    private Driver driver;
    //存放已确定路线，position类型的列表表示
    private ArrayList<Position> route = new ArrayList<Position>();
    //存放车上乘客的终点作为待安排路线，position类型的列表表示
    private ArrayList<Position> temproute = new ArrayList<Position>();
    //存放已确定路线，string类型列表存订单编号，第一次出现为订单的上车地点，第二次出现为订单的下车地点，数据库专用
    private ArrayList<Long> routefordb = new ArrayList<Long>();
    //存放车上乘客的终点作为待安排路线，string类型列表存订单编号，数据库专用
    private ArrayList<Long> temproutefordb = new ArrayList<Long>();
    //最大载客
    private final int maxnum=4;
    //目前载客
    private int nownum=0;
    //预计到达下一个节点的时间
    private int nexttime;
    //车辆当前位置
    private Position pos=new Position(118.820984405,31.887238809);// 根据海口需要初始化经纬度
    private ArrayList<Long> orderIDinSequence = new ArrayList<Long>();

    public driverSelect(Driver driver,int time,Position p) {
        this.driver = driver;
        this.nexttime=time;
        this.pos=p;
    }

    public Driver getDriver() {
        return driver;
    }

    public ArrayList<Long> getTemproutefordb() {
        return temproutefordb;
    }

    public ArrayList<Long> getRoutefordb() {
        return routefordb;
    }

    public int getNexttime() {
        return nexttime;
    }
    boolean isfull() { return maxnum <= nownum; }
    
    public static double getdistance(Position p1, Position p2){
        int dist;
        while(true){
            try{
                dist=RoadDetailUtils.getDistance(p1.getX(),p1.getY(),p2.getX(),p2.getY());
                break;
            }catch (Exception e){
                System.out.print(e.getMessage()+"\n");
            }
        }
        return dist;
    }
    public static int gettime(Position p1, Position p2){
        int time;
        while(true){
            try {
                time=RoadDetailUtils.getTime(p1.getX(),p1.getY(),p2.getX(),p2.getY());
                break;
            }catch (Exception e){System.out.print(e.getMessage()+"\n");}
        }

        return time;
    }
    //对于每辆车而言的选择算法，第一个参数是未分配的订单，后一个参数是当前时间
    void select(ArrayList<ReservationA> unselectorder, HashMap<Long,Integer> ordernumMap, int time) {
        //如果当前车上没有乘客
        if(nownum == 0) {
            double distance = 100000;
            ReservationA selectorder = null;
            for (ReservationA ReservationA : unselectorder) {
                //选择最近乘客且时间符合的乘客，修改相关量，写回数据库，删除该乘客，若无不改；返回
                double newdistance = getdistance(pos, ReservationA.getPos1());
                int newtime=gettime(pos, ReservationA.getPos1());
                if (newdistance < distance && ((time + newtime) > (ReservationA.getTime() - 10*60))) {
                    distance = newdistance;
                    selectorder = ReservationA;
                }
            }
            //如果选到了订单，更新数据
            if(selectorder != null)
            {
                nownum = nownum + selectorder.getPassagerNum();
                nexttime += gettime(pos,selectorder.getPos1());
                pos = selectorder.getPos1();
                orderIDinSequence.add(selectorder.getOrderID());
                selectorder.setCarID(driver.getCarId());
                temproute.add(selectorder.getPos2());
                temproutefordb.add(selectorder.getOrderID());
                route.add(selectorder.getPos1());
                routefordb.add(selectorder.getOrderID());


                //从unselectorder中删除
                unselectorder.remove(selectorder);

            }
        }
        //如果车上有人
        else{
            double optimization = 0;
            ReservationA selectorder = null;
            for (ReservationA ReservationA : unselectorder) {

                //选择最优且符合时间、空位的乘客，修改相关量，删除该乘客，若无则去当前乘客目的地；返回
                //通过人数和时间初筛选
                double newdistance = getdistance(pos, ReservationA.getPos1());
                int newtime=gettime(pos, ReservationA.getPos1());
                if (maxnum - nownum >= ReservationA.getPassagerNum() && ((time + newtime) > (ReservationA.getTime() - 10*60))) {
                    //求先送完当前乘客再接新乘客的距离
                    double basedistance = getdistance(pos, temproute.get(0));
                    for (int j = 0; j < temproute.size() - 1; j++) {
                        basedistance += getdistance(temproute.get(j), temproute.get(j + 1));
                    }
                    basedistance += getdistance(temproute.get(temproute.size() - 1), ReservationA.getPos1());
                    basedistance += getdistance(ReservationA.getPos1(), ReservationA.getPos2());
                    //求先接新乘客再送当前乘客的距离，加上超时奖励
                    double testdistance = getdistance(pos, ReservationA.getPos1());
                    if (ReservationA.getTime() > time) {
                        testdistance += (ReservationA.getTime() - time) * 40;
                    }
                    testdistance += getdistance(ReservationA.getPos1(), temproute.get(0));
                    for (int j = 0; j < temproute.size() - 1; j++) {
                        testdistance += getdistance(temproute.get(j), temproute.get(j + 1));
                    }
                    testdistance += getdistance(temproute.get(temproute.size() - 1), ReservationA.getPos2());
                    //相减，选非零最大项的乘客，修改相关量
                    if (basedistance - testdistance > optimization) {
                        optimization = basedistance - testdistance;
                        selectorder = ReservationA;

                    }

                }
            }
            if(selectorder != null)
            {
                nownum = nownum + selectorder.getPassagerNum();
                nexttime += gettime(pos,selectorder.getPos1());
                pos = selectorder.getPos1();
                orderIDinSequence.add(selectorder.getOrderID());
                selectorder.setCarID(driver.getCarId());
                temproute.add(selectorder.getPos2());
                temproutefordb.add(selectorder.getOrderID());
                route.add(selectorder.getPos1());
                routefordb.add(selectorder.getOrderID());
                //向数据库中更新数据,乘客所乘坐的车辆车牌号和预计到达时间
                //从unselectorder中删除
                unselectorder.remove(selectorder);


            }
            //若无，送当前最优乘客，修改相关量，尚未完成
            else{
                //乘客人数未知，暂定为1
                nownum -= ordernumMap.get(orderIDinSequence.get(0));
                orderIDinSequence.remove(0);
                //暂定送最先上车的乘客
                nexttime += gettime(pos,temproute.get(0));
                pos = temproute.get(0);
                route.add(temproute.get(0));
                routefordb.add(temproutefordb.get(0));
                temproute.remove(0);
                temproutefordb.remove(0);
            }
        }

    }

}
