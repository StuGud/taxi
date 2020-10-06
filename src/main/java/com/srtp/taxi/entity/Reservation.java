package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Reservation {
    private long id;
    private long userId;
    //起点经纬度
    private double start_lng;
    private double start_lat;
    //终点经纬度
    private double end_lng;
    private double end_lat;
    //出发时间
    private Date startAt;
    //乘客数
    private int num;
    private boolean isDispatched;

    /**
     * 完成时间finishedAt自动设为当前时间
     * @param driverId
     * @return
     */
    public Order convertToOrder(long driverId){
        Date finishedAt=new Date();
        Order order=new Order(userId,driverId,start_lng,start_lat,end_lng,end_lat,startAt,finishedAt,num);
        return order;
    }
}
