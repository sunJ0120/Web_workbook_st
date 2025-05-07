package org.sunj.boardproject.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.sunj.boardproject.domain.BoardDTO;
import org.sunj.boardproject.domain.BoardVO;
import org.sunj.boardproject.repository.BoardDAO;
import org.sunj.boardproject.util.MapperUtil;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
class BoardServiceTest {
    //사용해야 할 것들은 이렇게 명시해둔다.
    private BoardService service;
    private ModelMapper modelMapper;

    @BeforeEach
    public void ready(){
        service = BoardService.INSTANCE;
        modelMapper = MapperUtil.INSTANCE.get();
    }

    @Test
    @DisplayName("Service의 write test")
    public void writeTest() throws Exception{
        BoardDTO dto = BoardDTO.builder()
                .title("Service write test....")
                .content("this is for test that write method of service")
                .regDate(LocalDateTime.now())
                .isPublic(true)
                .build();

        service.write(dto);
    }

    @Test
    @DisplayName("Service의 listAll test")
    public void listAllTest() throws Exception{
        List<BoardDTO> dolist = service.listAll();

        for(BoardDTO dto : dolist){
            log.info(dto.toString());
        }

        Assertions.assertEquals(dolist.size(), 4);
    }

    @Test
    @DisplayName("Service의 get test")
    public void getTest() throws Exception{
        BoardDTO dto = service.get(2L);
        log.info("dto = {}", dto);
        Assertions.assertEquals(dto.getBoardno(), 2L);
    }

    @Test
    @DisplayName("Service의 remove test")
    public void deleteTest() throws Exception{
        service.remove(6L);
    }

    @Test
    @DisplayName("Service의 update test")
    public void modifyTest() throws Exception{
        BoardDTO dto = BoardDTO.builder()
                .boardno(3L)
                .title("modify test.....")
                .content("this is for test that modify method of service")
                .modDate(LocalDateTime.now())
                .isPublic(true)
                .build();

        service.modify(dto);
    }
}