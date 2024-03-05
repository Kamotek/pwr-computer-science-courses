package com.example;

public class ProductionResultMessage {
    private final String productName;
    private final int quantity;

    public ProductionResultMessage(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
