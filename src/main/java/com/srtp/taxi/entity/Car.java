package com.srtp.taxi.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class Car {
    @ApiModelProperty(value = "车辆id")
    private long id;
    // /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;
    //车辆型号
    @ApiModelProperty(value = "车辆型号")
    private String model;
    @ApiModelProperty(value = "车辆颜色")
    private String color;

    public Car(long id, String plateNumber, String model, String color) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
    }
}
