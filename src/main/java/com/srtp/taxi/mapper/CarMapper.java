package com.srtp.taxi.mapper;


import com.srtp.taxi.entity.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
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
    Car queryCarByPlateNumber(String plateNumber);

    /**
     * 保存车辆信息
     * @param car
     * @return 返回null,操作失败
     */
    @Insert("insert into t_car(plateNumber,model,color) values (#{plateNumber},#{model},#{color})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean saveCar(Car car);
}
