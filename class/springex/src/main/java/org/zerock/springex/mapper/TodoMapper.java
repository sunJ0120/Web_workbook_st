package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;

public interface TodoMapper {
    String getTime();

    // dao (mapper)에서 사용하는 메서드를 정의, insert로 정의한다.
    void insert(TodoVO todo);
}
