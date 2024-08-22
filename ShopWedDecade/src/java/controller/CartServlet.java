/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DAOOrder;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.Account;
import model.Order;
import model.Product;

/**
 *
 * @author HP
 */
@WebServlet(name="CartServlet", urlPatterns={"/cart"})
public class CartServlet extends HttpServlet {
    DAOProduct proDao = new DAOProduct();
    DAOOrder orderDao = new DAOOrder();
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //create constant ship price
            HttpSession session = request.getSession();

            String service = request.getParameter("Service");

            if (service == null) {
                java.util.Enumeration em = session.getAttributeNames();
                int numOrder = 0;
                while (em.hasMoreElements()) {
                    String id = em.nextElement().toString();
                    if (id.startsWith("cart")) {
                        Product pro_session = (Product) session.getAttribute(id);
                        numOrder += pro_session.getQuantity();
                    }
                }
                session.setAttribute("numOrder", numOrder);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }
            //if add to cart check have cart exist before
            if (service.equals("addToCart")) {
                 String pid_raw = request.getParameter("pid");
                String key = "cart-" + pid_raw;
                //san pham luu o dang key va value
                int pid = Integer.parseInt(pid_raw);
                int quantity = 1;

                Product p = (Product) session.getAttribute(key);
                Product p_root = proDao.getProductById(pid);
                //IF DONT EXIST will store to the session a new product
                if (p == null) {
                    Product pAdd = new Product(
                            pid,
                            p_root.getProductImg(),
                            p_root.getProductName(),
                            quantity,
                            p_root.getListPrice()
                    );
                    session.setAttribute(key, pAdd);
                } else {
                    //IF HAVE EXIST get old product in session and change quantity
                    Product pAdd = new Product(
                            pid,
                            p_root.getProductImg(),
                            p_root.getProductName(),
                            p.getQuantity() + quantity,
                            p_root.getListPrice()
                    );
                    session.setAttribute(key, pAdd);
                }
                response.sendRedirect("cart");
                return;
            }

            if (service.equals("add")) {
                String pid_raw = request.getParameter("pid");
                int pid = Integer.parseInt(pid_raw);
                String key = "cart-" + pid_raw;
                //sp root
                Product p_root = proDao.getProductById(pid);
                Product p = (Product) session.getAttribute(key);
                int quantity = 0;
                //IF DONT EXIST
                if (p != null) {
                    if ((p.getQuantity() + 1) == p_root.getQuantity()) {
                        quantity = p_root.getQuantity();
                    } else {
                        quantity = p.getQuantity() + 1;
                    }
                    // create a new p and set in old key
                    Product pAdd = new Product(
                            pid,
                            p_root.getProductImg(),
                            p_root.getProductName(),
                            quantity,
                            p_root.getListPrice()
                    );
                    session.setAttribute(key, pAdd);
                }
                response.sendRedirect("cart");
            }

            if (service.equals("minus")) {
                String pid_raw = request.getParameter("pid");
                int pid = Integer.parseInt(pid_raw);
                String key = "cart-" + pid_raw;
                Product p = (Product) session.getAttribute(key);
                Product p_root = proDao.getProductById(pid);
                //IF DONT EXIST
                if (p != null) {
                    // create a new p and set in old key
                    //check is delete
                    if ((p.getQuantity() - 1) == 0) {
                        session.removeAttribute(key);
                    } else {
                        Product pAdd = new Product(
                            pid,
                            p_root.getProductImg(),
                            p_root.getProductName(),
                            p.getQuantity() - 1,
                            p_root.getListPrice()
                    );
                        session.setAttribute(key, pAdd);
                    }
                }
                response.sendRedirect("cart");
            }
            if (service.equals("remove")) {
                String pid_raw = request.getParameter("pid");
                String key = "cart-" + pid_raw;
                session.removeAttribute(key);
                response.sendRedirect("cart");
            }

            if (service.equals("checkout")) {
                if (session.getAttribute("currentAcc") != null) {
                    Vector<Product> listItem = new Vector<>();
                    Account acc = (Account) session.getAttribute("currentAcc");
                    java.util.Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        //when get from session id is cart-id
                        String id = em.nextElement().toString();
                        if (id.startsWith("cart")) {
                            //add product into list before remove in session
                            Product pro_session = (Product) session.getAttribute(id);
                            //get product in database but the quantity is number want to order
                            listItem.add(pro_session);
                            session.removeAttribute(id);
                        }
                    }
                    
                    Order newOrder = new Order(
                            acc.getAccountId(),
                            "Chờ xử lý"
                    );
                    if (listItem.size() > 0) {
                        int orderId = orderDao.insertOrder(newOrder);
                        if(orderId > 0) {
                         orderDao.insertOrderItem(listItem, orderId);
                        }
                    }
                    response.sendRedirect("cart");
                } else {
                    response.sendRedirect("login");
                }
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
