/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Validate;

/**
 *
 * @author HP
 */
@WebServlet(name="Register", urlPatterns={"/register"})
public class Register extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        DAOAccount daoAcc = new DAOAccount();
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String mess;
        
        if(fname.isEmpty() || lname.isEmpty() || email.isEmpty() || phone.isEmpty() 
                || password.isEmpty() || confirmPassword.isEmpty()) {
           mess = "Please fill all of field";
           returnInputValue(request, response, fname, lname, "", phone, mess);
           return;
        }
        if(daoAcc.getAccountByEmail(email) != null) {
           mess = "This account have exist!";
           returnInputValue(request, response, fname, lname, "", phone, mess);
           return;
        }
        if(!Validate.isValidPhoneNumber(phone)) {
          mess = "Phone number invalid!";
          returnInputValue(request, response, fname, lname, email, "", mess);
          return;
        }
        if (!password.equals(confirmPassword)) {
            mess = "Password and Confirm Password do not match.";
            returnInputValue(request, response, fname, lname, email, phone, mess);
            return;
        }
        Account acc = new Account(fname, lname, password, email, phone, false);
        boolean isCreate = daoAcc.createAccount(acc);
        if(!isCreate) {
            mess = "Create account unsuccess";
            returnInputValue(request, response, fname, lname, email, phone, mess);return;
        } else {
         session.setAttribute("currentAcc", acc);
         response.sendRedirect("home");
        }
    }
    public void returnInputValue(HttpServletRequest request, 
            HttpServletResponse response, String fname, String lname, String email, 
            String phone, String mess)
    throws ServletException, IOException {
      request.setAttribute("fname", fname);
      request.setAttribute("lname", lname);
      request.setAttribute("email", email);
      request.setAttribute("phone", phone);
      request.setAttribute("mess", mess);
      request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
