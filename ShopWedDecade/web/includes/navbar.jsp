<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ee4d2d; padding: 1px;">
    <div class="container-fluid" id="navbarsExampleDefault">
        <ul class="navbar-nav ml-auto" style="left: 50px ">
            <c:if test="${sessionScope.isAdmin}">
               <li class="nav-item">
                <a class="nav-link" href="productManager">Manager Product</a>
               </li>      
            </c:if>
             <li class="nav-item">
             <c:if test="${sessionScope.currentAcc != null}">
                 <c:set var="acc" value="${sessionScope.currentAcc}"/>
               <li class="nav-item">
                  <a class="nav-link" href="profile">Hello: ${acc.getFirstName()} ${acc.getLastName()}</a>
               </li>   
               <li class="nav-item">
                  <a class="nav-link" href="logout">Logout</a>
               </li>      
            </c:if>
            <c:if test="${sessionScope.currentAcc == null}">
               <li class="nav-item">
                 <a class="nav-link" href="login">Login</a>
               </li>      
            </c:if>
            </li>
        </ul>
    </div>
</nav>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #ee4d2d;padding: 3px;">    
    <!-- Left side of the navbar -->
    <div class="container">
        <a class="navbar-brand" href="home">
            <img src="logo/logo2.png" style="height: 80px; margin-right: 10px;"> 
            <span style="font-size: larger;">Decade Shopping</span>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </div>
    <!-- Right side of the navbar -->
    <div class="col-md-8" id="navbarsExampleDefault">

        <form action="home" class="form-inline my-2 my-lg-0">
            <div class="input-group input-group-sm">
                <input name="txt" type="text" 
                       value="${txt}"
                       class="form-control" 
                       style="width: 850px;" aria-label="Search" placeholder="Search...">
                <div class="input-group-append">
                    <button name="Service" value="search" type="submit" class="btn btn-secondary btn-number">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
            </div> 
            <a class="btn btn-success btn-sm ml-3 mt-4" href="cart">
                <i class="fa fa-shopping-cart"></i> Cart
                <span class="badge badge-light">${sessionScope.numOrder}</span>
            </a>
            
            <a class="btn btn-success btn-sm ml-3 mt-4" href="myorder">
                Ordered
            </a>
        </form> 
</nav>

