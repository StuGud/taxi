package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.mapper.ReservationMapper;
import com.srtp.taxi.service.ReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="预约相关文档")
@RestController
@RequestMapping("/user")
public class ReservationController {

    @Autowired
    ReservationService reservationService;


    @ApiOperation("用户预约")
    @PostMapping("/reserve")
    public Reservation reserve(Reservation reservation){
        return reservationService.reserve(reservation);
    }


    @ApiOperation("用户取消预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name="reservationId",dataType="long",value = "预约的id")
    })
    @DeleteMapping("/{reservationId}")
    public String cancel(@PathVariable long reservationId){
        if(reservationService.cancel(reservationId)){
            return "取消预约成功";
        }else{
            return "取消预约失败";
        }
    }


    @ApiOperation("用户所有预约")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",dataType="long",value = "用户的id")
    })
    @GetMapping("/{userId}/reservations")
    public List<Reservation> list(@PathVariable long userId){
        return reservationService.listReservationByUserId(userId);
    }
}
