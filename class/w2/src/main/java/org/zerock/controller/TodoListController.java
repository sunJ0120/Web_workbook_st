package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.dto.TodoDTO;
import org.zerock.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
Controller의 경우, Service의 기능을 쓴다. 이걸 model에 전달하고 view에 전달한다.
 */
@WebServlet(name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("todoList............");

        try{
            List<TodoDTO> dtoList = todoService.listAll();
            req.setAttribute("dtoList", dtoList); //model에 추가
            req.getRequestDispatcher("/WEB-INF/todo/list.jsp") //getRequestDispatcher를 통해 서블릿 -> jsp
                    .forward(req, resp);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }
}
