package org.sunj.boardproject.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunj.boardproject.domain.UserVO;

@Log4j2
class UserDAOTest {
    //dao가 필요하므로, 항상 사전에 준비해둔다.
    private UserDAO dao;
    @BeforeEach
    public void ready(){
        dao = new UserDAO();
    }

    @Test
    @DisplayName("UserDAO의 getter 기능 test")
    public void getWithPasswordTest() throws Exception{
        //parameter로 쓰이는 userId, userPw 가져와서 해보기
        String userId = "test1";
        String userPw = "1111";

        UserVO vo = dao.getWithPassword(userId,userPw);
        log.info("vo = {}", vo);
        Assertions.assertEquals(userId, vo.getUserId());
    }

    @Test
    @DisplayName("잘못된 id, pw 입력시 null 반환 하는지 check")
    public void getWithPasswordFailTest() throws Exception{
        //parameter로 쓰이는 userId, userPw 가져와서 해보기
        String userId = "test1";
        String userPw = "1234";

        UserVO vo = dao.getWithPassword(userId,userPw);
        log.info("vo = {}", vo);
        Assertions.assertNull(vo, "잘못된 ID/PW 조합 시 vo는 null이어야 합니다.");
    }
}