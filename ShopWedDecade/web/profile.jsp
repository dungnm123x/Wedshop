<%-- 
    Document   : Profile
    Created on : 31 Oct, 2023, 8:05:34 AM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="css/homecss.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
        <link rel="stylesheet" href="css/homecss.css"/>
     
        <style>
            /* CSS cho phần giá */
            .price {
                color: #ff0000; /* Màu chữ đỏ */
                font-weight: bold; /* Chữ in đậm */
                font-size: 18px; /* Kích thước chữ */
                text-align: center; /* Căn giữa văn bản */
                margin-bottom: 10px; /* Khoảng cách dưới */
            }

            /* CSS cho nút mua */
            .btn-buy {
                background-color: #007bff; /* Màu nền xanh dương */
                color: #ffffff; /* Màu chữ trắng */
                border: none; /* Không viền */
                padding: 8px 16px; /* Kích thước nút */
                text-align: center; /* Căn giữa văn bản */
                text-decoration: none; /* Không gạch chân */
                display: inline-block;
                font-size: 14px; /* Kích thước chữ */
                cursor: pointer; /* Con trỏ thành hình tay */
            }

            /* Hover effect cho nút mua */
            .btn-buy:hover {
                background-color: #0056b3; /* Màu nền khi hover */
                color: #ffffff; /* Màu chữ khi hover */
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ee4d2d; padding: 1px;">
    <div class="container-fluid" id="navbarsExampleDefault">
        <ul class="navbar-nav ml-auto" style="left: 50px ">
               <li class="nav-item">
                 <i class='bx bx-home-heart' ></i>
                 <a class="nav-link text-white" href="home">Home</a>
               </li>      
             <c:if test="${sessionScope.currentAcc != null}">
                 <c:set var="acc" value="${sessionScope.currentAcc}"/>
               <li class="nav-item">
                  <a class="nav-link text-white" href="profile">Hello: ${acc.getFirstName()} ${acc.getLastName()}</a>
               </li>   
               <li class="nav-item">
                  <a class="nav-link text-white" href="logout">Logout</a>
               </li>      
            </c:if>
            <c:if test="${sessionScope.currentAcc == null}">
               <li class="nav-item">
                 <a class="nav-link text-white" href="login">Login</a>
               </li>      
            </c:if>
            </li>
        </ul>
    </div>
</nav>
    <section>
        <a href="changePassword" class="fs-2 text-decoration-none 
           py-2 px-3
           ms-5 mt-5 bg-success text-white"><i class='bx bx-lock-alt' ></i> Change password</a>
        <div class="w-60 m-auto">
            <h1 class="my-4">Account</h1>
            <div class="">
                <c:if test="${result!=null}">
                    <div class="fs-4 alert ${result==true?"alert-success":"alert-danger"}" role="alert">
                        ${mess}
                    </div>
                </c:if>
                <c:set var="acc" value="${acc}" />
                <form action="profile" method="post">
                    <input name="Service" value="update" hidden>
                    <div class="h-100vh mt-5">
                        <h1 class="fw-bold">Account infomation</h1>
                        <div class="row py-5">
                            <div class="col-md-12">
                                <div class="user-info">
                                    <div class="mt-5">
                                        <h3 class="fw-medium">First name</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="text"
                                                   value="${acc.getFirstName()}"
                                                   name="fname"
                                                   class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="First name">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3 class="fw-medium">Last name</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="text"
                                                   name="lname"
                                                   value="${acc.getLastName()}" class="form-control px-4 py-3 fs-3 rounded-xl"
                                                   placeholder="Last name">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3>Addess</h3>
                                        <div class="input-group mb-3 rounded-xl">
                                            <input type="text"
                                                   name="address"
                                                   value="${acc.getAddress()}" class="form-control px-4 py-3 fs-3" placeholder="Address" aria-label="Username" aria-describedby="basic-addon1">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3>Phone</h3>
                                        <div class="input-group mb-3 rounded-xl">
                                            <input type="text"
                                                   name="phone"
                                                   value="${acc.getPhone()}"  class="form-control px-4 py-3 fs-3" placeholder="Phone" aria-label="Username" aria-describedby="basic-addon1">
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-5">
                                    <button type="submit" class="border-0 px-5 py-4 fs-4 bg-info text-white fw-bold">Update</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
    </section>
</body>
</html>