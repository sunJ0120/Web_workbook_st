package org.zerock.dao;

import lombok.Cleanup;
import org.zerock.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {

    public MemberVO getWithPassword(String mid, String mpw) throws Exception{
        String sql = "SELECT mid, mpw, mname FROM tbl_member WHERE mid = ? AND mpw = ?";

        MemberVO vo = null;

        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, mid);
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery(); //select 이므로 executeQuery()입력

        //vo 객체에 builder로 값을 넣어준다.
        vo = MemberVO.builder()
                .mid(rs.getString("mid"))
                .mpw(rs.getString("mpw"))
                .mname(rs.getString("mname"))
                .build();

        return vo;
    }
}
