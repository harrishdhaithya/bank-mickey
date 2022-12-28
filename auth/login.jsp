<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="/bank1/css/style.css">
    <script src="/bank1/js/auth/login.js"></script>
</head>
<body>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="/bank1/img/logo.jpg" id="nav-img" alt="">
        </div>
        <div class="nav-header">
            Banking System
        </div>
    </div>
    <div class="container">
        <div class="container-header">
            Login
        </div>
        <div class="container-body">
            <form name="adminform" onsubmit="adminsignin(event);">
                <div class="form-name">
                    <div>Email:</div>
                    <input type="text" name="fname" id="email" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Password:</div>
                    <input type="password" name="password" id="password" class="form-text-box" required>
                </div>
                <input type="submit" class="form-submit-btn" >
            </form>
            <button class="form-submit-btn" onclick="location.href='/bank1'">Home</button>
        </div>
    </div>
</body>
</html>