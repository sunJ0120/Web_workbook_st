package org.sunj.boardproject.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunj.boardproject.domain.BoardVO;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}