
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart login</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <a href="home" class="fs-3 text-danger text-decoration-none m-5">
          <i class='bx bx-home' ></i> 
          Back to home
        </a>
        <div class="container" >
            <div class="card w-50 mx-auto my-5" >
                <div class="card-header text-center" > User Login</div> 
                <div class="card-body">
                    <form action="login" method="post">
                        <div class="form-group">
                            <label>Email Address</label>
                            <input type="email" class="form-control" name="login-email" placeholder="Enter Your Email" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="login-password" placeholder="******" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                        <h3 class="text-danger text-center">${mess}</h3>
                    </form>
                    <a href="register">Create your account</a>
                    <p style="color: red"> ${msg}</p>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp"%>

    </body>
</html>
