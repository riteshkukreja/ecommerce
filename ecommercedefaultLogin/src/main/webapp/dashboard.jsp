<%-- 
    Document   : dashboard
    Created on : Jul 2, 2017, 1:32:24 PM
    Author     : Ritesh Kukreja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cdns.jsp" %>
        <title>Dashboard</title>
        
    </head>
    <body>
        
        <%@include file="header.jsp" %>
        
        <div class="cart-icon">
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myCart">Cart</button>
        </div>
        
        <div id="myCart" class="modal fade" role="dialog">
            <div class="modal-dialog">

              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Cart</h4>
                </div>
                <div class="modal-body">
                    <table class="table table-bordered">
                        <thead class="thead-inverse">
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody id="cart"></tbody>
                    </table>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                  <button onclick="checkout()" type="button" class="btn btn-primary">Checkout</button>
                </div>
              </div>

            </div>
          </div>

        <div class="container-fluid">
            <h2>Products</h2>
            <a href="./addProduct" class="btn btn-primary">Add Product</a>
            <table class="table">
                <thead class="thead-inverse">
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody id="products"></tbody>
            </table>

            <div id="alerts"></div>
        </div>

        <script>
            var host = "/ecommerce";
            var temp = null;
            var ajax = function(url, success, error) {
                $.ajax({

                   url: url,
                   data: {},
                   method: 'GET',
                   success: success,
                   failed: error

                });
            };

            var getCart = function() {
                ajax(host + "/me/cart", 
                    function(response) {
                       console.log(response);
                       $("#cart").empty();
                       if(response.success) {
                           for(key in response.message) {
                               $("#cart").append(
                                    addCart(response.message[key])
                                );
                           }

                           if(response.message.length == 0) {
                               $("#cart").append($("<tr/>").append($("<td/>", {
                                   text: 'Your Cart is Empty!'
                               })));
                           }
                       }
                   }, function(error) {
                       console.log(error);
                   });
            };

            var addCart = function(prod) {
                var holder = $("<tr/>", {
                    class: 'product_item'
                });

                $("<td/>", {
                    class: "name",
                    text: prod.product.name
                }).appendTo(holder);

                $("<td/>", {
                    class: "description",
                    text: prod.product.description
                }).appendTo(holder);

                $("<td/>", {
                    class: "category",
                    text: prod.product.category.name
                }).appendTo(holder);

                $("<td/>", {
                    class: "price",
                    text: prod.product.price
                }).appendTo(holder);

                $("<td/>", {
                    class: "quantity",
                    text: prod.quantity
                }).appendTo(holder);

                $("<td/>", {
                    class: "removefromCart",
                }).append($("<button/>", {
                    text: "Remove",
                    class: 'btn btn-danger',
                    "data-id": prod.id,
                    click: function() {
                        $.ajax({
                           url: host + "/me/cart/remove",
                           data: {"orderItem": $(this).data("id")},
                           method: "post",
                           success: function(response) {
                               console.log(response);
                               notifier("Item removed from Cart", "danger");
                               getCart();
                           }, 
                           failed: function(error) {
                               console.log(response);
                           }
                        });
                    }
                })).appendTo(holder);

                return holder;
            };

            var addProduct = function(prod) {
                var holder = $("<tr/>", {
                    class: 'product_item'
                });

                $("<td/>", {
                    class: "name",
                    text: prod.name
                }).appendTo(holder);

                $("<td/>", {
                    class: "description",
                    text: prod.description
                }).appendTo(holder);

                $("<td/>", {
                    class: "category",
                    text: prod.category.name
                }).appendTo(holder);

                $("<td/>", {
                    class: "price",
                    text: prod.price
                }).appendTo(holder);
                
                $("<td/>", {class: 'num-input'}).append($("<input/>", {
                    type:'number',
                    class: "quant  form-control",
                    value: 1
                })).appendTo(holder);

                $("<td/>", {
                    class: "addToCart",
                }).append($("<button/>", {
                    text: "Add",
                    class: 'btn btn-primary btn-block',
                    "data-id": prod.id,
                    click: function() {
                        temp = this;
                        var quant = $(this).parent().siblings("td.num-input").children('input.quant').val();
                        $.ajax({
                           url: host + "/me/cart/add",
                           data: {"prodId": $(this).data("id"), "quantity": quant},
                           method: "post",
                           success: function(response) {
                               console.log(response);                               
                               notifier("Item added to Cart", "success");
                               getCart();
                           }, 
                           failed: function(error) {
                               console.log(response);
                           }
                        });
                    }
                })).appendTo(holder);

                return holder;
            };

            var getProducts = function() {
                ajax(host + "/products", 
                    function(response) {
                       console.log(response);
                       $("#products").empty();
                       if(response.success) {
                           for(key in response.message) {
                               $("#products").append(
                                  addProduct(response.message[key]  )     
                                );
                           }
                       }
                   }, function(error) {
                       console.log(error);
                   });
            };

            var checkout = function() {
                $.ajax({
                   url: host + '/me/cart/checkout',
                   method: 'post',
                   success: function(response) {
                       console.log(response);
                       if(response.success) {
                           window.location = "./orders/" + response.message;
                       }
                   },
                   failed: function(error) {
                       console.log(error);
                   }
                });
            }

            function makeid(len) {
                var text = "";
                var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

                for (var i = 0; i < len; i++)
                  text += possible.charAt(Math.floor(Math.random() * possible.length));

                return text;
              }

            var notifier = function(msg, type) {
              var rand_key = makeid(10);
              var m = $("<p/>", {
                  text: msg
              });  
              
              var ho = $("<div/>", {
                  class: 'alert alert-' + type,
                  "data-key": rand_key
              }).append(m);
              
              ho.appendTo($("#alerts"));
              
              setTimeout(function() {
                  $("[data-key='" + rand_key + "']").remove();
              }, 1000);
            };

            getCart();
            getProducts();
        </script>
    </body>
</html>
