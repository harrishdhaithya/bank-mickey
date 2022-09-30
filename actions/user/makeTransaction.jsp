<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.User,com.dao.UserDao,com.Singleton.Singleton"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/user/transaction.js"></script>
</head>
<body>
    <%
        if(session.getAttribute("name")==null){
            response.sendRedirect("/bank1");
        }else{
            if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("/bank1/menu/adminmenu.jsp");
            }
        }
    %>
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
            Welcome
        </div>
        <div class="container-body">
            <form name="transaction" onsubmit="makeTransaction(event);">
                <div class="form-name">
                    <div>Account Number</div>
                    <input type="text" name="accno" id="accno" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Amount</div>
                    <input type="text" name="amount" id="amount" class="form-text-box" required>
                </div>
                <input type="submit" value="Make Transaction" class="form-submit-btn">
            </form>
           <button class="form-submit-btn" onclick="location.href='/bank1/menu/usermenu.jsp'">Home</button>
        </div>
    </div>
</body>
</html>