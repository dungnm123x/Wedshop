/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAOCategory;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Vector;
import model.Category;
import model.Product;

/**
 *
 * @author HP
 */
public class HomeServlet extends HttpServlet {
    DAOCategory dCate = new DAOCategory();
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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
        DAOProduct daoPro = new DAOProduct();
        DAOCategory daoCate = new DAOCategory();
        String service = request.getParameter("Service");
        if (service == null) {
            request.setAttribute("products", daoPro.getAllProduct());
            request.setAttribute("cates", daoCate.getAllCategory());
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }
        if(service.equals("filter")) {
            String cid = request.getParameter("cateId");
            request.setAttribute("cates", daoCate.getAllCategory());
            request.setAttribute("cid", cid);
            request.setAttribute("products", daoPro.getProductByCid(Integer.parseInt(cid)));
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }
        if(service.equals("sort")) {
            String sortValue = request.getParameter("sortPrice");
            request.setAttribute("cates", daoCate.getAllCategory());
            request.setAttribute("sortValue", sortValue);
            request.setAttribute("products", daoPro.getProductBySort(sortValue));
            request.getRequestDispatcher("home.jsp").forward(request, response);
            return;
        }
         if(service.equals("search")) {
            String searchTxt = request.getParameter("txt");
            request.setAttribute("products", daoPro.searchProduct(searchTxt));
            request.setAttribute("cates", daoCate.getAllCategory());
            request.setAttribute("txt", searchTxt);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    public String convertPrice(String price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("VND"));
        return currencyFormatter.format(Double.parseDouble(price));
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
        processRequest(request, response);
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

    public static void main(String[] args) {
    }
}
