package org.sunj.boardproject.repository;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunj.boardproject.domain.BoardVO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Log4j2
class BoardDAOTest {
    private BoardDAO dao;

    @BeforeEach //dao를 항상 test 전에 준비
    public void ready(){
        dao = new BoardDAO();
    }

    @Test
    @DisplayName("게시글 1건 정상적으로 등록되는지 확인")
    public void testInsert() throws Exception{
        BoardVO vo = BoardVO.builder()
                .title("DAO INSERT TEST TITLE")
                .content("this is dao insert test content")
                .regDate(LocalDateTime.now()) //현재 시각으로 지정한다.
                .build();

        Assertions.assertEquals(dao.insert(vo),1); //하나 insert 되었으면 완료.
    }

    @Test
    @DisplayName("현재 게시글의 내용, title, isPublic 수정 가능한지 확인")
    public void testUpdate() throws Exception{
        BoardVO vo = BoardVO.builder()
                .boardno(1L) //이거 지정해야 update할 것을 정할 수 있다.
                .title("DAO UPDATE TEST TITLE")
                .content("this is dao update test content")
                .modDate(LocalDateTime.now())
                .isPublic(true)
                .build();

        Assertions.assertEquals(dao.updateOne(vo),1);
    }

    @Test
    @DisplayName("select를 통해 하나의 게시글을 불러올 수 있는지 확인")
    public void testSelectOne() throws Exception{
        Long boardno = 1L;
        BoardVO vo = dao.selectOne(1L);

        log.info(vo.toString());
    }

    @Test
    @DisplayName("selectAll을 통해 여러개의 게시글을 불러올 수 있는지 확인")
    public void testSelectAll() throws Exception{
        List<BoardVO> list = dao.selectAll();

        for(BoardVO vo : list){
            log.info(vo.toString());
        }
        Assertions.assertEquals(list.size(),2); //지금 2개이므로, list가 다 가져오면 2개여야 함.
    }

    @Test
    @DisplayName("deleteOne을 통해 한개의 게시들을 삭제할 수 있는지 확인")
    public void testDeleteOne() throws Exception{
        Long boardno = 4L;
        Assertions.assertEquals(dao.deleteOne(boardno),1);
    }
}