/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import model.Order;
import model.OrderItem;
import model.Product;

/**
 *
 * @author HP
 */
public class DAOOrderItem extends DBContext {
    public List<OrderItem> getAllOrders() {
        Vector<OrderItem> listOrderItem = new Vector<>();
        try {
            String query = "SELECT * FROM OrderItems";
            try ( PreparedStatement st = connection.prepareStatement(query)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listOrderItem.add(new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("productId"),
                        rs.getInt("quantity"),
                        rs.getDouble("listPrice"))
                );
               }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrderItem;
    }
    
    public Vector<OrderItem> getAllOrdersByOrderId(int orderId) {
        Vector<OrderItem> listOrderItem = new Vector<>();
        DAOProduct dPro = new DAOProduct();
        try {
            String query = "SELECT * FROM OrderItems where orderId = ?";
            try ( PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, orderId);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listOrderItem.add(new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("quantity"),
                        rs.getDouble("listPrice"),
                        dPro.getProductById(rs.getInt("productId"))
                    )
                );
              }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrderItem;
    }
    public static void main(String[] args) {
        DAOOrderItem oi = new DAOOrderItem();
        System.out.println(oi.getAllOrders());
    }
}
