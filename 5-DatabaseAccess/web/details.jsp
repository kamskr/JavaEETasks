<%@ page import="model.Resource" %><%--
  Created by IntelliJ IDEA.
  User: kamilsikora
  Date: 12/04/2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Resource resource = (Resource)request.getAttribute("resource");
%>
<html>
<head>
    <title>Details</title>
</head>
<body>
    <a href="/resources"><- Go back</a>
    <h3>Name: <%=resource.getName()%></h3>
    <h5>Content:</h5>
    <p><%=resource.getContent()%></p>
    <br>
    <br>
    <br>
    <a href="logout">Log out</a>
</body>
</html>
