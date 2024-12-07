package com.model;

public class Favorite {
	private int accountId;
    private int productId;
    private String status;
    private int buy;
    private float totalPrice;
    
    public Favorite(int accountId, int productId) {
        this.accountId = accountId;
        this.productId = productId;
    }
    
    public Favorite(int accountId, int productId, int buy) {
        this.accountId = accountId;
        this.productId = productId;
        this.buy = buy;
    }
    
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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
