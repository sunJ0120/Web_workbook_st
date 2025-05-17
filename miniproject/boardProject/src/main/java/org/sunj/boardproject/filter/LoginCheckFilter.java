package org.sunj.boardproject.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/board/*"}) //현재는 board를 기준으로만 잡음, 차후 mypage 추가시 추가 예정
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter~~~~");
        HttpServletRequest req = (HttpServletRequest) servletRequest; //session을 가져오려면, 반드시 HttpServletRequest로 형변환 해야함
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(); //세션 공간을 가져옴. 이게 이 안에 LoginInfo가 있는 지를 보면 된다.

        if(session.getAttribute("loginInfo") != null){
            //session 안에 로그인 정보가 있는지를 보기 위함이다.
            log.info("HttpSession loginInfo : {}", session.getAttribute("loginInfo"));
            chain.doFilter(req, resp); //chain을 통해 다음 필터로 넘어간다.
            return;
        }else{
            //일단 이렇게 해두고, 나중에 cookie 자동 로그인까지 구현시 cookie까지 없으면 login으로 가는 것으로 한다.
            resp.sendRedirect("/login");
        }
        //여기에 이제 loginInfo값이 없을 경우 쿠키값을 가져와서 loginInfo에 넣는 방식을 구현하면 된다.
    }
}
