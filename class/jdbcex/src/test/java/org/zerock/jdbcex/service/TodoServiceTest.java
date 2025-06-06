package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dto.TodoDTO;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class TodoServiceTest {
    private TodoService todoService;

    @BeforeEach
    public void ready(){
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister() throws Exception{
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        log.info("---------------------------------"); //테스트 코드의 Log4j2 설정 확인
        log.info(todoDTO);

        todoService.register(todoDTO);
    }
}