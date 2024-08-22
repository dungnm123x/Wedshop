/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Category;

/**
 *
 * @author HP
 */
public class DAOCategory extends DBContext {
    
    public Vector<Category> getAllCategory() {
        String sql = "Select * from Categories";
        Vector<Category> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("categoriesId"),
                        rs.getString("categoriesName"),
                        rs.getString("describe")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Vector<Category> getAllCategoryById(int brandId) {
        String sql = "Select C.categoriesId, C.categoriesName from Categories C where C.categoriesId = ?";
        Vector<Category> list = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brandId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(
                        rs.getInt("categoriesId"),
                        rs.getString("categoriesName")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
     public static void main(String[] args) {
        DAOCategory nCAT= new DAOCategory();
        System.out.println(nCAT.getAllCategoryById(1));
    }
}
