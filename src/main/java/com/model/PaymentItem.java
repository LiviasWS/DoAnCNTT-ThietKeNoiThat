package com.model;

public class PaymentItem {
    private int productId;
    private int quantity;
    private float totalPrice;
    private int itemid;
    private int paymentid;
    private String productName;
    public PaymentItem() {

    }
    
    public PaymentItem(int productId, int quantity, float totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

	public void setPaymentItemId(int itemid) {
		this.itemid = itemid;
		
	}
	public int getPaymentItemId() {
		return itemid;
	}
	public void setPaymentId(int paymentid) {
		this.paymentid = paymentid;
		
	}
	public int getPaymentId() {
		return paymentid;
	}

	public void setProductName(String productName) {
		this.productName = productName;		
	}
	public String getProductName() {
		return productName;
	}

}

