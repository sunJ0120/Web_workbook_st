package org.sunj.boardproject.comtroller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
logout의 경우는 따로 페이지를 띄울 것이 없기 때문에 바로 post로 연결한다.
 */

@Log4j2
@WebServlet(name = "logoutController", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("log out doPost()");
        HttpSession session = req.getSession(); //session을 가져와야 처리가 가능하다.

        session.removeAttribute("loginInfo"); //loginInfo 처리해서 로그아웃
        session.invalidate(); //세션을 무효화 시킨다. 이게 없으면 세션이 남아있다.

        resp.sendRedirect("/home"); //home으로 redirect 한다.
    }
}
