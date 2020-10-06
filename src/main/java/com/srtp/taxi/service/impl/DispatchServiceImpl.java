package com.srtp.taxi.service.impl;

import com.srtp.taxi.entity.Dispatch;
import com.srtp.taxi.entity.ReservationDispatched;
import com.srtp.taxi.mapper.DispatchMapper;
import com.srtp.taxi.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;


@Service
public class DispatchServiceImpl implements DispatchService {

    @Autowired
    DispatchMapper dispatchMapper;

    @Override
    public Dispatch getDispatchByDriverId(long driverId) {
        Dispatch dispatch = dispatchMapper.findDispatchByDriverId(driverId);
        if(dispatch!=null&&!dispatch.getReservationList().isEmpty()){
            dispatch.getReservationList().sort(Comparator.comparing(ReservationDispatched::getDispatchId));
            return dispatch;
        }
        return null;
    }

}
