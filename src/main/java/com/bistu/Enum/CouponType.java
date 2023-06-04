package com.bistu.Enum;

public enum CouponType {
    AMOUNT_OFF_50_27(27),
    AMOUNT_OFF_40_15(15),
    AMOUNT_OFF_30_3(3),
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
