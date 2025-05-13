package org.zerock.controller;
import lombok.extern.log4j.Log4j2;
import org.zerock.dto.MemberDTO;
import org.zerock.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

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

        String auto = req.getParameter("auto"); //자동 로그인 체크 박스

        boolean rememberMe = false; //자동 로그인 체크 박스가 체크되어 있는지 확인하는 flag
        if (auto != null && auto.equals("on")) {
            rememberMe = true; //auto가 체크 되어 있고 null이 아닐 경우,.
        }

        //rememberMe = true 이면 java.util의 UUID를 이용해서 임의의 번호를 생성한다.
        if(rememberMe){
            String uuid = UUID.randomUUID().toString();
        }
        //실제 로그인 service를 하는 것으로 변경
        try{
            MemberDTO dto = MemberService.INSTANCE.login(mid, mpw);
            //여기가 로그인이 끝나고 session에 저장하기 전 시점이다.
            if(rememberMe){
                String uuid = UUID.randomUUID().toString(); //랜덤한 String의 uuid를 생성한다.
                MemberService.INSTANCE.updateUuid(mid, uuid); //updateUuid를 통해서 uuid를 update한다.
                dto.setUuid(uuid); //dto에 uuid를 넣어준다.

                //생성된 uuid를 쿠키에 넣어주는 작업을 한다.
                 Cookie rememberCookie
                         = new Cookie("remember-me", uuid); //rememberMe라는 이름으로 uuid를 쿠키로 넣어준다.
                rememberCookie.setMaxAge(60*60*24*7); //쿠키의 유효기간을 7일로 설정
                rememberCookie.setPath("/"); //path를 설정

                resp.addCookie(rememberCookie); //resp에 cookie를 추가한다.
            }
            HttpSession session = req.getSession();
            //session에 로그인 결과 정보 저장
            session.setAttribute("loginInfo", dto);

            //list로 redirect
            resp.sendRedirect("/todo/list");
        }catch (Exception e){
            //error로 redirect
            log.error("message : {}", e.getMessage());
            resp.sendRedirect("/login?result=error");
        }
    }
}
