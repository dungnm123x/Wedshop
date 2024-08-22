<%-- 
    Document   : productInsert
    Created on : 8 Mar, 2024, 3:22:50 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            li {
                list-style: none;
            }
        </style>
    </head>
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
        <c:if test="${result!=null}">
            <div class="fs-4 alert ${result==true?"alert-success":"alert-danger"}" role="alert" style="margin-top: 50px;">
                ${mess}
           </div>
        </c:if>
        <div class="w-50 mx-auto mt-5">
            <form action="productManager" method="POST">
                <input name="Service" value="insert" hidden/>
                <div class="row g-3">
                    <div class="col-6">
                        <label class="fw-bold fs-5">Image: </label>
                        <input name="image" class="w-100 p-2" required value="${image}" type="file"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Name: </label>
                        <input name="name" value="${name}" required class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Available </label>
                        <input name="available"
                               value="${available}" 
                               required
                               type="number" min="1" max="1000" required class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Price: </label>
                        <input name="price"
                               value="${price}" 
                               type="number" min="0" max="10000000" required class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Describe: </label>
                        <input name="describe" 
                               required
                               value="${describe}" 
                               class="w-100 p-2"/>
                    </div>
                    <div class="col-6">
                        <label class="fw-bold fs-5">Categories: </label>
                        <c:set var="cid" value="${cid}"/>
                        <select class="w-100 p-2" name="category" require>
                            <c:forEach var="cate" items="${Cates}">
                                <option 
                                    ${cate.getCategoriesId()==category?'selected':''}
                                    value="${cate.getCategoriesId()}">${cate.getCategoriesName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <button type="submit" class="px-3 py-2 text-white bg-success border-0 rounded-lg mt-3">Submit</button>
                <button type="reset" class="px-3 py-2 text-white bg-danger border-0 rounded-lg mt-3">Reset</button>
            </form>
        </div>
    </body>
</html>

