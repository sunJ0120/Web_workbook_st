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

/*
등록의 경우. get & post를 둘다 쓴다.
1. get : 화면을 띄운다.
2. post : 버튼 누르면 post, 화면에 req.getParameter로 받아서 dto를 만들어서 service에 write
 */
@Log4j2
@WebServlet(name = "writeController", urlPatterns = "/board/write")
public class WriteController extends HttpServlet {
    private BoardService service = BoardService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("/board/write GET......");
        req.getRequestDispatcher("/WEB-INF/board/write.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDTO dto = BoardDTO.builder()
                .title(req.getParameter("title"))
                .content(req.getParameter("content"))
                .regDate(LocalDateTime.now())
                .isPublic(Boolean.parseBoolean(req.getParameter("isPublic")))
                .build();
        log.info("board/write POST.......");

        //db에 보내기 위해 service를 이용한다.
        try{
            service.write(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect("/board/list"); //prg를 적용하기 위해서 sendRedirect를 한다.
    }
}
