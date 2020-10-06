package com.srtp.taxi.restController;

import com.srtp.taxi.entity.Dispatch;
import com.srtp.taxi.entity.Reservation;
import com.srtp.taxi.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DispatchController {

    @Autowired
    DispatchService dispatchService;

    /**
     *
     * @param driverId
     * @return list中是成对出现的reservation；第一次出现的是起点，第二次则是终点
     */
    @GetMapping("/{driverId}/dispatch")
    public Dispatch listTasks(@PathVariable long driverId){
        return dispatchService.getDispatchByDriverId(driverId);
    }
}
