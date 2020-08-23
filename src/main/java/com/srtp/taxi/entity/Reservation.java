package com.srtp.taxi.entity;

import java.util.Date;

public class Reservation {
    private String id;
    private String username;
    //起点经度
    private double start_lng;
    //起点纬度
    private double start_lat;
    //终点经纬度
    private double end_lng;
    private double end_lat;
    //出发时间
    private Date date;
   //乘客数
    private String num;

    public Reservation() {
    }

    public Reservation(String id, String username, double start_lng, double start_lat, double end_lng, double end_lat, Date date, String num) {
        this.id = id;
        this.username = username;
        this.start_lng = start_lng;
        this.start_lat = start_lat;
        this.end_lng = end_lng;
        this.end_lat = end_lat;
        this.date = date;
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getStart_lng() {
        return start_lng;
    }

    public void setStart_lng(double start_lng) {
        this.start_lng = start_lng;
    }

    public double getStart_lat() {
        return start_lat;
    }

    public void setStart_lat(double start_lat) {
        this.start_lat = start_lat;
    }

    public double getEnd_lng() {
        return end_lng;
    }

    public void setEnd_lng(double end_lng) {
        this.end_lng = end_lng;
    }

    public double getEnd_lat() {
        return end_lat;
    }

    public void setEnd_lat(double end_lat) {
        this.end_lat = end_lat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", start_lng=" + start_lng +
                ", start_lat=" + start_lat +
                ", end_lng=" + end_lng +
                ", end_lat=" + end_lat +
                ", date='" + date + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
