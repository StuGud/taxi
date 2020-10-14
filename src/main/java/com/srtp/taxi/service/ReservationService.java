package com.srtp.taxi.service;


import com.srtp.taxi.entity.Reservation;

import javax.naming.spi.ResolveResult;
import java.util.List;

/**
 * Created by david.w on 2020/4/14.
 */

public interface ReservationService {
    Reservation reserve(Reservation reservation);
    boolean cancel(long reservationId);
    Reservation setIsDispatched(long reservationId,boolean isDispatched);
    List<Reservation> listReservationByUserId(long userId);
    List<Reservation> listAll();
    List<Reservation> listAllNotDispatched();
    List<Reservation> listAllNotDispatchedInEightHours();
}
