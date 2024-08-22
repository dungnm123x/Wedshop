<%-- 
    Document   : productManage
    Created on : 17 Jan, 2024, 3:17:10 PM
    Author     : HP
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@page import="dal.*" %>
<%@page import="constant.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product Manage</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <style>
    </style>
    <body>
      <nav class="navbar navbar-expand-lg navbar-light py-3" style="background-color: #ee4d2d; padding: 1px;">
            <div class="container-fluid" id="navbarsExampleDefault">
                <ul class="navbar-nav ml-auto" style="left: 50px ">
                    <c:if test="${sessionScope.isAdmin}">
                        <li class="nav-item fw-bold">
                            <a class="nav-link text-white" href="productManager">Manager Product</a>
                        </li>      
                    </c:if>
                    <li class="nav-item fw-bold">
                        <c:if test="${sessionScope.currentAcc != null}">
                            <c:set var="acc" value="${sessionScope.currentAcc}"/>
                        <li class="nav-item fw-bold">
                            <a class="nav-link text-white" href="logout">Hello: ${acc.getFirstName()} ${acc.getLastName()}</a>
                        </li>   
                        <li class="nav-item fw-bold">
                            <a class="nav-link text-white" href="logout">Logout</a>
                        </li>      
                    </c:if>
                    <c:if test="${sessionScope.currentAcc == null}">
                        <li class="nav-item fw-bold">
                            <a class="nav-link text-white" href="login">Login</a>
                        </li>      
                    </c:if>
                    </li>
                </ul>
            </div>
        </nav>
        <section>
            <div class="row h-100">
                <div class="col-md-12 h-100 manage-product">
                    <h1 class="fw-bold my-4">Order</h1>
                    <a class="mx-auto text-white py-3 mt-5 fs-3 px-4 rounded-md text-white fw-bold"
                           style="width: fit-content; background-color: #ee4d2d;" 
                           href="productManager?Service=addMore">Add more product +
                    </a>
                    <a class="mx-auto text-white ms-3 py-3 mt-5 fs-3 px-4 rounded-md text-white fw-bold"
                           style="width: fit-content; background-color: #ee4d2d;" 
                           href="orderManager">Manage Order
                    </a>
                    <a class="mx-auto ms-3 py-3 mt-5 fs-3 px-4 rounded-md text-white fw-bold"
                          style="width: fit-content; background-color: #ee4d2d;" 
                           href="productManager">Get all product
                    </a>
                  
                    <c:if test="${result!=null}">
                        <div class="fs-4 mt-5 alert ${result==true?"alert-success":"alert-danger"}" role="alert">
                            ${mess}
                        </div>
                    </c:if>
                    <div class="mt-5">
                        <div class="row text-white px-3 py-4 fs-4 mx-auto" style="background-color: #ee4d2d;">
                            <div class="col-1">
                                <div class="d-flex align-items-center">
                                    <span>id</span>
                                </div>
                            </div>
                            <div class="col-3 col-md-2">
                                <div class="d-flex align-items-center">
                                    <span>Email</span>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="d-flex align-items-center">
                                    <span>Phone</span>
                                </div>
                            </div>
                            <div class="col-2 col-md-2">
                                <div class="d-flex align-items-center">
                                    <span>Order date</span>
                                </div>
                            </div>
                        </div>
                     
                        <div class="row gx-5 gap-sm-0 list_manage-product">
                            <c:forEach var="order" items="${requestScope.orders}">
                                <c:set var="acc" value="${order.getAcc()}"/>
                                <div class="col-12 col-sm-4 col-md-12 mt-5 transition-item transition-item_${product.productId}">
                                    <div
                                        class="row position-relative fs-4 px-3 py-4 d-flex align-items-center justify-content-between bg-white rounded-lg border">
                                        <div class="col-sm-12 col-md-1">${order.getOrderId()}</div>
                                        <div class="col-sm-12 col-md-2">${acc.getEmail()}</div>
                                        <div class="col-sm-12 col-md-2">${acc.getPhone()}</div>
                                        <div class="col-sm-12 col-md-2">${order.getOrderDate()}</div>
                                        <div class="col-md-2 col-sm-6">${product.getStatus()}</div>
                                        <div class="col-md-1 col-sm-6">
                                            <a href="orderManager?Service=detail&oid=${order.getOrderId()}" class="text-info text-decoration-none">Detail</a>
                                        </div>
                                        <div class="col-sm-6 col-md-2">
                                            <div class="text-center d-flex">
                                                <select class="border-0 text-danger" onchange="changeStatus([this, ${order.getOrderId()}])">
                                                <c:forEach items="${listStatus}" var="status">
                                                    <option value="${status}" ${(order.getStatus()==status)?"selected":""}>${status}</option>
                                                </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
         <script type="text/javascript">
            function changeStatus(param) {
                let url = "orderManager?Service=changeStatus&status=" + param[0].value + "&oid=" + param[1]
                window.location = url
            }
        </script>
    </body>
</html>
