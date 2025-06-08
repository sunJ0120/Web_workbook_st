package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;


@Log4j2
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO dto){
        log.info("modelMapper : {}", modelMapper);
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        //modelMapper를 통해서 dto를 vo로 변환한다.
        log.info("todoVO : {}", vo);
        todoMapper.insert(vo);
    }
}
