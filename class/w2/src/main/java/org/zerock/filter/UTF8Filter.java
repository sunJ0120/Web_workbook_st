package org.zerock.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/*"})
public class UTF8Filter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("UTF-8 filter.............");
        HttpServletRequest req = (HttpServletRequest) request;
        req.setCharacterEncoding("UTF-8"); //request에 인코딩 UTF-8 설정

        chain.doFilter(request,response);
    }
}
