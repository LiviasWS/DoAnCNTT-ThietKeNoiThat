package com.model;

public class Cart {
	private int accountId;
    private int productId;
    private float total;

    // Constructor
    public Cart(int accountId, int productId, float total) {
        this.accountId = accountId;
        this.productId = productId;
        this.total = total;
    }

    // Getters and Setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
