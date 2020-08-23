package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Reservation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */
@Mapper
public interface ReservationMapper {
    /**
     * 根据用户名查询预约信息
     * @param username 用户名
     * @return  返回null,无此用户
     */
    @Select("select * from t_reservation where username=#{username}")
    public Reservation queryReservationByUsername(String username);

    /**
     * 列举所有预约信息
     * @return  返回null,无此用户
     */
    @Select("select * from t_reservation")
    public List<Reservation> listAllReservation();


    /**
     * 保存预约信息
     * @param reservation
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_reservation(username,start_lng,start_lat,end_lng,end_lat,date,num) values (#{username},#{start_log},#{start_lat},#{end_lng},#{end_lat},#{date},#{num}")
    public int saveReservation(Reservation reservation);
}
