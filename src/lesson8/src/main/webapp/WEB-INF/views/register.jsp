<%--
  Created by IntelliJ IDEA.
  User: Ural
  Date: 04.10.2020
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
<form method="post" action="/register">
    <input placeholder="name" class="input-text" name="name" type="text">
    <input placeholder="email" class="input-text" name="email" type="text">
    <input placeholder="password" class="input-text" name="password" type="password">
    <input placeholder="repeat password" class="input-text" name="password-repeat" type="password">
    <label><input type="checkbox" name="apply" value="true"/> Apply data processing condition</label>
    <input type="submit">
</form>
<br>
<%=request.getAttribute("error")%>
</body>
</html>
