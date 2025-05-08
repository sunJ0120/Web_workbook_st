
# 📘 Servlet + JSP + MariaDB 게시판 프로젝트

## 📌 프로젝트 소개

- 웹 개발 워크북에서 Servlet + JSP + MariaDB를 학습한 후, 이를 토대로 제작한 개인 미니 프로젝트입니다.
- CRUD 기능 중심으로 DAO, DTO, VO 구조와 MVC 아키텍처의 흐름, DB 연동 방식에 대한 실습을 목적으로 합니다.

---

## 🚀 ver 0.1. 주요 기능 (구현 완료)

- [x] 게시글 작성 (Create)
- [x] 게시글 목록 조회 (Read)
- [x] 게시글 상세 조회
- [x] 게시글 수정 (Modify)
- [x] 게시글 삭제 (Delete)

---

## 🧩 차후 확장 예정 기능

- [ ] 세션 로그인
- [ ] 페이징 처리
- [ ] 문서 private (나만 보기) 기능
- [ ] 댓글 기능
- [ ] 대댓글 기능
- [ ] 카테고리 기능

---

## 🛠️ 사용 기술 스택

- **Language**: Java 11
- **Frontend**: HTML + CSS + JSP  
  - *(CSS는 생성형 AI(ChatGPT)의 도움을 받아 일부 구성하였습니다.)*
- **Backend**: Servlet + JDBC
- **Database**: MariaDB
- **Servlet Container**: Apache Tomcat 9.x
- **IDE**: IntelliJ IDEA

---

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

## 📸 프로젝트 화면 예시
<!-- TODO: 주요 화면 캡처 후 표 또는 리스트로 정리해주세요 -->

## 📄 개발 문서
https://www.notion.so/JAVA-Servlet-JSP-MariaDB-1ea5ce67581980e9beccd74f72b6e4d9?pvs=4

## 🎞️ ver 0.1 시연 GIF
<!-- TODO: 주요 기능 시연을 GIF로 제작해 첨부해주세요 -->
