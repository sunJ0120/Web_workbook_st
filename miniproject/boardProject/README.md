
# 📘 Servlet + JSP + MariaDB 게시판 프로젝트

## 📌 프로젝트 소개

- 웹 개발 워크북에서 Servlet + JSP + MariaDB를 학습한 후, 이를 토대로 제작한 개인 미니 프로젝트입니다.
- CRUD 기능 중심으로 DAO, DTO, VO 구조와 MVC 아키텍처의 흐름, DB 연동 방식에 대한 실습을 목적으로 합니다.

## 🚀 ver 0.1. 주요 기능 (구현 완료)

- [x] 게시글 작성 (Create)
- [x] 게시글 목록 조회 (Read)
- [x] 게시글 상세 조회
- [x] 게시글 수정 (Modify)
- [x] 게시글 삭제 (Delete)

## 🧩 차후 확장 예정 기능

- [ ] 세션 로그인
- [ ] 페이징 처리
- [ ] 문서 private (나만 보기) 기능
- [ ] 댓글 기능
- [ ] 대댓글 기능
- [ ] 카테고리 기능


## 🛠️ 사용 기술 스택

| 구분 | 사용 기술 |
|------|-----------|
| **Frontend** | ![HTML](https://img.shields.io/badge/HTML5-E34F26?logo=html5&logoColor=white) ![CSS](https://img.shields.io/badge/CSS3-1572B6?logo=css3&logoColor=white) ![JSP](https://img.shields.io/badge/JSP-007396?logo=java&logoColor=white) |
| **Backend** | ![Java](https://img.shields.io/badge/Java-11-orange?logo=OpenJDK&logoColor=white) ![Servlet](https://img.shields.io/badge/Servlet-430098?logo=java&logoColor=white) ![JDBC](https://img.shields.io/badge/JDBC-007396?logo=java&logoColor=white) |
| **Database** | ![MariaDB](https://img.shields.io/badge/MariaDB-003545?logo=mariadb&logoColor=white) |
| **Environment** | ![Tomcat](https://img.shields.io/badge/Tomcat-FFCC00?logo=apachetomcat&logoColor=black) ![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-000000?logo=intellijidea&logoColor=white) |

*(CSS는 생성형 AI(ChatGPT)의 도움을 받아 일부 구성하였습니다.)*

## 📁 프로젝트 구조

```text
📁 src
 ┣ 📂 main
 ┃ ┣ 📂 java
 ┃ ┃ ┗ 📂 org.sunj.boardproject
 ┃ ┃    ┣ 📂 controller       # DeleteController, ListController 등
 ┃ ┃    ┣ 📂 domain           # BoardDTO, BoardVO
 ┃ ┃    ┣ 📂 repository       # BoardDAO
 ┃ ┃    ┣ 📂 service          # BoardService
 ┃ ┃    ┗ 📂 util             # ConnectionUtil, MapperUtil
 ┃ ┣ 📂 resources
 ┃ ┃ ┣ 📄 db.properties
 ┃ ┃ ┗ 📄 log4j2.xml
 ┃ ┗ 📂 webapp
 ┃   ┣ 📄 index.jsp
 ┃   ┣ 📂 img                 # smilehehe.png
 ┃   ┗ 📂 WEB-INF
 ┃     ┣ 📄 web.xml
 ┃     ┗ 📂 board             # home.jsp, list.jsp, read.jsp, write.jsp, modify.jsp
 ┣ 📂 test
 ┃ ┣ 📂 java
 ┃ ┃ ┗ 📂 org.sunj.boardproject
 ┃ ┃    ┣ 📂 repository       # BoardDAOTest
 ┃ ┃    ┗ 📂 service          # BoardServiceTest
 ┃ ┗ 📂 resources
 ┃   ┣ 📄 db.properties
 ┃   ┗ 📄 log4j2.xml
📄 build.gradle
📄 README.md
📄 settings.gradle
```
## 📸 프로젝트 화면 예시
<!-- TODO: 주요 화면 캡처 후 표 또는 리스트로 정리해주세요 -->

| 기능 | 설명 | 스크린샷 |
|------|------|----------|
| 홈 화면 | main 홈화면 입니다.<br>list를 볼 수 있는 기능과 write로 글을 쓸 수 있는 기능이 있습니다. | <img src="https://github.com/user-attachments/assets/933fd861-5fc2-4c5f-897b-7eae3c0f27e5" width="1000"/> |
| 글 작성 | 게시글을 작성할 수 있습니다. | <img src="https://github.com/user-attachments/assets/d9992982-3c8a-47fd-abe3-46fb46079fef" width="1000"/> |
| 게시판 리스트 | 게시판 리스트를 볼 수 있습니다. | <img src="https://github.com/user-attachments/assets/4c8b650c-0d3b-4328-8d09-21564a034e27" width="1000"/> |
| 수정 | 게시글을 수정할 수 있습니다. | <img src="https://github.com/user-attachments/assets/c776210c-dc44-4173-b7e6-2ba375b3400a" width="1000"/> |
| 상세 | 게시글의 상세 페이지 입니다. | <img src="https://github.com/user-attachments/assets/85b86bbd-bbe9-4f26-8958-1a2d3568bf3c" width="1000"/> |
| 삭제 | 게시글의 삭제 기능입니다. | <img src="https://github.com/user-attachments/assets/d4402179-a3e3-4af1-92fe-5a07b7b45d6d" width="1000"/> |

## 🎞️ ver 0.1 시연 GIF
<!-- TODO: 주요 기능 시연을 GIF로 제작해 첨부해주세요 -->
<img src="https://github.com/user-attachments/assets/d371f36f-59c5-49d1-9f24-eddde4ac6d98" width="1000"/>
