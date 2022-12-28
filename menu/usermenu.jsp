<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="../img/logo.jpg" id="nav-img" alt="">
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
            <form action="/bank1/actions/user/viewBalance.jsp" method="get">
                <button type="submit" class="container-btn">
                    View Balance
                </button>
            </form>
            <form action="/bank1/actions/user/makeTransaction.jsp" method="get">
                <button type="submit" class="container-btn">
                    Make Transaction
                </button>
            </form>
            <form action="/bank1/actions/user/withdraw.jsp" method="get">
                <button type="submit" class="container-btn">
                    Make Withdrawal
                </button>
            </form>
            <form action="/bank1/actions/user/viewTransactions.jsp" method="get">
                <button type="submit" class="container-btn">
                    View My Transactions
                </button>
            </form>
        </div>
    </div>
</body>
</html>