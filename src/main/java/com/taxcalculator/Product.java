package com.taxcalculator;

import java.math.BigDecimal;
import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class Product {
    private final String name;
    private final BigDecimal cost;
    private final ProductType productType;
    private final boolean isImported;
    private final int count;
    private static final int IMPORT_DUTY = 5;

    Product(String name, double cost, ProductType productType, boolean isImported, int count) {
        this.name = name;
        this.cost = valueOf(cost).multiply(valueOf(count));
        this.productType = productType;
        this.isImported = isImported;
        this.count = count;
    }

    public BigDecimal calculateTaxValue(){
        BigDecimal taxValue = productType.calculateBasicTaxFor(cost).multiply(valueOf(count));
        if (isImported) taxValue = taxValue.add(cost.multiply(valueOf(IMPORT_DUTY)).divide(valueOf(100), HALF_UP));
        return taxValue;
    }

    public BigDecimal calculateTaxInclusivePrice() {
        return cost.add(calculateTaxValue());
    }

    public String printProductDetails() {
        return format("%s %s: %s", count, name, calculateTaxInclusivePrice().setScale(2, HALF_UP));
    }
}
