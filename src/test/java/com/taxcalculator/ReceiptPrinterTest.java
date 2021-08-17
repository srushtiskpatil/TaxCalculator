package com.taxcalculator;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptPrinterTest {
    private final List<Product> productList = new ArrayList<>();
    ReceiptPrinter receiptPrinter;

    @Test
    public void testTaxCalculatorForLocalProducts(){
        lets().buy()
                .book()
                .musicCD()
                .chocolates("chocolate bar", 40.85, false)
                .andGetReceipt(
                "1 book: 124.99\n" +
                "1 music CD: 164.99\n" +
                "1 chocolate bar: 40.85\n" +
                "Tax : 15.00 \n" +
                "Total : 330.83");
    }

    @Test
    public void testTaxCalculatorForImportedProducts(){
        lets().buy()
                .chocolates("imported box of chocolates", 100.0, true)
                .perfume(470.5, "imported bottle of perfume", true)
                .andGetReceipt(
                "1 imported box of chocolates: 105.00\n" +
                        "1 imported bottle of perfume: 541.05\n" +
                        "Tax : 75.55 \n" +
                        "Total : 646.05");
    }

    @Test
    public void testTaxCalculatorForLocalAndImportedProducts(){
        lets().buy()
                .perfume(270.99, "imported bottle of perfume", true)
                .perfume(180.99, "bottle of perfume", false)
                .headachePills()
                .chocolates("imported box of chocolates", 210.25, true)
                .andGetReceipt(
                "1 imported bottle of perfume: 311.64\n" +
                        "1 bottle of perfume: 199.09\n" +
                        "1 packet of headache pills: 19.75\n" +
                        "1 imported box of chocolates: 220.76\n" +
                        "Tax : 69.26 \n" +
                        "Total : 751.24");
    }

    private ReceiptPrinterTest headachePills() {
        productList.add(new Product("packet of headache pills", 19.75, ProductType.MEDICAL_PRODUCTS, false, 1));
        return this;
    }

    private ReceiptPrinterTest perfume(double cost, String name, boolean isImported) {
        productList.add(new Product(name, cost, ProductType.OTHERS, isImported, 1));
        return this;
    }

    private void andGetReceipt(String expectedTax) {
        assertEquals(expectedTax, receiptPrinter.printReceipt(productList));
    }

    private ReceiptPrinterTest chocolates(String name, double cost, boolean isImported) {
        productList.add(new Product(name, cost, ProductType.FOOD, isImported, 1));
        return this;
    }

    private ReceiptPrinterTest musicCD() {
        productList.add(new Product("music CD", 149.99, ProductType.OTHERS, false, 1));
        return this;
    }

    private ReceiptPrinterTest book() {
        productList.add(new Product("book", 124.99, ProductType.BOOKS, false, 1));
        return this;
    }

    private ReceiptPrinterTest buy() {
        receiptPrinter = new ReceiptPrinter();
        return this;
    }

    private ReceiptPrinterTest lets() {
        return this;
    }
}
