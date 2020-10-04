package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Dispatcher {
    private long id;
    private long driverId;
    private List<Long> reservationIdList;
}
