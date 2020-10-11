package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Order;
import com.srtp.taxi.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 逻辑：司机提交完成，生成订单
 */
@RestController
@Api(tags = "订单相关文档")
public class OrderController {

    @Autowired
    OrderService orderService;


    @ApiOperation("完成预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name="reservationId",dataType="long",value="预约的id")

    })
    @GetMapping("driver/order/{reservationId}/complete")
    public Order complete(@PathVariable long reservationId){
        return orderService.saveOrderByReservationId(reservationId);
    }


    @ApiOperation("通过司机id获取他的全部订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="driverId",dataType="long",value="司机的id")

    })
    @GetMapping("driver/{driverId}/order")
    public List<Order> getOrdersByDriverId(@PathVariable long driverId){
        return orderService.findOrderByDriverId(driverId);
    }

    @ApiOperation("通过用户id获取他的全部订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",dataType="long",value="用户的id")
    })
    @GetMapping("user/{userId}/order")
    public List<Order> getOrderByUserId(@PathVariable long userId){
        return orderService.findOrderByUserId(userId);
    }
}
