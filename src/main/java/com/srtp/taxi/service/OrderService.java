package com.srtp.taxi.service;

import com.srtp.taxi.entity.Order;
import com.srtp.taxi.entity.Reservation;

import java.util.List;

public interface OrderService {
    /**
     * 先删除dispatch中的对应内容，再删除reservation，最后添加order
     * @param reservationId
     * @return
     */
    Order saveOrderByReservationId(long reservationId);
    List<Order> findOrderByUserId(long userId);
    List<Order> findOrderByDriverId(long driverId);
}
