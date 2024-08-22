<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.Normalizer" %>
<%@page import="java.util.regex.Pattern" %>
<%@page import="dal.*" %>
<%@page import="entity.*" %>
<%@page import="java.util.*" %>
<%@page import="constant.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="assets/content.css"/>
        <link rel="stylesheet" href="assets/bootstrap.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
        <link rel="stylesheet" href="./css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            li {
                list-style: none
            }
        </style>
    </head>
    <body>
        <section>
             <%@include file="/includes/navbar.jsp"%>
                <div class="row border-top ">
                    <div class="col-md-12 row border-top py-3">
                        <div class="col-md-12 pe-5">
                            <div class="row order-item py-5">
                                    <div class="row bg-black text-white rounded-md py-3">
                                            <div class="col-md-3 fs-3 fw-bold">Product image</div>
                                            <div class="col-md-2 fs-3 fw-bold">Name</div>
                                            <div class="col-md-1 fs-3 fw-bold">Price</div>
                                            <div class="col-md-2 fs-3 fw-bold">Quantity</div>
                                            <div class="col-md-1 fs-3 fw-bold">Subtotal</div>
                                    </div>
                                <c:forEach var="order" items="${myOrder}">
                                    <div class="row fs-3 align-items-center mt-5 fw-bold py-2">
                                        <div class="d-flex align-items-center justify-content-between">
                                            <p class="fs-5">Orderdate: ${order.getOrderDate()}</p>
                                            <p class="fs-5">Status: ${order.getStatus()}</p>
                                        </div>
                                    <c:forEach var="orderItems" items="${order.getOrderItems()}">
                                        <c:set var="pro" value="${orderItems.getProduct()}"/>
                                        <div class="row border align-items-center rounded-sm">
                                            <div class="col-md-3">
                                                <img src="${pro.getProductImg()}" alt="" style="width: 150px;" class="rounded-lg object-fit-cover">
                                            </div>
                                            <div class="col-md-2">
                                                <h3 class="fw-bold">${pro.getProductName()}</h3>
                                            </div>
                                            <div class="col-md-1 text-center">
                                                <span class="fs-4">${orderItems.getPriceFormat()} vnd</span>
                                            </div>
                                            <div class="col-md-2">
                                                <div class="box-input" >
                                                    <div class="box-input d-flex">
                                                        <input type="text" value="${orderItems.getQuantity()}"
                                                               class="py-3 fs-3 w-25 border outline-0 text-center"
                                                               id="numberValue" readonly />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-1 text-center">
                                                <span class="fs-4">${orderItems.getPriceSubTotal()} vnd</span>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                              </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
