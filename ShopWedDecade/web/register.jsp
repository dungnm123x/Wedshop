
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart register</title>
        <%@include file="includes/head.jsp" %>
    </head>
    <body>
        <a href="home" class="fs-3 text-danger text-decoration-none m-5">
          <i class='bx bx-home' ></i> 
          Back to home
        </a>
        <div class="container" >
            <div class="card w-50 mx-auto my-5" >
                <div class="card-header text-center" > User Register</div> 
                <div class="card-body">
                    <form action="register" method="post">
                         <div class="form-group">
                            <label>First name</label>
                            <input type="text" 
                                   value="${fname}"
                                   class="form-control" name="fname" placeholder="Enter Your fist name" required>
                        </div>
                        <div class="form-group">
                            <label>Last Name</label>
                            <input type="text" 
                                   value="${lname}"
                                   class="form-control" name="lname" placeholder="Enter Your last name" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" 
                                   value="${email}"
                                   class="form-control" name="email" placeholder="Enter Your Email" required>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="phone" 
                                   value="${phone}"
                                   class="form-control" name="phone" placeholder="Enter Your Phone" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" name="password" placeholder="******" required>
                        </div>
                        <div class="form-group">
                            <label>Confirm password</label>
                            <input type="password" class="form-control" name="confirmPassword" placeholder="******" required>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                        <h3 class="text-danger text-center">${mess}</h3>
                    </form>
                    <a href="login">Login</a>
                    <p style="color: red"> ${msg}</p>
                </div>
            </div>
        </div>
        <%@include file="includes/footer.jsp"%>
    </body>
</html>
