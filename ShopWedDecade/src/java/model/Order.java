/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author HP
 */
public class Order {
    private int orderId, accountId;
    private String orderDate, status;
    private Account acc;
    Vector<OrderItem> orderItems;
    public Order() {
    }

    public Order(int orderId, int accountId, String orderDate, String status) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.status = status;
    }
    public Order(int orderId, int accountId, String orderDate, String status, Account acc) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.status = status;
        this.acc = acc;
    }
    
    public Order(int orderId, int accountId, String orderDate, String status,
            Account acc, Vector<OrderItem> orderItems) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.status = status;
        this.acc = acc;
        this.orderItems = orderItems;
    }
    public Order(int accountId, String status) {
        this.accountId = accountId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public Vector<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", accountId=" + accountId + ", orderDate=" + orderDate + ", status=" + status + ", acc=" + acc + ", orderItems=" + orderItems + '}';
    }


}
