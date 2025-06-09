package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public List<TodoDTO> getAll() {
        // todoMapper로 정의한 인터페이스에서 selectAll 메서드를 호출해서, modelMapper를 이용해서 이를 vo에서 dto로 변환한다.
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
}
