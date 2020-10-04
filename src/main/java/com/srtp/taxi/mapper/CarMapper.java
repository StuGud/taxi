package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by david.w on 2020/4/14.
 */
@Component
@Mapper
public interface CarMapper {
    /**
     * 根据车牌号查询车辆信息
     * @param plateNumber
     * @return  返回null,无此车辆
     */
    @Select("select * from t_car where plateNumber=#{plateNumber}")
    public Car queryCarByPlateNumber(String plateNumber);

    /**
     * 保存车辆信息
     * @param car
     * @return 返回-1,操作失败
     */
    @Insert("insert into t_car(plateNumber,brand,model,color) values (#{plateNumber},#{brand},#{model},#{color})")
    public Car saveCar(Car car);
}
