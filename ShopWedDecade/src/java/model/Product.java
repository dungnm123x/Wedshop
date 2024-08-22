/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author HP
 */
public class Product {
    private int productId, categoriesId, available, discount, quantity;
    private String productName, productImg, describe, cateName;
    private double listPrice;
    
    public Product() {
    }

    public Product(int productId, int available, 
            String productName, String productImg, String describe, 
            double listPrice) {
        this.productId = productId;
        this.available = available;
        this.productName = productName;
        this.productImg = productImg;
        this.describe = describe;
        this.listPrice = listPrice;
    }

    public Product(int productId, int categoriesId, int available,
            String productName, String productImg, double listPrice, String describe) {
        this.productId = productId;
        this.categoriesId = categoriesId;
        this.available = available;
        this.productName = productName;
        this.productImg = productImg;
        this.listPrice = listPrice;
        this.describe = describe;
    }
    public Product(int categoriesId, int available,
            String productName, String productImg, double listPrice, String describe) {
        this.categoriesId = categoriesId;
        this.available = available;
        this.productName = productName;
        this.productImg = productImg;
        this.listPrice = listPrice;
        this.describe = describe;
    }
    
    
    public Product(int productId, String productImg,
            String productName, int quantity, double listPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
        this.quantity = quantity;
        this.listPrice = listPrice;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
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
        String formattedPrice = String.format("%,.0f", listPrice*quantity);
        formattedPrice = formattedPrice.replace(",", ".");
        return formattedPrice;
    }
    
    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", categoriesId=" + categoriesId + ", available=" + available + ", discount=" + discount + ", productName=" + productName + ", productImg=" + productImg + ", listPrice=" + listPrice + '}';
    }
    
}

    
    
    
        
