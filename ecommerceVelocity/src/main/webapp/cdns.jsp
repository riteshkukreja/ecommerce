<%-- 
    Document   : cdns
    Created on : Jul 4, 2017, 12:12:29 PM
    Author     : Ritesh Kukreja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Latest jQuery -->
<script     src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
            
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<style>
    body { background: rgba(0, 0, 0, .03);  margin: 0px;}
    .model {
        width: 60%;
        height: 40%;
        position: absolute;
        left: 20%;
        top: 30%;
        background: #CCC;
        box-sizing: border-box;
        padding: 20px;
        display: none;
    }
    .cart-icon {
        position: absolute;
        top: 10px;
        right: 20px;
        z-index: 10;
    }

    .container-fluid {
        width: 80%;
        margin-left: 10%;
        margin-right: 10%;
    }
    nav {
        background: white;
        padding: 10px;
        box-shadow: 0px 2px 5px 0px #ccc;
    }

    #alerts {
        position: absolute;
        bottom: 10px;
        width: 80%;
        left: 10%;
        right: 10%;
        z-index: 99;
    }
</style>