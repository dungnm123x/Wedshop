/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.Account;

/**
 *
 * @author HP
 */
public class DAOAccount extends DBContext {
    public Vector<Account> getAllAccount() {
        String sql = "Select * from Account";
        Vector<Account> listAccount = new Vector<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                listAccount.add(new Account(
                        rs.getInt("accountId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listAccount;
    }

    public boolean deleteAccount(int accountId) {
        String sql = "DELETE FROM [dbo].[Account]\n"
                + "      WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean createAccount(Account acc) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([firstName]\n"
                + "           ,[lastName]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[isAdmin])\n"
                + "     VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getFirstName());
            st.setString(2, acc.getLastName());
            st.setString(3, acc.getPassword());
            st.setString(4, acc.getEmail());
            st.setString(5, acc.getPhone());
            st.setBoolean(6, acc.isIsAdmin());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
   public Account validate(String email, String password) {
        String sql = "Select * from Account where email = ? and password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("accountId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
   public Account getAccountById(int accountId) {
        String sql = "Select * from Account where accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("accountId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
   public Account getAccountByEmail(String eamil) {
        String sql = "Select * from Account where email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, eamil);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new Account(
                        rs.getInt("accountId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getBoolean("isAdmin"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateAccount(Account acc) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[phone] = ?\n"
                + " WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getFirstName());
            st.setString(2, acc.getLastName());
            st.setString(3, acc.getAddress());
            st.setString(4, acc.getPhone());
            st.setInt(5, acc.getAccountId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean updatePassword(Account acc) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [password] = ?"
                + " WHERE accountId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getPassword());
            st.setInt(2, acc.getAccountId());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        System.out.println(dao.validate("cus1@example.com", "123"));
    }
}
