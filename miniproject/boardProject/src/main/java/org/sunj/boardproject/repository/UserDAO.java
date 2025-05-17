package org.sunj.boardproject.repository;

import lombok.Cleanup;
import org.sunj.boardproject.domain.UserVO;
import org.sunj.boardproject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
여기서는, ID, PW를 이용해서 SELECT를 하는 login()을 구현한다.
 */
public class UserDAO {

    public UserVO getWithPassword(String userId, String userPw) throws Exception{
        String sql = "SELECT user_id, user_pw, user_name FROM users " +
                "WHERE user_id = ? AND user_pw = ?";

        UserVO vo = null; //Builder Pattern을 통해서 생성한다.

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userId);
        pstmt.setString(2, userPw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            vo = UserVO.builder()
                    .userId(rs.getString("user_id"))
                    .userPw(rs.getString("user_pw"))
                    .userName(rs.getString("user_name"))
                    .build();
        }
        return vo;
    }
}
