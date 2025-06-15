package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
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
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        // pageRequestDTO의 경우는 page, size, skip을 정한다.

        // 이 부분이 limit로 선택하는 부분이다.
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        //voList를 dtoList로 변환
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        //getCount를 통해 전체 갯수를 가져온다.
        int total = todoMapper.getCount(pageRequestDTO);

        //여기 로직이 지금 문제가 있으니, 어디가 문제인지 확인해보자.

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        log.info("확인용 PageResponseDTO 생성 결과: {}", pageResponseDTO);

        //이걸 통해 prev, next, start, end 등을 전부 계산 가능하다.
        return pageResponseDTO;
    }

    //selectOne을 사용하는 getOne 메서드를 정의한다.
    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO vo = todoMapper.selectOne(tno);
        // service에서 vo를 dto로 변환해야 한다.
        TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
        return dto;
    }

    @Override
    public void remove(Long tno) {
        log.info("remove tno: {}", tno);
        //service의 mapper의 delete를 연결한다.
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO dto) {
        //service에서는 dto를 받아서 vo로 변환한다.
        TodoVO vo = modelMapper.map(dto, TodoVO.class);
        todoMapper.update(vo);
    }
}
