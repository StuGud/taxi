package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Reservation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */
@Component
@Mapper
public interface ReservationMapper {

    @Insert("insert into t_reservation(userId,start_lng,start_lat,end_lng,end_lat,date,num,isDispatched) values (#{userId},#{start_log},#{start_lat},#{end_lng},#{end_lat},#{date},#{num},#{isDispatched})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean saveReservation(Reservation reservation);

    @Delete("delete from t_reservation where id=#{reservationId}")
    boolean deleteReservationById(long reservationId);

    @Update("update t_reservation set isDispatched=#{isDispatched} where id=#{reservationId}")
    boolean setIsDispatched(long reservationId,boolean isDispatched);


    @Select("select * from t_reservation where userId=#{userId}")
    List<Reservation> queryReservationByUserId(long userId);

    @Select("select * from t_reservation")
    List<Reservation> queryAllReservation();

    @Select("select * from t_reservation where isDispatched=#{isDispatched}")
    List<Reservation> queryReservationByIsDispatched(boolean isDispatched);

}
