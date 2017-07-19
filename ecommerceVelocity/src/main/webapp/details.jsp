<%-- 
    Document   : details
    Created on : Jul 2, 2017, 10:32:46 PM
    Author     : Ritesh Kukreja
--%>

<%@page import="com.ritesh.ecommerce.dao.Payment"%>
<%@page import="com.ritesh.ecommerce.dao.Address"%>
<%@page import="com.ritesh.ecommerce.dao.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% User user = (User)request.getAttribute("user"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cdns.jsp" %>
        <title><%= user.getName() %></title>
        <style>
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        
        <div class="container-fluid">
            <h1><%= user.getName() %></h1>

            <h3>Email Address</h3>
            <p><%= user.getEmail() %></p>

            <h3>Address</h3>
            
            
            <ul id="addresses">
                <button class="btn btn-secondary btn-lg" data-toggle="modal" data-target="#myAdd">+ Add New</button>
                <div class="btn-group" id="addHere" data-toggle="buttons"></div>
            </ul>

            <h3>Payments</h3>
            
            <ul id="payments">
                <button class="btn btn-secondary btn-lg" data-toggle="modal" data-target="#myPayment">+ Add New</button>
                <div class="btn-group" id="pay_here" data-toggle="buttons"></div>
            </ul>
            
            <div id="myPayment" class="modal fade" role="dialog">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Add Payment details</h4>
                    </div>
                    <div class="modal-body">
                        <input type='text' class="form-control" id='payment_input' placeholder='Enter Payment Method'>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button onclick="pay_btn_click()" data-dismiss="modal" type="button" class="btn btn-primary">Add</button>
                    </div>
                  </div>

                </div>
            </div>
            
            <div id="myAdd" class="modal fade" role="dialog">
                <div class="modal-dialog">

                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Add Payment details</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>Street Name</label>
                            <input type='text' id='address_input_street' class="form-control" placeholder='Enter Street'>
                        </div>
                        <div class="form-group">
                            <label>City Name</label>
                            <input type='text' id='address_input_city' class="form-control" placeholder='Enter City'>
                        </div>
                        <div class="form-group">
                            <label>Pin Code</label>
                            <input type='text' id='address_input_pin' class="form-control" placeholder='Enter Pin Code'>
                        </div>
                        <div class="form-group">
                            <label>Country Name</label>
                            <input type='text' id='address_input_country' class="form-control" placeholder='Enter Country'>
                        </div>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button onclick="add_btn_click()" data-dismiss="modal" type="button" class="btn btn-primary">Add</button>
                    </div>
                  </div>

                </div>
            </div>
        
        </div>
        
        <script>
            var host = "/ecommerce";
            function add_btn_click() {
                // make ajax to server
                var street = $('#address_input_street').val();
                var city = $('#address_input_city').val();
                var country = $('#address_input_country').val();
                var pin = $('#address_input_pin').val();
                
                $.ajax({
                   url: host + "/me/address",
                   method: 'post',
                   data: {street: street, city: city, pincode: pin, country: country },
                   success: function(response) {
                       console.log(response);
                       getAddress();
                   },
                   failed: function(error) {
                       console.log(error);
                   }
                });
            }
            
            function addPaymentTab(payment) {
                return $("<a/>", {
                    class: 'btn btn-primary btn-lg',
                    text: payment.type
                });
            }
            
            function addAddressTab(add) {
                return $("<a/>", {
                    class: 'btn btn-primary btn-lg btn-block',
                    text: add.street + ", " + add.city + " - " + add.pincode + ", " + add.country
                });
            }
            
            function pay_btn_click() {
                // make ajax to server
                var type = $('#payment_input').val();
                
                $.ajax({
                   url: host + "/me/payment",
                   method: 'post',
                   data: {type:type },
                   success: function(response) {
                       console.log(response);
                       getPayments();
                   },
                   failed: function(error) {
                       console.log(error);
                   }
                });
            }
            
            
            function getAddress() {
                $.ajax({
                    url: host + "/me/address",
                    method: 'get',
                    success: function(response) {
                        if(response.success) {
                            $("#add_here").empty();
                            for(var key in response.message) {
                                $("#addHere").append(
                                    addAddressTab(response.message[key])
                                );
                            }
                        }
                    }
                });
            } 
            
            function getPayments() {
                $.ajax({
                    url: host + "/me/payment",
                    method: 'get',
                    success: function(response) {
                        if(response.success) {
                            $("#pay_here").empty();
                            for(var key in response.message) {
                                $("#pay_here").append(
                                        addPaymentTab(response.message[key])
                                );
                            }
                        }
                    }
                });
            }
        
            getAddress();
            getPayments();
        </script>
    </body>
</html>
