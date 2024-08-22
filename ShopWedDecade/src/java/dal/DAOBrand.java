/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import constant.IConstant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Brand;
import model.Category;

/**
 *
 * @author HP
 */
public class DAOBrand extends DBContext {
    DAOCategory dCat = new DAOCategory();
    public Vector<Brand> getAllBrand() {
        String sql = "Select * from Brands";
        Vector<Brand> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand c = new Brand(
                        rs.getInt("brandId"),
                        rs.getString("brandName"),
                        rs.getString("country"),
                        rs.getString("website"),
                        rs.getString("logo")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    
    public Brand getBrandById(int brandId) {
        String sql = "Select * from Brands B where B.brandId = ?";
        Brand brand = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brandId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                brand = new Brand(
                        rs.getInt("brandId"),
                        rs.getString("brandName"),
                        rs.getString("country"),
                        rs.getString("website"),
                        rs.getString("logo")
                );
                return brand;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return brand;
    }
    public boolean UpdateBrandById(String brandName, String country, 
            String website, String logo, int brandId) {
        String sql = "UPDATE [dbo].[Brands]\n"
            + "   SET [brandName] = ?\n"
            + "      ,[country] = ?\n"
            + "      ,[website] = ?\n"
            + "      ,[logo] = ?\n"
            + " WHERE brandId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, brandName);
            st.setString(2, country);
            st.setString(3, website);
            st.setString(4, logo);
            st.setInt(5, brandId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public static void main(String[] args) {
        DAOBrand Brand = new DAOBrand();
    }
}
