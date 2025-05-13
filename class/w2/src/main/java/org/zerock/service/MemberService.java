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
    //자동 로그인을 위해 uuid를 update 하는 method
    public void updateUuid(String mid, String uuid) throws Exception{
        dao.updateUuid(mid, uuid);
    }

    //DAO에서 UUID로 회원 정보를 가져오는 메서드를 작성해서 해당 사용자의 정보를 로딩해온다.
    public MemberDTO getByUUID(String uuid) throws Exception{
        MemberVO vo = dao.selectUUID(uuid);
        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);

        return dto; //dto를 리턴한다.
    }
}
