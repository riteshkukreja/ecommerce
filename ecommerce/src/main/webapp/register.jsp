<%-- 
    Document   : register
    Created on : Jul 2, 2017, 11:11:46 PM
    Author     : Ritesh Kukreja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <%@include file="cdns.jsp" %>
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
                <h1>Registration Page</h1>
        
            <form method="post" action="users/register">
                <div class="form-group">
                  <label for="exampleInputEmail1">Email address</label>
                  <div class="input-group">
                    <input type="email" class="form-control" placeholder="Enter Email Address" name="email">
                  </div>
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Password</label>
                  <div class="input-group">
                    <input type="password" class="form-control" placeholder="Enter Password" name="pass">
                  </div>
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Name</label>
                  <div class="input-group">
                    <input type="text" class="form-control" name="name" placeholder="Enter Name">
                  </div>
                </div>
                <div class="form-group">
                    <a href="./login" class="btn btn-warning btn-lg">Login Here</a>
                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
                </div>
          </form>
            </div>
            
        </div>
    </body>
</html>
