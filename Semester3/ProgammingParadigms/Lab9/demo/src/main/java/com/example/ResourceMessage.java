package com.example;

public class ResourceMessage {
    private final String resourceName;
    private final int quantity;

    public ResourceMessage(String resourceName, int quantity) {
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getQuantity() {
        return quantity;
    }
}
