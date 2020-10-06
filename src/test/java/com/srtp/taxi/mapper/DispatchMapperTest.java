package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Dispatch;
import com.srtp.taxi.entity.ReservationDispatched;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DispatchMapperTest {

    @Autowired
    DispatchMapper dispatchMapper;

    @Test
    void findDispatchByDriverId() {
        Dispatch dispatchByDriverId = dispatchMapper.findDispatchByDriverId(1);
        System.out.println(dispatchByDriverId);
        for (ReservationDispatched reservationDispatched:dispatchByDriverId.getReservationList()){
            System.out.println(reservationDispatched);
        }
    }
}