<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-05-05
  Time: 오후 5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Modify/Remove</title>
</head>
<body>
<%--form을 나누는 것은, 각각 다른 행위를 하는 버튼을 명시하기 위함이다.--%>
<form id = "form1" action="/todo/modify" method="post">
    <div>
        <input type = "text" name = "tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type = "text" name = "title" value="${dto.title}" >
    </div>
    <div>
        <input type = "date" name = "dueDate" value="${dto.dueDate}">
    </div>
    <div>
        <input type = "checkbox" name = "finished" ${dto.finished ? "checked" : ""}>
    </div>

    <div>
        <button type="submit">Modify</button>
    </div>
</form>

<form id = "form2" action="/todo/remove" method="post">
<%--    <input type = "hidden" name = "tno" value = "${dto.tno}" readonly>--%>
    <input name = "tno" value = "${dto.tno}" readonly>
    <div>
        <button type="submit">Remove</button>
    </div>
</form>

</body>
</html>
