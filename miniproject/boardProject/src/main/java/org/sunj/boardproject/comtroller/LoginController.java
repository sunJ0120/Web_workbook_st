package org.sunj.boardproject.comtroller;
import lombok.extern.log4j.Log4j2;
import org.sunj.boardproject.domain.UserDTO;
import org.sunj.boardproject.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

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
        String auto = req.getParameter("auto"); //auto 자동 로그인 체크 값을 가져온다.

        boolean rememberMe = false; //자동 로그인 체크 여부
        String uuid = null;
        if(auto != null && auto.equals("on")){ //auto가 null이 아니고 on이랑 같을 때
            rememberMe = true; //자동 로그인 체크를 한다.
        }
        //쿠키에 저장할 식별자인 uuid를 생성한다.
        if(rememberMe){
            uuid = UUID.randomUUID().toString();
        }
        //getParameter로 받아온 userId, userPw를 log로 찍어본다.
        log.info("세션 loginInfo에 들어갈 userId : {}", userId);
        log.info("세션 loginInfo에 들어갈 userPw : {}", userPw);
        log.info("세션 loginInfo에 들어갈 uuid : {}", uuid);

        //여기에 service에 id, pw 보내는 과정
        try{
            UserDTO dto = UserService.INSTANCE.login(userId, userPw);
            //filter에 cookie, session이 없으면 여기로 넘어오는 것이므로 여기서 먼저 체크한다.
            if(rememberMe){ //자동 로그인 체크 했을 경우
                //여기에 uuid로 멤버 불러와서 저장하는 과정이 필요하다.
                UserService.INSTANCE.updateUuid(userId, uuid);
                dto.setUuid(uuid);

                //생성된 uuid를 쿠키에 넣어주는 작업을 한다.
                Cookie rememberCookie
                         = new Cookie("remember-me", uuid);

                rememberCookie.setMaxAge(60*60*24*7); //쿠키의 유효기간을 7일로 설정
                rememberCookie.setPath("/"); //path를 설정

                resp.addCookie(rememberCookie); //구성한 쿠키를 넣는다.
            }
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
