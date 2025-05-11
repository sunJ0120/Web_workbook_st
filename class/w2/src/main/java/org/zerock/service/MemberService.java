package org.zerock.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.zerock.dao.MemberDAO;
import org.zerock.domain.MemberVO;
import org.zerock.dto.MemberDTO;
import org.zerock.util.MapperUtil;

/*
dao에서 받은 vo를 dto로 변환하는 기능을 포함
 */
@Log4j2
public enum MemberService {
    INSTANCE;
    //사용할 것들 미리 빼둔다.
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService(){
        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public MemberDTO login(String mid, String mpw) throws Exception{
        MemberVO vo = dao.getWithPassword(mid, mpw);
        //ModelMapper를 이용해서 vo를 dto로 변환한다.
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);

        return dto; //급 궁금한건데, 이거 dto를 바로 리턴하는 걸로 로그인 끝내도 되나? 정보만 가져와도 되는것임?
    }
}
