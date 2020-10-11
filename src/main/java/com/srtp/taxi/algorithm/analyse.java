package com.srtp.taxi.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.srtp.taxi.entity.*;
public class analyse {
    public static void execute(){
        int time = 0;//time需要初始化
        HashMap<Long,Integer> ordernumMap=new HashMap<Long,Integer>();
        ArrayList<order> unselectorder = new ArrayList<order>();
        ArrayList<driverSelect> alldriver = new ArrayList<driverSelect>();
        ArrayList<driverSelect> tempdriver = new ArrayList<driverSelect>();
//        访问数据库初始化以上变量
        List<Order> listofOrder=findAllOrders();
        List<Driver> listofDriver=findAllDrivers();

        for(Order o : listofOrder){
            unselectorder.add(new order(o));
            ordernumMap.put(o.getId(),o.getNum());
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
            time++;
        }
        //向数据库中写入所有司机的所有（按顺序）的乘客ID，路线（节点位置）信息
        for(driverSelect ds:alldriver) {
            updateRouteByDriverID(ds.getDriver().getId(), ds.getRoutefordb());
        }
    }
}
