package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface OrderMapper {

    @Insert("insert into t_order(userId,driverId,start_lng,start_lat,end_lng,end_lat,startAt,finishedAt,num) " +
            "values(#{userId},#{driverId},#{start_lng},#{start_lat},#{end_lng},#{end_lat},#{startAt},#{finishedAt},#{num})")
    boolean saveOrder(Order order);

    @Select("select from t_order where userId=#{userId}")
    List<Order> queryOrderByUserId(long userId);

    @Select("select from t_order where driverId=#{driverId}")
    List<Order> queryOrderByDriverId(long driverId);


}
