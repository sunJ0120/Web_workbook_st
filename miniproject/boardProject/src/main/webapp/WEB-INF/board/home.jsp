<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World~</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f8f8;
            margin: 40px;
        }

        .container {
            max-width: 600px;
            margin: auto;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            padding: 30px;
            text-align: center;
        }

        h1 {
            margin-bottom: 10px;
        }

        h3 {
            color: #666;
            margin-bottom: 30px;
        }

        img {
            border-radius: 50%;
            width: 200px;
            height: 200px;
            object-fit: cover;
            margin-bottom: 30px;
        }

        .button-group {
            display: flex;
            justify-content: center;
            gap: 15px;
            margin-top: 20px;
        }

        .button-group a button {
            padding: 10px 20px;
            font-size: 18px;
            border: none;
            background-color: #333; /* 진한 회색/검정 */
            color: white;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .button-group a button:hover {
            background-color: #555; /* 밝은 회색으로 hover 효과 */
        }
    </style>
</head>
<body>

<div class="container">
    <h1>Hello world~</h1>
    <h3>this is my first board project</h3>

    <img src="${pageContext.request.contextPath}/img/smilehehe.png" alt="My Dog">

    <div class="button-group">
        <a href="/board/list">
            <button type="button">List</button>
        </a>
        <a href="/board/write">
            <button type="button">Write</button>
        </a>
    </div>
</div>

</body>
</html>
