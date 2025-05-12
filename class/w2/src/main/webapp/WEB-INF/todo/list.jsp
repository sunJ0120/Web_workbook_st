<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-05-05
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>
<h2>${loginInfo}</h2>
<h3>${loginInfo.mname}</h3>
<%--var로 변수 잡고--%>
<%--내부에서 <li>로 하나씩 출력--%>

<%--
링크를 연결하여, READ 기능을 LIST에서 사용할 수 있도록 한다.
--%>

<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished ? "DONE" : "NOT YET"}</span>
        </li>
    </c:forEach>
</ul>

<form action = "/logout" method="post">
    <button>LOGOUT</button>
</form>
</body>
</html>
