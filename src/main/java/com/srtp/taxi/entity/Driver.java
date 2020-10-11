package com.srtp.taxi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Driver {
    @ApiModelProperty(value = "司机id")
    private long id;
    @ApiModelProperty(value = "司机用户名")
    private String username;
    @ApiModelProperty(value = "司机密码")
    private String password;
    @ApiModelProperty(value = "司机电话号码")
    private String phone;
    // /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/
    @ApiModelProperty(value = "司机车辆的id")
    private long carId;

    public Driver(long id, String username, String password, String phone, long carId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.carId = carId;
    }
}
