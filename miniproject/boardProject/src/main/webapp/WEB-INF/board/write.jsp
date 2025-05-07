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
            justify-content: flex-end;  /* → 오른쪽 정렬 */
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
    <h2>📌 Create MyBoard!</h2>

    <form action="/board/write" method="post">
        <div class="form-group">
            <label>제목</label>
            <input type="text" name="title" required />
        </div>

<%--        <div class="form-group">--%>
<%--            <label>--%>
<%--                <input type="checkbox" name="isPublic" value="true" />--%>
<%--                set this public--%>
<%--            </label>--%>
<%--        </div>--%>

        <div class="form-group">
            <label>본문</label>
            <textarea name="content" placeholder="내용을 입력하세요" required></textarea>
        </div>
        <div class="btn-box">
            <button type="submit">Write</button>
        </div>
    </form>
</div>

</body>
</html>
