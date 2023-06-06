package com.bistu.Enum;

public enum CouponType {
    AMOUNT_OFF_100_27(27),
    AMOUNT_OFF_80_15(15),
    AMOUNT_OFF_50_10(10),
    PERCENT_OFF_NINE(0.9),
    PERCENT_OFF_EIGHT(0.8),
    PERCENT_OFF_SEVEN(0.7);

    private final double value;

    CouponType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
