package com.taxcalculator;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

public class ReceiptPrinter {

    public String printReceipt(List<Product> productList) {
        BigDecimal total = ZERO;
        BigDecimal totalTax = ZERO;
        StringBuilder printer = new StringBuilder();
        for (Product product : productList) {
            totalTax = totalTax.add(product.calculateTaxValue());
            total = total.add(product.calculateTaxInclusivePrice());
            printer.append(product.printProductDetails()).append("\n");
        }
        printer.append("Tax : ").append(totalTax.setScale(2, HALF_UP)).append(" \n");
        printer.append("Total : ").append(total.setScale(2, HALF_UP));
        return printer.toString();
    }
}
