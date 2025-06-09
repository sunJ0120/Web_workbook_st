package org.zerock.springex.service;

import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO dto);

    List<TodoDTO> getAll();

    // selectOne 메서드를 사용할 TodoService getOne을 정의한다.
    TodoDTO getOne(Long tno);
}
