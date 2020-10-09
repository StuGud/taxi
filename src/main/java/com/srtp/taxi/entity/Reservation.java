package com.srtp.taxi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Reservation {
    @ApiModelProperty(value = "预约id")
    private long id;
    @ApiModelProperty(value = "预约用户id")
    private long userId;
    //起点经纬度
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
    //乘客数
    @ApiModelProperty(value = "乘客数量")
    private int num;
    @ApiModelProperty(value = "预约是否被接受")
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
