package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.mapper.ReservationMapper;
import com.srtp.taxi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by david.w on 2020/4/14.
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationMapper reservationMapper;

    @Override
    public void reserve(Reservation reservation) {
        reservationMapper.saveReservation(reservation);
    }
}
