package com.fqts.rating.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundToOneDecimal {
    public static Float roundToOneDecimal(Float value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value)
                .setScale(1, RoundingMode.HALF_UP)
                .floatValue(); // Convert back to Float
    }
}
