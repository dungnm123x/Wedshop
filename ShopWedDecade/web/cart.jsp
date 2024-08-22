<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<%@page import="constant.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="css/cartcss.css"/>
    </head>
    <body>
        <%@include file="/includes/navbar.jsp"%>
        <div class="container">
            <table class="table table-loght">
                <thead>
                    <tr>
                        <th scope="col">Name</th> 
                        <th scope="col">Price</th>
                        <th scope="col">Buy Now</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set value="0" var="totalBill"/>
                    <c:forEach var="entry" items="${sessionScope}">
                        <c:choose>
                            <c:when test="${fn:startsWith(entry.key, 'cart-')}">
                                <c:set var="pro" value="${entry.value}" />
                                <c:set 
                                    value="${totalBill + (pro.getListPrice() * pro.getQuantity())}" var="totalBill"/>
                                <tr>
                                    <td>${pro.getProductName()}</td> 
                                    <td>${pro.getPriceSubTotal()}</td>
                                    <td>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <form action="" method="post" class="form-inline">
                                                    <input type="hidden" name="id" value="1" class="form-input">
                                                    <div class="form-group d-flex justify-content-between">
                                                        <a class="btn btn-sm btn-incre" href="?Service=add&pid=${pro.getProductId()}">
                                                            <i class="fas fa-plus-square" ></i>
                                                        </a>
                                                        <input type="text" name="quantity" value="${pro.getQuantity()}" class="form-control" readonly>
                                                        <a class="btn btn-sm btn-decre" href="?Service=minus&pid=${pro.getProductId()}">
                                                            <i class="fas fa-minus-square" ></i>
                                                        </a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </td>
                                    <td><a class="btn btn-sm btn-danger" 
                                           href="cart?Service=remove&pid=${product.getProductId()}">Remove</a></td>
                                </tr>
                            </c:when>
                        </c:choose>
                    </c:forEach>       
                </tbody>
            </table>
        </div>
                    <fmt:formatNumber type="number" value="${totalBill}" pattern="#,##0" var="formatBill"/>
        <div class="d-flex py-3"><h3>Total:${formatBill}vnd</h3>
            <a class="mx-3 btn btn-primary" href="?Service=checkout">Check Out</a>
        </div>
        <%@include file="/includes/footer.jsp"%>
    </body>
</html>
