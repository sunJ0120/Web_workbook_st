package org.sunj.boardproject.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.sunj.boardproject.domain.BoardDTO;
import org.sunj.boardproject.domain.BoardVO;
import org.sunj.boardproject.repository.BoardDAO;
import org.sunj.boardproject.util.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum BoardService {
    INSTANCE;

    //사용해야 할 것들은 이렇게 명시해둔다.
    private BoardDAO dao;
    private ModelMapper modelMapper;

    BoardService(){
        dao = new BoardDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public void write(BoardDTO dto) throws Exception{
        //dto -> vo
        BoardVO vo = modelMapper.map(dto, BoardVO.class);
        log.info(vo); //vo 정보 가져오기
        dao.insert(vo); //insert로 넣기
    }
    /*
    dtolist를 조회해서 Controller에 넘기기
    db에서 vo -> dto
     */
    public List<BoardDTO> listAll() throws Exception{
        List<BoardVO> voList = dao.selectAll();
        log.info( "voList = {}",voList);

        //voList -> dtoList
        List<BoardDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    /*
    vo를 가져와서 dto로 구성해서 넘기기
     */
    public BoardDTO get(Long boardno) throws Exception{
        BoardVO vo = dao.selectOne(boardno);
        log.info("vo = {}", vo);

        BoardDTO dto = modelMapper.map(vo, BoardDTO.class);
        return dto;
    }

    /*
    remove 할 객체 번호를 가져와서 dao의 remove 실행
     */
    public void remove(Long boardno) throws Exception{
        dao.deleteOne(boardno);
    }

    /*
    modify
    dto 받아서 vo로 변환
    dao의 modify 부르기
     */
    public void modify(BoardDTO dto) throws Exception{
        BoardVO vo = modelMapper.map(dto, BoardVO.class);
        dao.updateOne(vo);
    }

}
