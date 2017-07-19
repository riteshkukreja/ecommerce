<%-- 
    Document   : login
    Created on : Jun 29, 2017, 2:49:18 PM
    Author     : Ritesh Kukreja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cdns.jsp" %>
        <title>Login Page</title>
        <style>
            body { margin: 0px;  }
            .selected { color: red; }


            .container { text-align: center; width: 100%; }
            .data {
                width: 40%;
                height: 60%;
                position: absolute;
                left: calc(50% - 20%);
                top: calc(50% - 30%);
                background: rgba(255, 255, 255, .7);
                box-sizing: border-box;
                padding: 30px;
                box-shadow: 0px 0px 20px 0px #000;
                border-radius: 25px;
            }
        </style>
    </head>
    <body>
        
        <div clas="container-fluid">
            <div class="data">
                <h1>Login Page</h1>
        
            <form method="post" action="users/login">
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <div class="input-group">
                    <div class="input-group-addon">@</div>
                    <input type="email" class="form-control" placeholder="Enter Email Address" name="email">
                  </div>
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <div class="input-group">
                    <div class="input-group-addon">!</div>
                    <input type="password" class="form-control" placeholder="Enter Password" name="pass">
                  </div>
                </div>
                <div class="form-group">
                    <a href="./register" class="btn btn-warning btn-lg">Register Here</a>
                    <button type="submit" class="btn btn-primary btn-lg">Login</button>
                </div>
          </form>
            </div>
            
        </div>
    </body>
</html>
