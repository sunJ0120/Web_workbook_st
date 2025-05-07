<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Welcome to my world~</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      margin: 40px;
      background-color: #fafafa;
    }
    .container {
      max-width: 800px;
      margin: auto;
    }
    .title {
      text-align: center;
    }
    .card {
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
      padding: 20px;
      margin-bottom: 20px;
    }
    .card a {
      font-size: 18px;
      font-weight: bold;
      color: #3366cc;
      text-decoration: none;
    }
    .card .info {
      margin-left: 20px;
      color: #555;
    }
    .label {
      font-weight: bold;
      margin-right: 5px;
    }
  </style>
</head>
<body>
<!-- 🏠 홈으로 버튼 (오른쪽 위 고정) -->
<div style="position: absolute; top: 20px; right: 20px;">
  <a href="${pageContext.request.contextPath}/home" style="text-decoration: none;">
    <button style="padding: 8px 16px; font-size: 16px; background-color: #f0f0f0; border: 1px solid #ccc; border-radius: 6px; cursor: pointer;">
      🏠 홈으로
    </button>
  </a>
</div>

<div class="container">
  <h1 class="title">Welcome to my world~</h1>
  <h3 class="title">this is my first board project</h3>

  <c:forEach items="${dtoList}" var="dto">
    <div class="card">
      <div>
        🖤 <a href="/board/read?boardno=${dto.boardno}">${dto.boardno}. ${dto.title}</a>
      </div>
      <div class="info">
        📄 <span class="label">Content:</span>${dto.content}
      </div>
      <div class="info">
        🕓 <span class="label">RegDate:</span>${dto.regDate}
      </div>
      <div class="info">
        ✏️ <span class="label">ModDate:</span>
        <c:choose>
          <c:when test="${not empty dto.modDate}">
            ${dto.modDate}
          </c:when>
          <c:otherwise>수정 안함</c:otherwise>
        </c:choose>
      </div>
    </div>
  </c:forEach>
</div>
</body>
</html>
