package org.zerock.dao;

import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
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

        if(rs.next()){ //꼭 next를 넣어야 한다!
            //vo 객체에 builder로 값을 넣어준다.
            vo = MemberVO.builder()
                    .mid(rs.getString("mid"))
                    .mpw(rs.getString("mpw"))
                    .mname(rs.getString("mname"))
                    .build();

        }
        return vo;
    }
    // 해당 mid에 uuid를 넣기 위함이다.
    public void updateUuid(String mid, String uuid) throws Exception{
        String sql = "UPDATE tbl_member SET uuid = ? WHERE mid = ?";
        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, uuid);
        pstmt.setString(2, mid);

        pstmt.executeUpdate(); //update 이므로 executeUpdate()입력
    }
}
