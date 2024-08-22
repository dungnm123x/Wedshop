/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import model.Order;
import model.OrderItem;
import model.Product;

public class DAOOrder extends DBContext {

    //hàm lấy ra tất cả các order
    //cho orderManager
    public List<Order> getAllOrders() {
        Vector<Order> listOrder = new Vector<>();
        DAOAccount daoAcc = new DAOAccount();
        try {
            String query = "SELECT * FROM Orders";
            try ( PreparedStatement st = connection.prepareStatement(query)) {
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listOrder.add(new Order(
                            rs.getInt("orderId"),
                            rs.getInt("accountId"),
                            rs.getString("orderDate"),
                            rs.getString("status"),
                            daoAcc.getAccountById(rs.getInt("accountId"))
                    )
                    );
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public Vector<Order> getAllOrdersByAcc(int accId) {
        Vector<Order> listOrder = new Vector<>();
        DAOAccount daoAcc = new DAOAccount();
        DAOOrderItem daoOI = new DAOOrderItem();
        try {
            String query = "SELECT * FROM Orders where accountId = ?";
            try ( PreparedStatement st = connection.prepareStatement(query)) {
                st.setInt(1, accId);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    listOrder.add(new Order(
                            rs.getInt("orderId"),
                            rs.getInt("accountId"),
                            rs.getString("orderDate"),
                            rs.getString("status"),
                            daoAcc.getAccountById(rs.getInt("accountId")),
                            daoOI.getAllOrdersByOrderId(rs.getInt("orderId"))
                     )
                    );
                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listOrder;
    }

    public int insertOrder(Order order) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([accountId]\n"
                + "           ,[orderDate]\n"
                + "           ,[status])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, order.getAccountId());
            st.setTimestamp(2, Timestamp.valueOf(getFormatDate()));
            st.setString(3, "Wait");
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }
            try ( ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }

    public void insertOrderItem(Vector<Product> list, int orderId) {
        String sql = "INSERT INTO [dbo].[OrderItems]\n"
                + "           ([orderId]\n"
                + "           ,[productId]\n"
                + "           ,[listPrice]\n"
                + "           ,[discount]\n"
                + "           ,[quantity])\n"
                + "     VALUES(?, ?, ?, ?, ?)";
        try {
            for (Product item : list) {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, orderId);
                st.setInt(2, item.getProductId());
                st.setDouble(3, item.getListPrice());
                st.setInt(4, item.getDiscount());
                st.setInt(5, item.getQuantity());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateOrderStatus(int orderId, String status) {
        String sql = "UPDATE [dbo].[Orders]\n"
                + "   SET [status] = ?\n"
                + " WHERE orderId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setInt(2, orderId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public String getFormatDate() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
        System.out.println(dao.getAllOrdersByAcc(2));
    }

}
