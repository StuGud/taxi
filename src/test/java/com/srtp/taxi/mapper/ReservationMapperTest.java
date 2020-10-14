package com.srtp.taxi.mapper;

import com.srtp.taxi.entity.Reservation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReservationMapperTest {

    @Autowired
    ReservationMapper reservationMapper;

    @Test
    void saveReservation() throws ParseException {

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        String time = "20-10-14 5:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd H:m:s");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = format.parse(time);

        Reservation reservation = new Reservation(1, 2, 3, 4, 4, date, 1);
        reservationMapper.saveReservation(reservation);
    }

    @Test
    void deleteReservationById() {
    }

    @Test
    void setIsDispatched() {
    }

    @Test
    void queryReservationById() {
    }

    @Test
    void queryReservationByUserId() {
    }

    @Test
    void queryAllReservation() {
    }

    @Test
    void queryReservationByIsDispatched() {
    }
}