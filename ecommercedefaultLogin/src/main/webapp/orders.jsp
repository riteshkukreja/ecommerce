<%-- 
    Document   : orders
    Created on : Jul 2, 2017, 3:16:45 PM
    Author     : Ritesh Kukreja
--%>

<%@page import="com.ritesh.ecommerce.dao.OrderItem"%>
<%@page import="com.ritesh.ecommerce.dao.Orders"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%@include file="cdns.jsp" %>
        <title>Orders</title>
        <script> 
            var host = "/ecommerce";
        </script>
        <style>
            .order_holder {
                background: #FFF;
                box-sizing: border-box;
                padding: 10px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        
        <%@include file="header.jsp" %>
        
        <div class="container-fluid">
        
        <h1>Orders</h1>
        <% if(request.getAttribute("single") != null) { %>
        
        <% Orders order = (Orders) request.getAttribute("order"); %>
        
        <table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price/Piece</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Cost</th>
                </tr>
            </thead>
            <tbody>
            
                <% for ( OrderItem orderItem: order.getItems() ) { %>

                <tr>
                    <td><%= orderItem.getProduct().getName() %></td>
                    <td><%= orderItem.getProduct().getDescription()%></td>
                    <td><%= orderItem.getProduct().getPrice()%></td>
                    <td><%= orderItem.getProduct().getCategory().getName() %></td>
                    <td><%= orderItem.getQuantity()%></td>
                    <td><%= orderItem.getQuantity() * orderItem.getProduct().getPrice() %></td>
                </tr>

                <% } %>
            </tbody>
        </table>
        
        <hr>
        <div>
            Amount:<b> <%= order.getPrice() %></b>
        </div>
        <hr>
        <% if(order.getPayment() == null) { %>
        <h3>Payments</h3>
        
        <ul id="payments">
            <button class="btn btn-secondary" data-toggle="modal" data-target="#myPayment">+ Add New</button>
            <div class="btn-group" id="addHere" data-toggle="buttons"></div>
        </ul>
        
        
        <button onclick="pay()" class="btn btn-primary">Pay Now</button>
        <button onclick="cancel()" class="btn btn-danger">Cancel</button>
        <% } else { %>
        Paid through <%= order.getPayment() %>
        <% } %>
        
        <hr>
        
        
        <script>
            var selectedPay = false;
            function loadPayments() {
                $.ajax({
                    url: host + '/me/payment',
                    method: 'get',
                    success: function(response) {
                        if(response.success) {
                            $("#addHere").empty();
                            for(key in response.message) {
                                $("#addHere").append(
                                        addPaymentTab(response.message[key])
                                );
                            }
                        }
                    },
                    failed: function(error) {
                        console.log(error);
                    }
                });   
            }
            
            var pay = function() {
                if(selectedPay) {
                    $.ajax({
                        url: host + '/me/order/pay',
                        method: 'post',
                        data: {orderId: <%= order.getId() %>, paymentId: selectedPay },
                        success: function(response) {
                            console.log(response);
                            location.reload();
                        },
                        failed: function(error) {
                            console.log(error);
                        }
                    });
                }
            }
            
            var cancel = function() {
                $.ajax({
                    url: host + "/me/order/cancel",
                    method: 'post',
                    data: {orderId: <%= order.getId() %>},
                    success: function() {
                        location = host + "/dashboard";
                    }
                });
            }
        
            function addPaymentTab(payment) {
                return $("<label/>", {
                    class: 'btn btn-primary',
                    "data-id": payment.id,
                    click: function() {
                        selectedPay = $(this).data("id");
                    }
                }).append($("<input/>", {
                    type: 'radio',
                    name: 'payment'
                })).append(payment.type);
            }
            loadPayments();
        </script>
        
        <% } %>
        <div id="pastorders">
            <h2>Previous Orders</h2>
        </div>
        
        <script>
            function loadPrevious() {
                $.ajax({
                    url: host + '/me/orders',
                    method: 'get',
                    success: function(response) {
                        console.log(response);
                        if(response.success) {
                            for(key in response.message) {
                                $("#pastorders").append(
                                  showOrder(response.message[key])   
                                );
                            }
                        }
                    },
                    failed: function(error) {
                        console.log(error);
                    }
                });
            }
            
            var showOrder = function(order) {                
                var holder = $("<div/>", {
                    "data-id": order.id,
                    class: 'order_holder'
                });
                $("<h3/>", {
                    text: order.items.length > 0 ? order.items[0].product.name + (order.items.length-1 > 0 ? " and " + (order.items.length-1) + " items" : ""): "No items"
                }).appendTo(holder);
                
                $("<p/>", {
                    html: "Amount: " + order.price + "<br/>" + (order.payment ? "paid through " + order.payment.type : "Not Paid yet!")
                }).appendTo(holder);
                
                holder.click(function() {
                   location =  host + "/orders/" + holder.data("id")
                });
                
                return holder;
            };
         
            loadPrevious();
        </script>
        
        
        <script>
            function pay_btn_click() {
                // make ajax to server
                var type = $('#payment_input').val();
                
                $.ajax({
                   url: host + "/me/payment",
                   method: 'post',
                   data: {type:type },
                   success: function(response) {
                       loadPayments();
                       closeModel();
                   },
                   failed: function(error) {
                       console.log(error);
                   }
                });
            }
        </script>
        
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
        
        </div>
    </body>
</html>
