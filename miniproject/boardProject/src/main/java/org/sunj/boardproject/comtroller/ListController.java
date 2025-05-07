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
import java.util.List;

/*
service에서 listAll로 받아오기
이걸 list로 req(model)에 저장
dispather
 */
@Log4j2
@WebServlet(name = "listController", urlPatterns = "/board/list")
public class ListController extends HttpServlet {
    private BoardService service = BoardService.INSTANCE;
    //controller에서 service를 사용한다.
    //dto vo 처리는 dao에서 다 해주므로 몰라도 된다.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("board list..........");
        try{
            List<BoardDTO> dtoList = service.listAll();
            req.setAttribute("dtoList", dtoList);
            req.getRequestDispatcher("/WEB-INF/board/list.jsp")
                    .forward(req, resp);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ServletException("list error");
        }
    }
}
