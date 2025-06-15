package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    String getTime();

    // dao (mapper)에서 사용하는 메서드를 정의, insert로 정의한다.
    void insert(TodoVO todo);

    //TodoVO 객체를 List로 반환할 수 있도록 selectAll 메서드를 정의한다.
    List<TodoVO> selectAll();

    //상세 조회를 위해 selectOne 메서드를 이용해서 TodoVO 객체를 반환한다.
    TodoVO selectOne(Long tno);

    //delete 메서드를 구현한다.
    void delete(Long tno);

    //update 메서드를 구현한다.
    void update(TodoVO vo);

    // 페이징 처리를 위해 selectList 메서드를 정의한다.
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
