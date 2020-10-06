package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Order;
import com.srtp.taxi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 逻辑：司机提交完成，生成订单
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("driver/order/{reservationId}/complete")
    public Order complete(@PathVariable long reservationId){
        return orderService.saveOrderByReservationId(reservationId);
    }

    @GetMapping("driver/{driverId}/order")
    public List<Order> getOrdersByDriverId(@PathVariable long driverId){
        return orderService.findOrderByDriverId(driverId);
    }

    @GetMapping("user/{userId}/order")
    public List<Order> getOrderByUserId(@PathVariable long userId){
        return orderService.findOrderByUserId(userId);
    }
}
