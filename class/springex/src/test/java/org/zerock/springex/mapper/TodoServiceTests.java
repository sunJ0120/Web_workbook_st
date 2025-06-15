package org.zerock.springex.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    void testRegister() throws Exception {
        //given
        TodoDTO dto = TodoDTO.builder()
                .title("Test.......")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();
        //when
        todoService.register(dto);

        //then
    }

    @Test
    public void testPaging() throws Exception {
        //given
        //이를 톨해 새로운 page 생성
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        //when, getList를 가져왔을때 제대로 가져오는지를 살펴본다.
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        //then
        log.info("responseDTO : {}", responseDTO);

        responseDTO.getDtoList().stream().forEach(todoDTO -> {
            log.info("todoDTO ; {}",todoDTO);
        });
    }
}
