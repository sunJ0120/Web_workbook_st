package org.zerock.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.dto.TodoDTO;
import org.zerock.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
READ 하면서 조회 쿠키를 조회하고, 추가해야 하므로 TodoReadController에 해당 내용을 추가한다.
 */
@Log4j2
@WebServlet(name = "todoReadController", value = "/todo/read")
public class TodoReadController extends HttpServlet {
    //Controller에선 Service를 써야 하므로 Service를 부른다.
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            Long tno = Long.parseLong(req.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);

            //데이터를 model에 담기
            req.setAttribute("dto", todoDTO);

            //해당 페이지를 담는 역할을 하는 쿠키 안에 있는지를 확인하기 위해 쿠키를 찾아야 한다.
            Cookie viewTodoCookie = findCookie(req.getCookies(), "viewTodos");
            String todoListStr = viewTodoCookie.getValue(); //viewTodoCookie라는 쿠키의 value String 가져옴. 쿠키의 내용을 검사하는 로직이다.
            boolean exist = false;

            if(todoListStr != null && todoListStr.indexOf(tno+"-") >= 0){
                //tno+"-" 여기 해당하는게 하나라도 있을 경우 0 이상이랬음, 즉 0보다 크다는건 쿠키 안에 있다는 의미이다.
                exist = true; //존재한다는 의미로 flag를 바꿔준다.
            }

            log.info("exist : {}", exist);

            if(!exist){ //해당 tno의 쿠키를 조회한 적이 없는 경우, 다시 정보를 구성해서 넘긴다.
                todoListStr += tno + "-"; //Cookie에 넣을 번호를 추가한다.
                viewTodoCookie.setValue(todoListStr); //앞에서 만든 value를 추가한다.
                viewTodoCookie.setMaxAge(60*60*24); //쿠키의 유효기간을 1일로 설정
                viewTodoCookie.setPath("/"); // /라는 기본 path에 추가한다.
                resp.addCookie(viewTodoCookie); // 해당 cookie를 추가한다.
            }

            req.getRequestDispatcher("/WEB-INF/todo/read.jsp")
                    .forward(req, resp);

        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }

    public Cookie findCookie(Cookie[] cookies, String cookieName){
        //Cookie 배열 안에서 해당 쿠키가 있는지를 찾아야 한다.
        Cookie targetCookie = null;

        if(cookies != null && cookies.length > 0){ //일단 이렇다는건 쿠키가 있다는 의미이므로, 존재하는지 찾아볼 수 있는 것이다.
            for(Cookie ck : cookies){
                if(ck.getName().equals(cookieName)){ //이미 쿠키가 존재한다.
                    targetCookie = ck;
                    break;
                }
            }
        }

        if(targetCookie == null){
            targetCookie = new Cookie(cookieName, ""); //일단 여기서는 찾는거만 진행하므로, 해당 cookie의 value는 없는 상태로 넘긴다.
            targetCookie.setPath("/"); //targetCookie가 없을 경우(아직 저장된 쿠키가 아닐 경우) / 라는 기본 path에 추가한다는 의미임.
            targetCookie.setMaxAge(60*60*24); //쿠키의 유효기간을 1일로 설정
        }
        return targetCookie;
    }
}
