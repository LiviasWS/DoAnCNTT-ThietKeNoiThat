package com.model;

public class Order {
	private int paymentId;
    private String orderDate;
    private String productName;
    private int productBuy;
    private float unitPrice;
    private float productTotal;
    private float totalAmount;

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getProductBuy() { return productBuy; }
    public void setProductBuy(int productBuy) { this.productBuy = productBuy; }
    public float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(float unitPrice) { this.unitPrice = unitPrice; }
    public float getProductTotal() { return productTotal; }
    public void setProductTotal(float productTotal) { this.productTotal = productTotal; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
}
