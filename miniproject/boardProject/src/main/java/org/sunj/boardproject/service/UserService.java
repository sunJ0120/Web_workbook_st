package org.sunj.boardproject.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.sunj.boardproject.domain.UserDTO;
import org.sunj.boardproject.domain.UserVO;
import org.sunj.boardproject.repository.UserDAO;
import org.sunj.boardproject.util.MapperUtil;

@Log4j2
public enum UserService {
    INSTANCE;

    //필요한 것들은 미리 빼둔다.
    private UserDAO dao; //dao에 보내기 위해
    private MapperUtil mapperUtil; //modelMapper를 사용

    UserService(){
        dao = new UserDAO();
        mapperUtil = MapperUtil.INSTANCE;
    }

    //일반 로그인 기능을 위한 메서드 마련
    //여기서 ModelMapper를 이용해서 vo를 dto로 반환한다.
    public UserDTO login(String userId, String userPw) throws Exception{
        UserVO vo = dao.getWithPassword(userId, userPw);
        UserDTO dto = mapperUtil.mapOrNull(vo, UserDTO.class);

        return dto; //dto를 리턴한다
    }

    //이 밑 부분에는 자동 로그인을 위한 uuid 관련 메서드들을 넣는다.
    public void updateUuid(String userId, String uuid) throws Exception{
        dao.updateUuid(userId, uuid); //dao의 updateUuid로 연결한다.
    }
}
