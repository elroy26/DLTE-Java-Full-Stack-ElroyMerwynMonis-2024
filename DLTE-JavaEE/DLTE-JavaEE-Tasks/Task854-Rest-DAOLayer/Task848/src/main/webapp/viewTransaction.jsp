<%@ page import="app.mybank.entity.Transaction" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: xxmonise
  Date: 4/22/2024
  Time: 7:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>View Credit cards</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<% response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("username")!=null){ %>
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
                    <a class="logout nav-link text-light btn btn-outline-danger border border-light ">Logout</a>
                </li>
            </ul>
            <!-- Search bar -->
            <form class="d-flex bg-light mb-3">
                <input class="form-control me-2" type="search" placeholder="Search by insurance type" aria-label="Search" id="searchInput">
                <button class="btn btn-outline-primary" type="button" >Search</button>
            </form>
        </div>
    </div>
</nav>

<%
    List<Transaction> transactionList =(List<Transaction>) request.getAttribute("transactionList");
    pageContext.setAttribute("myData",transactionList,PageContext.APPLICATION_SCOPE);
%>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-8 col-12 table-responsive p-5 shadow-lg">
            <table class="table table-striped text-nowrap">
                <thead>
                <tr>
                    <th>Username</th><th>Transaction type</th>
                    <th>Transaction Amount</th><th>Transaction Date</th>
                </tr>
                </thead>
                <tbody>
                <%for(Transaction each:transactionList){%>
                <tr>
                    <td><%out.print(each.getUserName());%></td>
                    <td><%out.print(each.getTransactionType());%></td>
                    <td><%out.print(each.getTransactionAmount());%></td>
                    <td><%out.print(each.getTransactionDate());%></td>


                </tr>
                <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% }
else {
    response.sendRedirect("index.jsp");
}%>
</body>
</html>
