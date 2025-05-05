package org.zerock.jdbcex.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum TodoService {
    INSTANCE;

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService(){
        dao = new TodoDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception{
        //dto -> vo로 변환
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
//        System.out.println("todoVO : " + todoVO);
        log.info(todoVO);
        dao.insert(todoVO); //int를 반환하므로 이를 이용해서 예외처리도 가능하다.
    }

    //[서블릿 컨테이너 - DAO와 Service 연결] ListAll 추가
    public List<TodoDTO> listAll() throws Exception{
        List<TodoVO> voList = dao.selectAll();
        log.info("voList.............");
        log.info(voList);

        //voList -> dtoList 변환 : ModelMapper
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    //[서블릿 컨테이너 - DAO와 Service 연결] 조회를 위한 GET 추가
    public TodoDTO get(Long tno) throws Exception{
        log.info("tno : " + tno);
        TodoVO todoVO = dao.selectOne(tno); //dao의 selectOne 이용해서 todoVO 가져오기
        TodoDTO dto = modelMapper.map(todoVO, TodoDTO.class);

        return dto;
    }

    //[서블릿 컨테이너 - DAO와 Service 연결] 삭제 기능 구현
    public void remove(Long tno) throws Exception{
        log.info("tno : " + tno);
        dao.deleteOne(tno);
    }

    //[서블릿 컨테이너 - DAO와 Service 연결] 수정 기능 구현
    public void modify(TodoDTO todoDTO) throws Exception{
        log.info("todoDTO : {}", todoDTO);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        dao.updateOne(todoVO);
    }
}
