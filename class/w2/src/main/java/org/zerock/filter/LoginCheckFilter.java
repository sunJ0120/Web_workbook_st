package org.zerock.filter;

import lombok.extern.log4j.Log4j2;
import org.zerock.dto.MemberDTO;
import org.zerock.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Log4j2
@WebFilter(urlPatterns = {"/todo/*"})
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.info("Login check filter.........");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        //로그인 정보가 있을 경우 세션 로그인 상태이다
        if(session.getAttribute("loginInfo") != null){
            //session 로그인 정보가 있는지 보기 위함.
            log.info("HttpSession loginInfo : {}", session.getAttribute("loginInfo"));
            chain.doFilter(req,resp);
            return;
        }

        //session에 loginInfo값이 없을 경우
        //remember-me라는 이름의 자동 로그인 쿠키를 찾아야 한다.
        Cookie cookie = findCookie(req.getCookies(), "rememberMe");

        //로그인 정보가 없을 경우 다시 login 페이지로 이동하도록 한다.
        if(cookie == null){
            //cookie 정보가 있는지 보기 위함.
            log.info("cookie가 존재하지 않는 상황");
            resp.sendRedirect("/login");
            return;
        }

        //쿠키가 존재하는 상황이라면
        log.info("cookie가 존재하는 상황");
        //uuid를 잡아서 dto에 넣어야 한다.
        String uuid = cookie.getValue();

        try{
            //데이터베이스에서 uuid를 통해 dto값을 가져와야 한다.
            MemberDTO dto = MemberService.INSTANCE.getByUUID(uuid);

            log.info("쿠키의 값으로 조회한 사용자 정보 dto : {}",dto);
            if(dto == null){
                throw new Exception("Cookie value is not valid");
            }
            //회원 정보를 세션에 추가한다.
            //세션의 경우는 dbㄹㄹ 거치는게 아니여서 바로 dto를 넣는구낭
            session.setAttribute("loginInfo", dto);
            chain.doFilter(req, resp);
        }catch(Exception e){
            //에러가 날 경우, 다시 login으로 redirect 한다.
            e.printStackTrace();
            resp.sendRedirect("/login");
        }
    }
    // 이 로직을 다시 한 번 살펴보기.
    private Cookie findCookie(Cookie[] cookies, String name){
        //cookie가 없을 경우 return null을 한다.
        if(cookies == null || cookies.length == 0){
            log.info("cookie.length : {}", cookies.length);
            return null;
        }

        //리스트를 돌면서, 가장 먼저 나오는 쿠키 값 이름을 찾는다.
        Optional<Cookie> result = Arrays.stream(cookies)
                .filter(ck -> ck.getName().equals(name))
                .findFirst();

        if(result.isPresent()){
            log.info(" result.get() : {}",  result.get());
        }else{
            log.info("result.get의 값이 없습니다.");
        }

        return result.isPresent() ? result.get() : null;
    }
}
