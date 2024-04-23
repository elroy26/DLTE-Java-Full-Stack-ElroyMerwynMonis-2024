<%@ page import="app.mybank.services.TransactionService" %>
<%@ page import="app.mybank.remotes.StorageTarget" %>
<%@ page import="app.mybank.middleware.DatabaseTarget" %>
<%@ page import="app.mybank.entity.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body style="background-color: rgb(206, 236, 246);">
<% response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    TransactionService transactionService;
    StorageTarget storageTarget=new DatabaseTarget();
    transactionService=new TransactionService(storageTarget);
    if(session.getAttribute("username")!=null){
        Account username = (Account) session.getAttribute("username");
        transactionService.callSaveAccount(username);
%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #449af5;">
    <div class="container-fluid">
        <a class="view navbar-brand text-light display-6 text-uppercase" style="font-weight: bold;">MyBank</a>
        <button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#myBankMenu">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="myBankMenu">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0 pe-3">
                <li class="nav-item pe-3">
                        <span class="navbar-text text-light bg-info border border-dark-subtle p-2">
                            Welcome <p><%=session.getAttribute("username")%></p>
                        </span>
                </li>
                <li class="nav-item pe-3">
                    <a class="new nav-link text-light btn btn-outline-warning border border-warning" href="createAccount.jsp">Create Account</a>

                </li>
                <li class="nav-item pe-3">
                    <a class="new nav-link text-light btn btn-outline-warning border border-warning" href="viewTransaction.jsp">View Transaction</a>

                </li>

                <li class="nav-item pe-3" >
                    <a class="logout nav-link text-light btn btn-outline-danger border border-light " href="logout">Logout</a>
                </li>
            </ul>
            <!-- Search bar -->
            <form class="d-flex bg-light mb-3">
                <input class="form-control me-2" type="search" placeholder="Search by insurance type" aria-label="Search" id="searchInput">
                <button class="btn btn-outline-primary" type="button" onclick="searchByInsuranceType()">Search</button>
            </form>
        </div>
    </div>
</nav>
<br>
<br>
<iframe id="insuranceFrame"  width="100%" height="900px" frameborder="0"></iframe>
<% }
else {
    response.sendRedirect("index.jsp");
}%>
</body>
</html>