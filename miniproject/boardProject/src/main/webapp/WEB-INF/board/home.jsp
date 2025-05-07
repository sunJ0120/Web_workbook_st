<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-05-07
  Time: 오후 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World~</title>
</head>
<body>
<h1>Hello world~</h1>
<h3>this is my first board project</h3>
<br><br><br> <%--띄어쓰기--%>
<%--이미지 가운데 정렬로 삽입--%>
<div style="text-align: center;">
  <img src="${pageContext.request.contextPath}/img/smilehehe.png" alt="My Dog" width="200">
</div>
<div style="position: relative; height: 120px;"> <%-- 영역 크기 줄임 --%>
    <div style="position: absolute; bottom: 0; right: 0; display: flex; gap: 10px;">
        <a href="/board/list">
            <button type="button" style="padding: 8px 16px; font-size: 20px;">List</button>
        </a>
        <a href="/board/write">
            <button type="button" style="padding: 8px 16px; font-size: 20px;">Write</button>
        </a>
    </div>
</div>
</body>
</html>
