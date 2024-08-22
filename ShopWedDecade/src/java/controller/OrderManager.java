/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.IConstant;
import dal.DAOOrder;
import dal.DAOOrderItem;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Vector;
import model.Order;
import model.OrderItem;


public class OrderManager extends HttpServlet {

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
        DAOOrder daoOrder = new DAOOrder();
        DAOOrderItem daoOrderItem = new DAOOrderItem();
        HttpSession session = request.getSession();
        String service = request.getParameter("Service");
         if (session.getAttribute("isAdmin") == null) {
            response.sendRedirect("login");
            return;
        }
        if (service == null) {
            List<Order> orders = daoOrder.getAllOrders();
            request.setAttribute("orders", orders);
            request.setAttribute("listStatus", IConstant.STATUS);
            request.getRequestDispatcher("orderManager.jsp").forward(request, response);
            return;
        }
        if (service.equals("changeStatus")) {
          String status = request.getParameter("status");
          String orderId = request.getParameter("oid");
          boolean hasUpdate = daoOrder.updateOrderStatus(Integer.parseInt(orderId), status);
          response.sendRedirect("orderManager");
        }
        if(service.equals("detail")) {
          String orderId = request.getParameter("oid");
           Vector<OrderItem> orderItems = daoOrderItem.getAllOrdersByOrderId(
                   Integer.parseInt(orderId)
           );
           request.setAttribute("orderItems", orderItems);
           request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
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
        DAOOrder dao = new DAOOrder();
        request.getRequestDispatcher("views/orders/orderManager.jsp").forward(request, response);
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
