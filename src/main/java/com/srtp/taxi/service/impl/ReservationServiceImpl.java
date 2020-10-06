package com.srtp.taxi.service.impl;


import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.mapper.ReservationMapper;
import com.srtp.taxi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationMapper reservationMapper;

    @Override
    public Reservation reserve(Reservation reservation) {
        if(reservationMapper.saveReservation(reservation)){
            return reservation;
        }
        return null;
    }

    @Override
    public Reservation setIsDispatched(long reservationId,boolean isDispatched) {
        return null;
    }

    @Override
    public boolean cancel(long reservationId) {
        return reservationMapper.deleteReservationById(reservationId);
    }

    @Override
    public List<Reservation> listReservationByUserId(long userId) {
        List<Reservation> reservations = reservationMapper.queryReservationByUserId(userId);
        if(!reservations.isEmpty()){
            return reservations;
        }
        return null;
    }
}
