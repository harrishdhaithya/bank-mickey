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
            Welcome
        </div>
        <div class="container-body">
            <%
                UserDao udao = Singleton.getUserDao();
                User user = udao.getUserByEmail(request.getRemoteUser());
            %>
            <h2>Name: <%=user.getFname()+" "+user.getLname()%></h2>
            <h2>Account Number: <%=user.getAccno()%></h2>
            <h2>Phone Number: <%=user.getPhone()%></h2>
            <h2>Email: <%=user.getEmail()%></h2>
            <h2>Balance: <%=user.getBalance()%></h2>
           <button class="form-submit-btn" onclick="location.href='/bank1/menu/usermenu.jsp'">Home</button>
        </div>
    </div>
</body>
</html>