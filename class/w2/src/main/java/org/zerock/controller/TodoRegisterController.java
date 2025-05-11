package org.zerock.controller;

/*
동록 기능 구현
get, post 방식의 동작 구현
post는 prg pattern
 */

import lombok.extern.log4j.Log4j2;
import org.zerock.dto.TodoDTO;
import org.zerock.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
@WebServlet(name = "todoRegisterController", value = "/todo/register")
public class TodoRegisterController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    /*
    login 정보 추가하기
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/todo/register GET ......");

        HttpSession session = req.getSession(); //session 획득

        if(session.isNew()){ //기존에 JSESSIONID가 없는 새로운 사용자
            log.info("JSESSIONID 쿠키가 새로 만들어진 사용자");
            resp.sendRedirect("/login");
            return;
        }

        //JSESSIONID는 있지만 해당 세션 컨텍스트에 loginInfo라는 이름으로 저장된 객체가 없는 경우
        if (session.getAttribute("loginInfo") == null) {
            log.info("로그인한 정보가 없는 사용자");
            resp.sendRedirect("/login");
            return;
        }
        // 정상 적인 경우라면 입력 화면으로 간다.
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TodoDTO todoDTO = TodoDTO.builder()
                .title(req.getParameter("title"))
                .dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
                .build();

        log.info("/todo/register POST ......");
        log.info(todoDTO);

        //db에 보내기 위해 vo로 바꾸는 과정 필요. service의 register 사용
        try{
            todoService.register(todoDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/todo/list"); //prg pattern을 이용하기 위해 redirect
    }
}
