package com.srtp.taxi.controller;

import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/reserve")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping("/reserve")
    public String reserve(Reservation reservation){
        System.out.println("保存的用户预约信息"+reservation);
        reservationService.reserve(reservation);
        return "/pages/user/reserve_succeed";
    }
}
