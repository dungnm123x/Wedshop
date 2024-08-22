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
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {

    DAOAccount daoAcc = new DAOAccount();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Profile at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("currentAcc") != null) {
          Account acc = (Account) session.getAttribute("currentAcc");
          request.setAttribute("acc", acc);
          request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
           response.sendRedirect("login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("Service");
        HttpSession session = request.getSession();
        if(session.getAttribute("currentAcc") == null) {
         response.sendRedirect("login");
         return;
        }
        Account acc = (Account) session.getAttribute("currentAcc");
        if (service.equals("update")) {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String mess;
            boolean hasUpdate = false;
            if (fname.isEmpty() || lname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                mess = "require fill all";
                responseMess(request, response, hasUpdate, mess, "profile.jsp");
                return;
            }
            if (!Validate.isValidPhoneNumber(phone)) {
                mess = "phone invalid";
                responseMess(request, response, hasUpdate, mess, "profile.jsp");
                return;
            }

            Account newAcc = new Account(acc.getAccountId(), fname, lname, address, phone);
            hasUpdate = daoAcc.updateAccount(newAcc);
            if (hasUpdate) {
                mess = "update success";
                session.setAttribute("currentAcc", newAcc);
            } else {
                mess = "update failed";
            }
            responseMess(request, response, hasUpdate, mess, "profile.jsp");
            return;
        }
        if (service.equals("changePass")) {
            String currentPass = request.getParameter("currentPass");
            String newPass = request.getParameter("newPass");
            String confirmPass = request.getParameter("confirmPass");
            Account validPass = daoAcc.validate(acc.getEmail(), currentPass);
            String mess;
            if (validPass == null) {
                mess = "current password invalid";
                responseMess(request, response, false, mess, "changePassword.jsp");
                return;
            }
            if (!newPass.equals(confirmPass)) {
                mess = "confirm password not same new password";
                responseMess(request, response, false, mess, "changePassword.jsp");
                return;
            }
            acc.setPassword(newPass);
            boolean isUpdate = daoAcc.updatePassword(acc);
             if (isUpdate) {
                mess = "update success";
            } else {
                mess = "update failed";
            }
            responseMess(request, response, isUpdate, mess, "changePassword.jsp");
        }
    }

    public void responseMess(HttpServletRequest request,
            HttpServletResponse response, boolean isUpdate, String mess, String path)
            throws ServletException, IOException {
        request.setAttribute("result", isUpdate);
        request.setAttribute("mess", mess);
        request.getRequestDispatcher(path).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
