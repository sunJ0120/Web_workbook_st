<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-06-09
  Time: 오후 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--JSTL 관련 설정 추가-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Features</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Pricing</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
    <div class="row content">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Featured
                </div>
                <!-- list에 맞춰 해당 card-body 부분을 수정한다. -->
                <div class="card-body">
                    <form action="todo/modify" method="post">
                        <div class="input-group mb-3">
                            <span class="input-group-text">TNO</span>
                            <input type="text" name="tno" class="form-control"
                                   value=<c:out value="${dto.tno}"></c:out> readonly>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control"
                                   value=<c:out value="${dto.title}"></c:out> readonly>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">DueDate</span>
                            <input type="text" name="dueDate" class="form-control"
                                   value=<c:out value="${dto.dueDate}"></c:out> readonly>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">Writer</span>
                            <input type="text" name="writer" class="form-control"
                                   value=<c:out value="${dto.writer}"></c:out> readonly>
                        </div>

                        <!-- 체크 박스를 통해 finished 여부를 체크한다. -->
                        <div class="form-check">
                            <label class="form-check-label">
                                Finished &nbsp;
                            </label>
                            <!-- 삼항 연산자로 checked를 설정한다. -->
                            <input class="form-check-input" type="checkbox" name="finished"
                            ${dto.finished ? "checked" : ""} disabled>
                        </div>

                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-danger">Remove</button>
                                <button type="button" class="btn btn-primary">Modify</button>
                                <button type="button" class="btn btn-secondary">List</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- 수정 삭제를 위한 자바 스크립트 코드 -->
                <script>
                    // 수정 버튼 누를 시 modify로 이동한다. document에서 querySelector를 통해 버튼을 선택해서 진행한다.
                    document.querySelector('.btn-primary').addEventListener('click', function() {
                        self.location = "/todo/modify?tno="+${dto.tno};
                    });
                    // list 버튼을 누를 시 list로 이동한다.
                    document.querySelector('.btn-secondary').addEventListener('click', function(e) {
                        self.location = "/todo/list";
                    });
                </script>

                <script>
                    const formObj = document.querySelector("form");

                    document.querySelector(".btn-danger").addEventListener("click", function (e){
                        e.preventDefault();
                        e.stopPropagation();

                        //formObj를 이렇게 잡아서 여기서 url이랑 post 설정을 해주는것도 가능하구나 아...
                        formObj.action = "/todo/remove";
                        formObj.method = "post";

                        formObj.submit();
                    }, false);
                </script>
            </div>
        </div>
    </div>
    <div class="row content">
    </div>
    <div class="row footer">
        <!--        <h1>Footer</h1>-->
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class ="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
