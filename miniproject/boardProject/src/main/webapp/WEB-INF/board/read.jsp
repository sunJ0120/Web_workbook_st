<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Write</title>
    <style>
        .form-container {
            width: 500px;
            margin: 30px auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 12px;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.1);
            font-family: 'Arial', sans-serif;
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        .form-group {
            margin-bottom: 18px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-group input[type="text"],
        .form-group input[type="date"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #aaa;
            border-radius: 6px;
        }

        .form-group textarea {
            height: 120px;
            resize: vertical;
        }

        .form-group input[type="checkbox"] {
            margin-right: 5px;
        }

        .btn-box {
            display: flex;
            justify-content: flex-end; /* 오른쪽 정렬 */
            gap: 10px; /* 버튼 간 간격 */
            align-items: center;
            margin-top: 25px;
        }

        .btn-box button,
        .btn-box a {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            cursor: pointer;
        }

        .btn-box a {
            background-color: #888;
        }

        .btn-box button:hover,
        .btn-box a:hover {
            background-color: #555;
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

<div class="form-container">
    <h2>📌 ${dto.boardno}. MyBoard!</h2>

    <div class="form-group">
        <label>제목</label>
        <div>${dto.title}</div>
    </div>

<%--    <div class="form-group">--%>
<%--        <label>공개 여부</label>--%>
<%--        <div>--%>
<%--            <c:choose>--%>
<%--                <c:when test="${not empty dto.isPublic and dto.isPublic}">--%>
<%--                    공개--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                    비공개--%>
<%--                </c:otherwise>--%>
<%--            </c:choose>--%>
<%--        </div>--%>
<%--    </div>--%>

    <div class="form-group">
        <label>등록일</label>
        <div>${dto.regDate}</div>
    </div>

    <div class="form-group">
        <label>수정일</label>
        <div>
            <c:choose>
                <c:when test="${not empty dto.modDate}">
                    ${dto.modDate}
                </c:when>
                <c:otherwise>
                    수정 없음
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="form-group">
        <label>본문</label>
        <div style="white-space: pre-wrap;">${dto.content}</div>
    </div>

    <div class="btn-box">
        <!-- 수정하기 링크 -->
        <a href="${pageContext.request.contextPath}/board/modify?boardno=${dto.boardno}">✏️ 수정하기</a>

        <!-- 삭제 form (같은 btn-box 안에 배치) -->
        <form id="deleteForm"
              method="post"
              action="${pageContext.request.contextPath}/board/delete"
              style="display:inline-block; margin: 0;">
            <input type="hidden" name="boardno" value="${dto.boardno}" /> <!-- 이거 있어야 번호 가져옴. -->
            <button type="button" onclick="confirmDelete()">❌ 삭제하기</button>
        </form>
    </div>
</div>
<!-- 팝업 함수 자바 스크립스 호출 -->
<script>
    function confirmDelete() {
        if (confirm('😢 정말 삭제하시겠습니까?')) {
            document.getElementById('deleteForm').submit();
        }
    }
</script>
</body>
</html>
