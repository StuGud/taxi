package com.srtp.taxi.algorithm;

import lombok.Data;

@Data
public class Position {
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    private double x;
    private double y;
}
