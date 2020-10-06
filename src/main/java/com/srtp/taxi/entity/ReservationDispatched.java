package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 这个类的作用是为了mybatis 返回collection时去重;也方便列表按照dispatchId排序
 */
@Data
@NoArgsConstructor
public class ReservationDispatched {
    private long id;
    private long dispatchId;
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
}
