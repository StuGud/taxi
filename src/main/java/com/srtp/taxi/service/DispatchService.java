package com.srtp.taxi.service;

import com.srtp.taxi.entity.Dispatch;


public interface DispatchService {
    /**
     * 需要对DispatchMapper返回的reservationList按dispatchId进行排序
     * @param driverId
     * @return
     */
    Dispatch findDispatchByDriverId(long driverId);
}
