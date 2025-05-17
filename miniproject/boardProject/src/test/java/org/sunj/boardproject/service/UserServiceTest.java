package org.sunj.boardproject.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sunj.boardproject.domain.UserDTO;

@Log4j2
class UserServiceTest {
    //사용할 것 미리 꺼내둔다.
    private UserService userService;

    @BeforeEach
    public void ready(){
        userService = UserService.INSTANCE; //싱글톤으로 만든 객체 가져오기
    }

    //로그인 잘 동작하는지 test
    @Test
    @DisplayName("UserService의 login test")
    public void TestLogin() throws Exception{
        String userId = "test1";
        String userPw = "1111";
        UserDTO dto = userService.login(userId,userPw);
        log.info("dto = {}", dto);

        Assertions.assertEquals(userId, dto.getUserId());
    }

    //로그인 실패시 null 객체를 return 하는지 test
    @Test
    @DisplayName("UserService의 login fail test")
    public void TestLoginFail() throws Exception{
        String userId = "test1";
        String userPw = "1234";
        UserDTO dto = userService.login(userId,userPw);
        log.info("dto = {}", dto);

        Assertions.assertNull(dto, "잘못된 ID/PW 조합 시 dto는 null이어야 합니다.");
    }

    //차후 쿠키 자동 로그인 기능 구현시, test 추가 예정
}