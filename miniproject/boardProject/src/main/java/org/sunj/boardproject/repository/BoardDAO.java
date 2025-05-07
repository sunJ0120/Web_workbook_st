package org.sunj.boardproject.repository;

import lombok.Cleanup;
import org.sunj.boardproject.domain.BoardVO;
import org.sunj.boardproject.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/*
insert : 하나를 등록, vo 받아서 db에 insert(controller에서 vo로 변환해서 줌)
updateOne : 하나를 업데이트, vo를 받아서 db에 update(controller에서 vo로 변환해서 줌)
selectOne : 하나를 조회, vo를 받아서 controller에 전달(controller에서 dto로 변환)
selectAll : 여러개를 조회(list), vo를 받아서 controller에 전달
deleteOne : 하나를 삭제, boardno를 받아서 vo로 변환해서 db에 delete
 */
public class BoardDAO {
    public int insert(BoardVO vo) throws Exception{
        String sql = "INSERT INTO board " +
                "(title, content, regDate, isPublic) " +
                "VALUES " +
                "(?,?,?,?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getContent());
        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getRegDate()));
        pstmt.setBoolean(4, vo.isPublic());

        return pstmt.executeUpdate();
    }

    /*
    modDate를 설정하는 것은 controller에서 진행하고
    dao는 db에 날릴 데이터만 설정한다.
     */
    public int updateOne(BoardVO vo) throws Exception{
        String sql = "UPDATE board SET title=?, " +
                "content=?, " +
                "modDate=?, " +
                "isPublic=? " +
                "WHERE boardno =?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getContent());
        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getModDate()));
        pstmt.setBoolean(4, vo.isPublic());
        pstmt.setLong(5, vo.getBoardno());

        return pstmt.executeUpdate();
    }
}
