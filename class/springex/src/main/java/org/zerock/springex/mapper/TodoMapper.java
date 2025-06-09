package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    // dao (mapper)에서 사용하는 메서드를 정의, insert로 정의한다.
    void insert(TodoVO todo);

    //TodoVO 객체를 List로 반환할 수 있도록 selectAll 메서드를 정의한다.
    List<TodoVO> selectAll();

    //상세 조회를 위해 selectOne 메서드를 이용해서 TodoVO 객체를 반환한다.
    TodoVO selectOne(Long tno);
}
