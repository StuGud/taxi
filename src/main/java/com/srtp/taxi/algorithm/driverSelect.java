package com.srtp.taxi.algorithm;
import com.srtp.taxi.entity.*;
import com.srtp.taxi.service.*;
import com.srtp.taxi.utils.RoadDetailUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class driverSelect {
    private Driver driver;
    //存放已确定路线，position类型的列表表示
    private ArrayList<position> route = new ArrayList<position>();
    //存放车上乘客的终点作为待安排路线，position类型的列表表示
    private ArrayList<position> temproute = new ArrayList<position>();
    //存放已确定路线，string类型列表存订单编号，第一次出现为订单的上车地点，第二次出现为订单的下车地点，数据库专用
    private ArrayList<Long> routefordb = new ArrayList<Long>();
    //存放车上乘客的终点作为待安排路线，string类型列表存订单编号，数据库专用
    private ArrayList<Long> temproutefordb = new ArrayList<Long>();
    //最大载客
    private final int maxnum=4;
    //目前载客
    private int nownum=0;
    //预计到达下一个节点的时间
    private int nexttime=0;
    //车辆当前位置
    private position pos=new position(1.01,2.01);// 根据海口需要初始化经纬度
    private ArrayList<Long> orderIDinSequence = new ArrayList<Long>();

    public driverSelect(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }

    public ArrayList<Long> getRoutefordb() {
        return routefordb;
    }

    public int getNexttime() {
        return nexttime;
    }
    boolean isfull() { return maxnum <= nownum; }
    
    public static double getdistance(position p1,position p2){
        return RoadDetailUtils.getDistance(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    }
    public static int gettime(position p1,position p2){
        return RoadDetailUtils.getTime(p1.getX(),p1.getY(),p2.getX(),p2.getY());
    }
    //对于每辆车而言的选择算法，第一个参数是未分配的订单，后一个参数是当前时间
    void select(ArrayList<order> unselectorder, HashMap<Long,Integer> ordernumMap, int time) {
        //如果当前车上没有乘客
        if(nownum == 0) {
            double distance = 10000;
            order selectorder = null;
            for (com.srtp.taxi.algorithm.order order : unselectorder) {
                //选择最近乘客且时间符合的乘客，修改相关量，写回数据库，删除该乘客，若无不改；返回
                double newdistance = getdistance(pos, order.getPos1());
                int newtime=gettime(pos,order.getPos1());
                if (newdistance < distance && ((time + newtime) > (order.getTime() - 10))) {
                    distance = newdistance;
                    selectorder = order;
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
        if(nownum != 0) {
            double optimization = 0;
            order selectorder = null;
            for (com.srtp.taxi.algorithm.order order : unselectorder) {

                //选择最优且符合时间、空位的乘客，修改相关量，删除该乘客，若无则去当前乘客目的地；返回
                //通过人数和时间初筛选
                double newdistance = getdistance(pos, order.getPos1());
                int newtime=gettime(pos,order.getPos1());
                if (maxnum - nownum >= order.getPassagerNum() && ((time + newtime) > (order.getTime() - 10))) {
                    //求先送完当前乘客再接新乘客的距离
                    double basedistance = getdistance(pos, temproute.get(0));
                    for (int j = 0; j < temproute.size() - 1; j++) {
                        basedistance += getdistance(temproute.get(j), temproute.get(j + 1));
                    }
                    basedistance += getdistance(temproute.get(temproute.size() - 1), order.getPos1());
                    basedistance += getdistance(order.getPos1(), order.getPos2());
                    //求先接新乘客再送当前乘客的距离，加上超时奖励
                    double testdistance = getdistance(pos, order.getPos1());
                    if (order.getTime() > time) {
                        testdistance += (order.getTime() - time) * 40;
                    }
                    testdistance += getdistance(order.getPos1(), temproute.get(0));
                    for (int j = 0; j < temproute.size() - 1; j++) {
                        testdistance += getdistance(temproute.get(j), temproute.get(j + 1));
                    }
                    testdistance += getdistance(temproute.get(temproute.size() - 1), order.getPos2());
                    //相减，选非零最大项的乘客，修改相关量
                    if (basedistance - testdistance > optimization) {
                        optimization = basedistance - testdistance;
                        selectorder = order;

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
            if(selectorder == null) {
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
