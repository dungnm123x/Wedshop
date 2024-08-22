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
        <title>Change password</title>
        <link rel="stylesheet" href="./style.css">
        <link rel="stylesheet" href="css/homecss.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer">
        <link rel="stylesheet" href="css/homecss.css"/>
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
                    <input name="Service" value="changePass" hidden>
                    <div class="h-100vh mt-5">
                        <h1 class="fw-bold">Change password</h1>
                        <div class="row py-5">
                            <div class="col-md-12">
                                <div class="user-info">
                                    <div class="mt-5">
                                        <h3 class="fw-medium">Current password</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="text"
                                                   name="currentPass"
                                                   class="form-control px-4 py-3 fs-3 rounded-xl"
                                                   placeholder="current Password">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3 class="fw-medium">New password</h3>
                                        <div class="input-group flex-nowrap">
                                            <input type="text"
                                                   name="newPass"
                                                   class="form-control px-4 py-3 fs-3 rounded-xl" placeholder="Username">
                                        </div>
                                    </div>
                                    <div class="mt-5">
                                        <h3>Confirm password</h3>
                                        <div class="input-group mb-3 rounded-xl">
                                            <input type="text"
                                                   name="confirmPass"
                                                   class="form-control px-4 py-3 fs-3" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1">
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