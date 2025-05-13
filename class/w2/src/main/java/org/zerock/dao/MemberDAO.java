package org.zerock.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.zerock.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j2
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

    //UUID로 회원 정보를 가져오는 메서드를 작성해서 해당 사용자의 정보를 로딩해온다.
    public MemberVO selectUUID(String uuid) throws Exception{
        String query = "SELECT mid, mpw, mname FROM tbl_member WHERE uuid=?"; //uuid로 정보를 가져온다.

        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, uuid);

        @Cleanup ResultSet rs = pstmt.executeQuery();
        MemberVO vo = null;

        if(rs.next()){
            //받아온 정보로 vo객체를 생성한다.
            vo = MemberVO.builder()
                    .mid(rs.getString("mid"))
                    .mpw(rs.getString("mpw"))
                    .mname(rs.getString("mname"))
                    .uuid(uuid)
                    .build();
        }
        return vo; //vo객체를 리턴한다.
    }
}
