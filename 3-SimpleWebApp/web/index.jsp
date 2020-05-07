<%--
  Created by IntelliJ IDEA.
  User: kamilsikora
  Date: 26/03/2020
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <h1>Input values</h1>
  <form>
    <input type="number" required name="value1" id="a" pattern="[0-0]" />
    <input type="number" required name="value2" id="b" pattern="[0-0]" />
    <button formmethod="get" formaction="/add">GET</button>
    <button formmethod="post" formaction="/add">POST</button>
  </form>
  <div id="ajaxAddServletResponse"></div>
  <h3>Response</h3>
  <p>Your response is  <span style="font-weight: bold"> ${result}  </span></p>

