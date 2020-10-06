package com.srtp.taxi.service.impl;

import com.srtp.taxi.entity.Order;
import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.mapper.DispatchMapper;
import com.srtp.taxi.mapper.OrderMapper;
import com.srtp.taxi.mapper.ReservationMapper;
import com.srtp.taxi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    DispatchMapper dispatchMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ReservationMapper reservationMapper;

    @Override
    @Transactional
    public Order saveOrderByReservationId(long reservationId) {
        //获得司机信息，获得用户信息；拼接为order
        long driverId= dispatchMapper.findDriverIdByReservationId(reservationId);
        dispatchMapper.deleteDispatchByReservationId(reservationId);
        Reservation reservation=reservationMapper.queryReservationById(reservationId);
        reservationMapper.deleteReservationById(reservationId);
        Order order = reservation.convertToOrder(driverId);
        if(orderMapper.saveOrder(order)){
            return order;
        }
        return null;
    }

    @Override
    public List<Order> findOrderByUserId(long userId) {
        return orderMapper.queryOrderByUserId(userId);
    }

    @Override
    public List<Order> findOrderByDriverId(long driverId) {
        return orderMapper.queryOrderByDriverId(driverId);
    }
}
