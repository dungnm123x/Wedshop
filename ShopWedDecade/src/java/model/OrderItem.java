/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class OrderItem {
    private int orderItemId, orderId, productId, discount, quantity;
    private double listPrice;
    private Product product;

    public OrderItem() {
    }

    public OrderItem(int orderItemId, int orderId, int productId, 
            int quantity, double listPrice) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.listPrice = listPrice;
    }
    public OrderItem(int orderItemId, int orderId, 
            int quantity, double listPrice, Product product) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.listPrice = listPrice;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public Product getProduct() {
        return product;
    }
    
public String getPriceAfterDiscount() {
        double productPrice = listPrice - (listPrice * (discount * 0.01));
        String formattedPrice = String.format("%,.0f", productPrice);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }
    public String getPriceFormat() {
        String formattedPrice = String.format("%,.0f", listPrice);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }
    public String getPriceSubTotal() {
        double productPrice = listPrice - (listPrice * (discount * 0.01));
        String formattedPrice = String.format("%,.0f", productPrice*quantity);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "orderItemId=" + orderItemId + ", orderId=" + orderId + ", productId=" + productId + ", discount=" + discount + ", quantity=" + quantity + ", listPrice=" + listPrice + ", product=" + product + '}';
    }
    
}
