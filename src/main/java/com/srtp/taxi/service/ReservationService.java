package com.srtp.taxi.service;


import com.srtp.taxi.entity.Reservation;

/**
 * Created by david.w on 2020/4/14.
 */

public interface ReservationService {
    /**
     * 用户预约
     * @param reservation
     */
    public void reserve(Reservation reservation);
}
