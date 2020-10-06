package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Dispatch;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DispatchMapper {
    Dispatch findDispatchByDriverId(long driverId);
}
