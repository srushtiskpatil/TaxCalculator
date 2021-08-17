package com.taxcalculator;

import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;

public enum ProductType {
    BOOKS(0),
    FOOD(0),
    MEDICAL_PRODUCTS(0),
    OTHERS(10);

    private final double basicTax;

    ProductType(double basicTax) {
        this.basicTax = basicTax;
    }

    public BigDecimal calculateBasicTaxFor(BigDecimal cost){
        return cost.multiply(BigDecimal.valueOf(basicTax)).divide(BigDecimal.valueOf(100), HALF_UP);
    }
}
