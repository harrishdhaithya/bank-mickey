<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.User,com.dao.UserDao,com.Singleton.Singleton,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/admin/transactionbydate.js"></script>
</head>
<body>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="../../img/logo.jpg" id="nav-img" alt="">
        </div>
        <div class="nav-header">
            Banking System
        </div>
        <form action="/bank1/logout" method="post" class="logout-form">
            <button type="submit" class="logout-btn">logout</button>
        </form>
    </div>
    <div class="container">
        <div class="container-header">
            Search Transactions with Date
        </div>
        <div class="search-box">
            <form name="searchuser" onsubmit="transactionByDate(event);">
                <div class="form-name">
                    <div>Date: </div>
                    <input type="date" name="date" id="date" class="form-text-box" required>
                </div>
                <input type="submit" value="Search" class="form-submit-btn">
            </form>
        </div>
        <div class="container-body hide-box" id="result"></div>
        <button class="form-submit-btn" onclick="location.href='/bank1/menu/adminmenu.jsp'">Home</button>
    </div>
</body>
</html>