package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.mapper.ReservationMapper;
import com.srtp.taxi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/reserve")
    public Reservation reserve(Reservation reservation){
        return reservationService.reserve(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public String cancel(@PathVariable long reservationId){
        if(reservationService.cancel(reservationId)){
            return "取消预约成功";
        }else{
            return "取消预约失败";
        }
    }

    @GetMapping("/{userId}/reservations")
    public List<Reservation> list(@PathVariable long userId){
        return reservationService.listReservationByUserId(userId);
    }
}
