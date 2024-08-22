/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Account {
    private int accountId;
    private String firstName, lastName, password, email, address, phone;
    private boolean isAdmin;

    public Account() {
    }

    public Account(int accountId, String firstName, String lastName, String password,
            String email, String address, String phone, boolean isAdmin) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public Account(String firstName, String lastName, String password,
            String email, String phone, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.isAdmin = isAdmin;
    }

    public Account(int accountId, String firstName, String lastName,
           String address, String phone) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + ", email=" + email + ", address=" + address + ", phone=" + phone + ", isAdmin=" + isAdmin + '}';
    }
}
