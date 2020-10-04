package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Order {
    private long id;
    private long userId;
    private long driverId;
    //起点经纬度
    private double start_lng;
    private double start_lat;
    //终点经纬度
    private double end_lng;
    private double end_lat;
    //出发时间
    private Date startAt;
    //结束时间
    private Date finishedAt;
    //乘客数
    private int num;
}
