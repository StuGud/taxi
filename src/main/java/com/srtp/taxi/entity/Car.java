package com.srtp.taxi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car {
    private long id;
    // /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/
    private String plateNumber;
    //车辆型号
    private String model;
    private String color;

    public Car(long id, String plateNumber, String model, String color) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
    }
}
