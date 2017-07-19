<%-- 
    Document   : addProduct
    Created on : Jul 2, 2017, 11:25:41 PM
    Author     : Ritesh Kukreja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="cdns.jsp" %>
        <title>Add Product</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container-fluid">
            <h1>Add a new product</h1>
            
            <div>
                <div class="form-group">
                  <label>Product Name</label>
                  <div class="input-group">
                    <input type="text" class="form-control" placeholder="Enter Product Name" id="prod_name">
                  </div>
                </div>
                <div class="form-group">
                  <label>Description</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id='prod_desc' placeholder='Enter Product Description'>
                  </div>
                </div>
                <div class="form-group">
                  <label>Price</label>
                  <div class="input-group">
                    <input type="number" class="form-control" id='prod_price' placeholder='Enter Price'>
                  </div>
                </div>
                <div class="form-group">
                  <label>Category</label>
                  <div class="input-group">
                      <select id='categories' name='categoryId' class="form-control"></select>
                  </div>
                </div>
                
                <div class="form-group">
                    <button onclick='addProd()' class="btn btn-primary">Add</button>
                    <button class="btn btn-secondary" data-toggle="modal" data-target="#myCat">Add category</button>
                </div>
          </div>
            
            <div id="myCat" class="modal fade" role="dialog">
                <div class="modal-dialog">
                  <!-- Modal content-->
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title">Add Category</h4>
                    </div>
                    <div class="modal-body">
                        <input type='text' class="form-control" id='cat_name' placeholder='Enter Category Name'>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button onclick="addCat()" data-dismiss="modal" type="button" class="btn btn-primary">Add</button>
                    </div>
                  </div>

                </div>
            </div>
            
            <div id='alerts'></div>
        
        </div>
        <script>
            var host = "/ecommerce";
            function addCat() {
                $.ajax({
                    url: host + "/category",
                    method: 'post',
                    data: { name: $("#cat_name").val() },
                    success: function() {
                        loadCat();
                    }
                });
            }
            
            function loadCat() {
                $.ajax({
                    url: host + "/category",
                    success: function(response) {
                        if(response.success) {
                            $("#categories").empty();
                            for(var key in response.message) {
                                $("#categories").append(
                                  $("<option/>", {
                                      value: response.message[key].id,
                                      text: response.message[key].name
                                  })      
                                );
                            }
                        }
                    }
                });
            }
            
            function addProd() {
                $("#message").text("");
                $.ajax({
                    url: host + "/products",
                    method: 'post',
                    data: {
                        name: $("#prod_name").val(),
                        description: $("#prod_desc").val(),
                        price: $("#prod_price").val(),
                        categoryId: $("#categories").val()
                    },
                    success: function(response) {
                        if(response.success) {
                            $("#message").text("Product added");
                            $("#prod_name").val("");
                            $("#prod_desc").val("");
                            $("#prod_price").val("");
                            notifier("Product Added Successfully", "success");
                        }
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
            
            loadCat();
        </script>
    </body>
</html>
