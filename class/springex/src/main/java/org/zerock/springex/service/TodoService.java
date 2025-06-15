package org.zerock.springex.service;

import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

public interface TodoService {
    void register(TodoDTO dto);

    //이부분을 page를 가지고 오는 것으로 변경한다.
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    // selectOne 메서드를 사용할 TodoService getOne을 정의한다.
    TodoDTO getOne(Long tno);

    //remove 메서드를 정의하기 위해 Service 인터페이스에 이를 추가한다.
    void remove(Long tno);

    // modify 기능을 정의하기 위해 다음과 같이 modify를 만들어 준다.
    void modify(TodoDTO dto);
}
