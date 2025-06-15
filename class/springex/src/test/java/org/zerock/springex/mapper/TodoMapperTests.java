package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.awt.image.VolatileImage;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTests {
    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime() {
        //<select id="getTime" resultType="string"> 이므로 todoMapper.getTime()로 test한다.
        log.info("todoMapper.getTime() = {}", todoMapper.getTime());
    }

    @Test
    void testInsert() throws Exception {
        //given
        TodoVO vo = TodoVO.builder()
                .title("새로운 할 일")
                .dueDate(LocalDate.of(2022, 10, 10))
                .writer("user00")
                .build();
        //when
        todoMapper.insert(vo);

        //then
    }

    @Test
    public void testSelectAll(){
        //todoMapper로 정의한 인터페이스에서 selectAll 메서드를 호출해서, mapper.xml에서 정의한 쿼리를 실행한다.
        List<TodoVO> voList = todoMapper.selectAll();

        //voList에 담긴 vo 리스트들을 출력한다.
        voList.forEach(vo -> {
            log.info("todoVO : {}", vo);
        });
    }

    @Test
    public void testSelectOne() {
        //tno가 1인 TodoVO 객체를 가져온다.
        TodoVO todoVO = todoMapper.selectOne(3L);

        //가져온 TodoVO 객체를 출력해서 test 한다.
        log.info("todoVO : {}", todoVO);
    }

    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }
}
