package org.zerock.controller;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "loginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login Get..........");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login Post..........");
        String mid = req.getParameter("mid"); //아이디
        String mpw = req.getParameter("mpw"); //비번

        String str = mid + mpw;
        HttpSession session = req.getSession();

        //session에 정보 저장
        session.setAttribute("loginInfo", str);
        //list로 redirect
        resp.sendRedirect("/todo/list");
    }
}
