<%--
  Created by IntelliJ IDEA.
  User: kamilsikora
  Date: 12/04/2020
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="/register" method="post">
        <h2>Register</h2>
        <input name="inputLogin" type="text" class="form-control" placeholder="Login" required autofocus>
        <input name="inputFirstName" type="text" class="form-control" placeholder="First Name" required autofocus>
        <input name="inputLastName" type="text" class="form-control" placeholder="Last Name" required autofocus>
        <input name="inputPassword" type="password" class="form-control" placeholder="Password" required>
        <button type="submit">Register</button>
        <a href="/register">Login</a>
    </form>
</body>
</html>
