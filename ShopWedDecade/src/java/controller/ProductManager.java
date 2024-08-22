/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import constant.IConstant;
import dal.DAOCategory;
import dal.DAOProduct;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.Product;

/**
 *
 * @author HP
 */
@WebServlet(name = "ProductManager", urlPatterns = {"/productManager"})
@MultipartConfig
public class ProductManager extends HttpServlet {
    DAOCategory cateDao = new DAOCategory();
    DAOProduct daoPro = new DAOProduct();
    DAOCategory daoCate = new DAOCategory();
 
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
            out.println("<title>Servlet ProductManager</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManager at " + request.getContextPath() + "</h1>");
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
        String service = request.getParameter("Service");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        if (session.getAttribute("isAdmin") == null) {
            response.sendRedirect("login");
            return;
        }
        if (service == null) {
            service = "manage";
        }
        if (service.equals("manage")) {
            Vector<Product> listProduct = daoPro.getAllProduct();
            request.setAttribute("listProduct", listProduct);
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }

        if (service.equals("delete")) {
            String proId_raw = request.getParameter("productId");
            boolean hasDelete = daoPro.deleteProduct(Integer.parseInt(proId_raw));
            if (hasDelete) {
                out.println();
            }
        }
        if(service.equals("search")) {
            String searchTxt = request.getParameter("searchTxt");
            Vector<Product> listProduct = daoPro.searchProduct(searchTxt);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("searchTxt", searchTxt);
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }
        if (service.equals("addMore")) {
            request.setAttribute("Cates", cateDao.getAllCategory());
            request.getRequestDispatcher("productInsert.jsp").forward(request, response);
        }
        if(service.equals("remove")) {
            String proId = request.getParameter("prodId");
            boolean hasDelete = daoPro.deleteProduct(Integer.parseInt(proId));
            if(hasDelete) {
             request.setAttribute("mess", "delete success");
            } else {
             request.setAttribute("mess", "delete error");
            }
            request.setAttribute("result", hasDelete);
            request.setAttribute("listProduct", daoPro.getAllProduct());
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }
        if (service.equals("update")) {
            String proId = request.getParameter("prodId");
            Product pro = daoPro.getProductById(Integer.parseInt(proId));
            request.setAttribute("Cates", cateDao.getAllCategory());
            request.setAttribute("product", pro);
            request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
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
            
            String image = request.getParameter("image");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String available = request.getParameter("available");
            String describe = request.getParameter("describe");
            String price = request.getParameter("price");
            if(!image.startsWith("images/")) {
                   image="images/"+image;
                }
        if (service.equals("insert")) {
            boolean isAdd = false;
            String mess;
            System.out.println(name.isEmpty() +"/ "+ image.isEmpty() +"/ "+ category.isEmpty()
                    + "/ "+ available.isEmpty()+
                    "/ "+ price.isEmpty());
            if (name.isEmpty() || image.isEmpty() || category.isEmpty()
                   || available.isEmpty()
                    || price.isEmpty()) {
                mess = "please fill field empty";
            } else {
                Product pro = new Product(
                        Integer.parseInt(category),
                        Integer.parseInt(available), 
                        name, image,
                        Double.parseDouble(price),
                        describe);
                
                isAdd = daoPro.insertProduct(pro);
                if(isAdd) {
                  mess = "Add product success";
                } else {
                  mess = "Add error";
                }
            }
            request.setAttribute("result", isAdd);
            request.setAttribute("mess", mess);
            returnInputValue(request, response, image, name, category, available, price, describe);
            request.setAttribute("listCate", cateDao.getAllCategory());
            request.getRequestDispatcher("productInsert.jsp").forward(request, response);
        }
        if(service.equals("update")) {
        boolean isUpdate = false;
            String productId = request.getParameter("pid");
            String mess;
             if (name.isEmpty() || image.isEmpty() || category.isEmpty()
                     || available.isEmpty()
                    || price.isEmpty()) {
                mess = "please fill field empty";
            } else {
                Product pro = new Product(
                        Integer.parseInt(productId),
                        Integer.parseInt(category),
                        Integer.parseInt(available), 
                        name, image, Double.parseDouble(price), describe);
                isUpdate = daoPro.updateProduct(pro);
                if(isUpdate) {
                  mess = "Update product success";
                } else {
                  mess = "Update product error";
                }
            }
            request.setAttribute("result", isUpdate);
            request.setAttribute("mess", mess);
            returnInputValue(request, response, image, name, category, available, price, describe);
            request.setAttribute("listCate", cateDao.getAllCategory());
            request.getRequestDispatcher("productUpdate.jsp").forward(request, response);
        }
    }
    
    public void returnInputValue(HttpServletRequest request, 
            HttpServletResponse response, String image ,
                    String name, String category,
                    String available, String price, String describe)
            throws ServletException, IOException {
           request.setAttribute("image", image);
           request.setAttribute("name", name);
           request.setAttribute("category", category);
           request.setAttribute("available", available);
           request.setAttribute("price", price);
           request.setAttribute("describe", describe);
           request.setAttribute("Cates", cateDao.getAllCategory());
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
        DAOProduct daoPro = new DAOProduct();
        System.out.println(daoPro.deleteProduct(8));
    }
}
