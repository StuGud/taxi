package com.srtp.taxi.algorithm;

import com.srtp.taxi.entity.Reservation;

public class ReservationA {
    private long orderID;
    private long passagerID;
    private long carID;
    //出发时间
    private long Time;
    //乘客数
    private int passagerNum;
    private Position pos1;
    private Position pos2;
    public ReservationA(Reservation o) {
        orderID=o.getId();
        passagerID=o.getUserId();
        Time=o.getStartAt().getTime()/1000;
        passagerNum=o.getNum();
        pos1=new Position(o.getStart_lng(),o.getStart_lat());
        pos2=new Position(o.getEnd_lng(),o.getEnd_lat());
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

    public long getTime() {
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

    public Position getPos1() {
        return pos1;
    }

    public void setPos1(Position pos1) {
        this.pos1 = pos1;
    }

    public Position getPos2() {
        return pos2;
    }

    public void setPos2(Position pos2) {
        this.pos2 = pos2;
    }
}
