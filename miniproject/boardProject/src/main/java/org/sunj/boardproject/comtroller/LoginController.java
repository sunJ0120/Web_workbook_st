package org.sunj.boardproject.comtroller;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
마찬가지로, get으로 화면을 띄우고 post로 연결해야 한다.
 */
@Log4j2
@WebServlet(name = "loginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login Get..........");
        /*
        getDispatcher + forward를 통해서 jsp로 전송
         */
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("login Post..........");

        String userId = req.getParameter("userId");
        String userPw = req.getParameter("userPw");

        //여기에 service에 id, pw 보내는 과정


        //여기에서 session 생성해서 넣는 과정

    }
}
