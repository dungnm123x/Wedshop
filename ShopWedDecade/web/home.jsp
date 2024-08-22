
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to shopping cart</title>
        <link rel="stylesheet" href="css/homecss.css"/>
        <%@include file="includes/navbar.jsp"%>
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
        <%@include file="includes/head.jsp"%>
        <section class="jumbotron text-center">
            <div class="container">
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-80" src="logo/logo1.png" style="object-fit: cover; width: 100%; height: 100vh;">
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="container">
            <div class="row justify-content-between">
                <div class="col-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="homes">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Category</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                        </ol>
                    </nav>
                </div>
                
                <div class="col-2">
                    <select id="select-sort" onchange="sendSortOption()">
                        <option value="-1">Select your option</option>
                        <option value="ASC" ${sortValue.equals("ASC")?'selected':''}>Sort by price Asc</option>
                        <option value="DESC" ${sortValue.equals("DESC")?'selected':''}>Sort by price Desc</option>
                    </select>
                </div>
            </div>
        </div>
        <div>
            
        </div>
        <div class="container">
            <div class="row">
                <!-- Menu left -->
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${cates}" var="c">
                                <li class="list-group-item text-white">
                                   <a class="${cid == c.getCategoriesId()?'text-danger':''}"
                                       href="?Service=filter&cateId=${c.getCategoriesId()}">
                                    ${c.getCategoriesName()}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${products}" var="pro">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card w-100">
                                    <img class="card-img-top mb-3 img-fluid" 
                                         src="${pro.getProductImg()}" alt="Card image cap">
                                    <div class="card-body">
                                        <h6 class="card-title show_txt"><a href="#" title="View Product">${pro.getProductName()}</h6></h4>
                                        <div class="row">
                                            <div class="col">
                                                <div class="d-flex align-items-center">
                                                    <p class="btn btn-danger">${pro.getPriceFormat()}vnd</p>
                                                    <p class="btn">
                                                        <a class="btn btn-sm btn-primary" href="cart?Service=addToCart&pid=${pro.getProductId()}">Buy Now</a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>       
        <%@include file="includes/footer.jsp"%>
<script>
    function sendSortOption() {
      var selectElement = document.getElementById("select-sort");
      var selectedValue = selectElement.value;
    
      if (selectedValue !== -1) {
        var url = "?Service=sort&sortPrice="+selectedValue;
        window.location = url;
      }
    }
</script>
    </body>
</html>
