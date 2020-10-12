package com.srtp.taxi.algorithm;

import com.srtp.taxi.entity.Order;
import com.srtp.taxi.entity.Reservation;

public class order {
    private long orderID;
    private long passagerID;
    private long carID;
    //出发时间
    private int Time;
    //乘客数
    private int passagerNum;
    private position pos1;
    private position pos2;
    public order(Reservation o) {
        orderID=o.getId();
        passagerID=o.getUserId();
        Time=Integer.parseInt(String.valueOf(o.getStartAt().getTime()).substring(0, 10));
        passagerNum=o.getNum();
        pos1=new position(o.getStart_lng(),o.getStart_lat());
        pos2=new position(o.getEnd_lng(),o.getEnd_lat());
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getPassagerID() {
        return passagerID;
    }

    public void setPassagerID(long passagerID) {
        this.passagerID = passagerID;
    }

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        this.carID = carID;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int getPassagerNum() {
        return passagerNum;
    }

    public void setPassagerNum(int passagerNum) {
        this.passagerNum = passagerNum;
    }

    public position getPos1() {
        return pos1;
    }

    public void setPos1(position pos1) {
        this.pos1 = pos1;
    }

    public position getPos2() {
        return pos2;
    }

    public void setPos2(position pos2) {
        this.pos2 = pos2;
    }
}
