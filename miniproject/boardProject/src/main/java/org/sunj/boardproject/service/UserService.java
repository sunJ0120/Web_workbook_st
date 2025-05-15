package org.sunj.boardproject.service;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.sunj.boardproject.repository.UserDAO;

@Log4j2
public enum UserService {
    INSTANCE;

    //필요한 것들은 미리 빼둔다.
    private UserDAO dao; //dao에 보내기 위해
    private ModelMapper modelMapper; //modelMapper를 사용

    UserService(){

    }
}
