<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-05-15
  Time: 오후 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            background-color: #f7f9fc;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .login-container {
            background-color: white;
            padding: 40px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 300px;
        }

        .login-container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .login-container input {
            width: 100%;
            padding: 12px 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .login-container button {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            margin-top: 15px;
        }

        .login-container button:hover {
            background-color: #45a049;
        }
        .auto-login {
            display: flex;
            align-items: center;     /* 세로 정렬 */
            justify-content: center; /* ← 이걸 추가해야 "가운데로" 온다! */
            gap: 8px;
            margin-top: 10px;
        }
        /*텍스트 노드가 저장되어 있으니까, 정렬이 잘 안된다. 이거 id로 감싸주기*/
        #check {
            width: 16px;
            height: 16px;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="/login" method="post">
        <input type="text" name="userId" placeholder="User ID" required>
        <input type="password" name="userPw" placeholder="Password" required>
        <div class="auto-login">
            <label for="check">자동 로그인 여부</label>
            <input type="checkbox" id="check" name="auto">
        </div>
        <button type="submit">LOGIN</button>
    </form>
</div>
</body>
</html>

