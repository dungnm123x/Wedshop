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
public class Brand {
    private int brandId;
    private String brandName, country, website, logo;
    private Vector<Category> listCategory;

    public Brand() {
    }

    public Brand(int brandId, String brandName, String country, String website, String logo, Vector<Category> listCategory) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.country = country;
        this.website = website;
        this.logo = logo;
        this.listCategory = listCategory;
    }

    public Brand(int brandId, String brandName, String country, String website, String logo) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.country = country;
        this.website = website;
        this.logo = logo;
    }

    public Brand(int brandId, String brandName, Vector<Category> listCategory) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.listCategory = listCategory;
    }

    public Brand(int brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Vector<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(Vector<Category> listCategory) {
        this.listCategory = listCategory;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "{" + "brandId:" + brandId + ", brandName=" + brandName + ", country=" + country + ", website=" + website + ", logo=" + logo + '}';
    }

    
}
