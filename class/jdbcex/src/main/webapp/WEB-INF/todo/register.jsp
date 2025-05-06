<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-05-05
  Time: 오후 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--제출하면 POST 형식으로 보낸다.--%>
<form action="/todo/register" method="post">
    <div>
        <input type="text" name = "title" placeholder="INSERT TITLE">
    </div>
    <div>
        <input type="date" name = "dueDate">
    </div>
    <div>
        <button type = "reset">RESET</button>
        <button type = "submit">REGISTER</button>
    </div>
</form>
</body>
</html>
