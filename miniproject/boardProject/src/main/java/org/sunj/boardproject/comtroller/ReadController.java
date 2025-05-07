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

@Log4j2
@WebServlet(name = "readController", urlPatterns = "/board/read")
public class ReadController extends HttpServlet {
    private BoardService boardService = BoardService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //파라미터 가져오기
        Long boardno = Long.parseLong(req.getParameter("boardno"));

        try{
            BoardDTO dto = boardService.get(boardno);
            req.setAttribute("dto", dto);
            req.getRequestDispatcher("/WEB-INF/board/read.jsp")
                    .forward(req, resp);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new ServletException("read error");
        }
    }
}
