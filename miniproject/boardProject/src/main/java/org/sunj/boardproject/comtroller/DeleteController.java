package org.sunj.boardproject.comtroller;

import lombok.extern.log4j.Log4j2;
import org.sunj.boardproject.service.BoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "deleteController", urlPatterns = "/board/delete")
public class DeleteController extends HttpServlet {

    private BoardService service = BoardService.INSTANCE;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long boardno = Long.parseLong(req.getParameter("boardno"));

        try{
            service.remove(boardno);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("delete error");
        }
        resp.sendRedirect("/board/list");
    }
}
