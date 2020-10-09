package com.srtp.taxi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Order {
    @ApiModelProperty(value = "订单id")
    private long id;
    @ApiModelProperty(value = "订单用户的id")
    private long userId;
    @ApiModelProperty(value = "订单司机的id")
    private long driverId;
    ///起点经纬度
    @ApiModelProperty(value = "起点 经度")
    private double start_lng;
    @ApiModelProperty(value = "起点 纬度")
    private double start_lat;
    //终点经纬度
    @ApiModelProperty(value = "终点 经度")
    private double end_lng;
    @ApiModelProperty(value = "终点 纬度")
    private double end_lat;
    //出发时间
    @ApiModelProperty(value = "出发时间")
    private Date startAt;
    //结束时间
    @ApiModelProperty(value = "结束时间")
    private Date finishedAt;
    //乘客数
    @ApiModelProperty(value = "乘客数量")
    private int num;

    public Order(long userId, long driverId, double start_lng, double start_lat, double end_lng, double end_lat, Date startAt, Date finishedAt, int num) {
        this.userId = userId;
        this.driverId = driverId;
        this.start_lng = start_lng;
        this.start_lat = start_lat;
        this.end_lng = end_lng;
        this.end_lat = end_lat;
        this.startAt = startAt;
        this.finishedAt = finishedAt;
        this.num = num;
    }
}
