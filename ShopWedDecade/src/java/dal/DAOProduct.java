/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Product;

/**
 *
 * @author HP
 */
public class DAOProduct extends DBContext {
    public Product getProductById(int proId) {
        String sql = "Select * from Products P where P.productId = ?";
        Product product = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, proId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) { 
                product = new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoriesId"),
                        rs.getInt("available"),
                        rs.getString("productName"),
                        rs.getString("productImg"),
                        rs.getDouble("listPrice"),
                        rs.getString("describe")   
                );
                return product;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }
    
    public Vector<Product> getProductByCid(int cateId) {
        String sql = "Select * from Products P where P.categoriesId = ?";
        Vector<Product> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cateId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) { 
                list.add(new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoriesId"),
                        rs.getInt("available"),
                        rs.getString("productName"),
                        rs.getString("productImg"),
                        rs.getDouble("listPrice"),
                        rs.getString("describe")  
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Vector<Product> getProductBySort(String sortValue) {
        String sql = "Select * from Products\n";

        Vector<Product> list = new Vector<>();
        if (sortValue.equalsIgnoreCase("ASC")) {
            sql += "order by listPrice ASC";
        } else if (sortValue.equalsIgnoreCase("DESC")) {
            sql += "order by listPrice DESC";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) { 
                list.add(new Product(
                        rs.getInt("productId"),
                        rs.getInt("categoriesId"),
                        rs.getInt("available"),
                        rs.getString("productName"),
                        rs.getString("productImg"),
                        rs.getDouble("listPrice"),
                        rs.getString("describe")  
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Vector<Product> getAllProduct() {
        String sql = "Select * from Products";
        Vector<Product> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getInt("available"),
                        rs.getString("productName"),
                        rs.getString("productImg"),
                        rs.getString("describe"),
                        rs.getDouble("listPrice")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Vector<Product> searchProduct(String searchValue) {
        String sql = "Select * from Products where productName like ?";
        Vector<Product> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%"+searchValue+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("productId"),
                        rs.getInt("available"),
                        rs.getString("productName"),
                        rs.getString("productImg"),
                        rs.getString("describe"),
                        rs.getDouble("listPrice")
                );
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public boolean deleteProduct(int productId) {
        String sql = "delete from Products where productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean insertProduct(Product pro) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([categoriesId]\n"
                + "           ,[productName]\n"
                + "           ,[available]\n"
                + "           ,[listPrice]\n"
                + "           ,[describe]\n"
                + "           ,[productImg])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pro.getCategoriesId());
            st.setString(2, pro.getProductName());
            st.setInt(3, pro.getAvailable());
            st.setDouble(4, pro.getListPrice());
            st.setString(5, pro.getDescribe());
            st.setString(6, pro.getProductImg());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean updateProduct(Product pro) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [categoriesId] = ?\n"
                + "      ,[productName] = ?\n"
                + "      ,[available] = ?\n"
                + "      ,[listPrice] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[productImg] = ?\n"
                + " WHERE productId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pro.getCategoriesId());
            st.setString(2, pro.getProductName());
            st.setInt(3, pro.getAvailable());
            st.setDouble(4, pro.getListPrice());
            st.setString(5, pro.getDescribe());
            st.setString(6, pro.getProductImg());
            st.setInt(7, pro.getProductId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        System.out.println(dao.searchProduct("LEGO"));
    }
}
