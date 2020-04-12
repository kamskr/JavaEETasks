<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Resource" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kamilsikora
  Date: 12/04/2020
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%
    User user = (User)session.getAttribute("user");
    List<Resource> resources = (List<Resource>)session.getAttribute("listOfResources");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resources</title>
</head>
<body>
    <h1>Hello ${user.getFirstName()}!</h1>

    <%
        if(resources.size() > 0){
    %>
        <h2>List of available resources:</h2>
        <%
            for(Resource resource : resources){
                %>
                <a href="/details?id=<%=resource.getIdResource()%>">Name:  <%= resource.getName()%></a><br>
                 <%
            }
        %>
    <%
        } else {
            %>
    <h2>No available resources :(</h2>
            <%
        }
    %>

    <h3>You can create new resource here!</h3>
    <form action="/resources" method="post">
        <h2>Add new resource</h2>
        <input name="inputName" type="text" class="form-control" placeholder="Name" required autofocus>
        <input name="inputContent" type="text" cols="40" rows="5" class="form-control" placeholder="Content" required autofocus>
        <button type="submit">Create new resource</button>
    </form>

    <br>
    <br>
    <br>
    <a href="logout">Log out</a>
</body>
</html>
