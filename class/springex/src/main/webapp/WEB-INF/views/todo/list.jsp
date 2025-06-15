<%--
  Created by IntelliJ IDEA.
  User: sspur
  Date: 2025-06-09
  Time: 오후 8:23
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
                    <h5 class="card-title">Special title treatment</h5>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Tno</th>
                            <th scope="col">Title</th>
                            <th scope="col">Writer</th>
                            <th scope="col">DueDate</th>
                            <th scope="col">Finished</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${responseDTO.dtoList}" var="dto">
                            <!-- 여기서 각각 TodoDTO의 제목을 누르면 상세 페이지로 이동할 수 있도록 한다. -->
                            <tr>
                                <th scope="row"><c:out value="${dto.tno}"/></th>
                                <td><a href="/todo/read?tno=${dto.tno}" class="text-decoration-none"><c:out value="${dto.title}"/></a></td>
                                <td><c:out value="${dto.writer}"/></td>
                                <td><c:out value="${dto.dueDate}"/></td>
                                <td><c:out value="${dto.finished}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!-- 페이징 처리 부트스트랩 -->

                    <div class="float-end">
                        <ul class="pagination justify-content-center">

                            <!-- 이 부분이 이제 Previous가 나타나는 부분이다. -->
                            <c:if test="${responseDTO.prev}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.start - 1}">Previous</a>
                                </li>
                            </c:if>

                            <!-- page 로 넘어갈 수 있도록 forEach 구성 -->
                            <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}"  var="num">
                                <li class="page-item ${responseDTO.page == num? "active":""}">
                                    <a class="page-link" data-num="${num}">${num}</a>
                                </li>
                            </c:forEach>

                            <!-- 이게 next를 구성하는 것이다. -->
                            <c:if test="${responseDTO.next}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                    <!-- 페이지 이동을 위한 스크립트 추가, data-num 변수를 이용한다. -->
                    <script>
                        document.querySelectorAll(".pagination").addEventListener('click', function(e) {
                            e.preventDefault() // 기본 동작을 막는다.
                            e.stopPropagation() // 이벤트 전파를 막는다.

                            const target = e.target; //클릭된 실제 DOM 요소를 가져온다.

                            //a 태그를 클릭했을때만 동작하도록 하기 위함이다.
                            if(target.tagName !== 'A'){
                                return;
                            }

                            // 저장한 data-num 속성값을 가져온다.
                            const num = target.getAttribute("data-num");

                            self.location = `/todo/list?page=\${num}`; //백틱을 이용해서 템플릿 처리한다.
                            //페이지 이동을 자바스크립트로 처리하기 위함이다.
                            //여기서 self는 현재 window 객체를 의미한다. (window.location과 동일하다.)
                        },false);
                    </script>

                </div>
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

