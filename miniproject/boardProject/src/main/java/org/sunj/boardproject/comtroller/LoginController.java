package org.sunj.boardproject.comtroller;
import lombok.extern.log4j.Log4j2;
import org.sunj.boardproject.domain.UserDTO;
import org.sunj.boardproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        //getParameter로 받아온 userId, userPw를 log로 찍어본다.
        log.info("세션 loginInfo에 들어갈 userId : {}", userId);
        log.info("세션 loginInfo에 들어갈 userPw : {}", userPw);

        //여기에 service에 id, pw 보내는 과정
        try{
            UserDTO dto = UserService.INSTANCE.login(userId, userPw);
            //세션 공간 생성
            HttpSession session = req.getSession();
            //이렇게 dto를 한 번에 넣어주면 된다.
            session.setAttribute("loginInfo", dto);

            //세션에 저장된 loginInfo를 가져와서 확인
            UserDTO dto2 = (UserDTO) session.getAttribute("loginInfo");
            log.info("세션 loginInfo: {}", dto2);

            resp.sendRedirect("/board/list"); //list로 바로 redirect
        }catch (Exception e){
            //error로 redirect
            log.error("message : {}", e.getMessage());
            resp.sendRedirect("/login?error=true"); //error가 발생하면 login으로 redirect
        }
    }
}
