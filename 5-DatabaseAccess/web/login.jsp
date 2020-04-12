<%--
  Created by IntelliJ IDEA.
  User: kamilsikora
  Date: 12/04/2020
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form action="/login" method="post">
        <h2>Log in</h2>
        <input name="inputLogin" type="text" class="form-control" placeholder="Username" required autofocus>
        <input name="inputPassword" type="password" class="form-control" placeholder="Password" required>
        <button type="submit">Log in</button>
        <br>
        <a href="/register">Register</a>
    </form>
</body>
</html>
