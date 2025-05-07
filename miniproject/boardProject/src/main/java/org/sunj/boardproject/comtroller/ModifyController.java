package org.sunj.boardproject.comtroller;

import lombok.extern.log4j.Log4j2;
import org.sunj.boardproject.domain.BoardDTO;
import org.sunj.boardproject.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Log4j2
@WebServlet(name = "modifyController", urlPatterns = "/board/modify")
public class ModifyController extends HttpServlet {

    private BoardService service = BoardService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long boardno = Long.parseLong(req.getParameter("boardno"));
        try{
            BoardDTO dto = service.get(boardno);
            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/WEB-INF/board/modify.jsp")
                    .forward(req, resp);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify error");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDTO dto = BoardDTO.builder()
                .title(req.getParameter("title"))
                .modDate(LocalDateTime.now())
                .content(req.getParameter("content"))
                .boardno(Long.parseLong(req.getParameter("boardno")))
                .regDate(LocalDateTime.parse(req.getParameter("regDate")))
                .build();

        log.info("modify dto = {}", dto);
        try{
            service.modify(dto);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("modify error");
        }
        resp.sendRedirect("/board/list");
    }
}
