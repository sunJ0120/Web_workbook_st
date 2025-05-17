package org.sunj.boardproject.repository;

import lombok.Cleanup;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.C;
import org.sunj.boardproject.domain.BoardVO;
import org.sunj.boardproject.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
☑️ insert : 하나를 등록, vo 받아서 db에 insert(controller에서 vo로 변환해서 줌)
☑️ updateOne : 하나를 업데이트, vo를 받아서 db에 update(controller에서 vo로 변환해서 줌)
☑️ selectOne : 하나를 조회, vo를 받아서 controller에 전달(controller에서 dto로 변환)
☑️ selectAll : 여러개를 조회(list), vo를 받아서 controller에 전달
deleteOne : 하나를 삭제, boardno를 받아서 vo로 변환해서 db에 delete
 */
@Log4j2
public class BoardDAO {
    public int insert(BoardVO vo) throws Exception{
        String sql = "INSERT INTO board " +
                "(title, content, regDate, isPublic,user_id) " +
                "VALUES " +
                "(?,?,?,?,?)";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vo.getTitle());
        pstmt.setString(2, vo.getContent());
        pstmt.setTimestamp(3, Timestamp.valueOf(vo.getRegDate()));
        pstmt.setBoolean(4, vo.isPublic());
        pstmt.setString(5, vo.getUserId());

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

    /*
    selectOne의 경우, vo를 받아서 controller에 전달한다.
     */
    public BoardVO selectOne(Long boardno) throws Exception{
        String sql = "SELECT * " +
                "FROM board " +
                "WHERE boardno = ?";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, boardno);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        BoardVO vo = null;

        if(rs.next()){
            vo = BoardVO.builder()
                    .boardno(rs.getLong("boardno"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .regDate(rs.getTimestamp("regDate").toLocalDateTime())
                    .modDate(rs.getTimestamp("modDate") != null ?
                            rs.getTimestamp("modDate").toLocalDateTime()
                            : null)
                    .userId(rs.getString("user_id"))
                    .build();
        }else{
            log.error("boardno에 해당하는 객체를 찾을 수 없습니다.");
        }
        return vo;
    }

    /*
    selectAll의 경우, list<vo>를 만들어서 controller에 전달한다.
     */
    public List<BoardVO> selectAll() throws Exception{
        String sql = "SELECT * FROM board";
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<BoardVO> list = new ArrayList<>();

        while(rs.next()){
            BoardVO vo = BoardVO.builder()
                    .boardno(rs.getLong("boardno"))
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .regDate(rs.getTimestamp("regDate").toLocalDateTime())
                    .modDate(rs.getTimestamp("modDate") != null ?
                            rs.getTimestamp("modDate").toLocalDateTime()
                            : null)
                    .isPublic(rs.getBoolean("isPublic"))
                    .userId(rs.getString("user_id"))
                    .build();
            list.add(vo);
        }
        return list;
    }

    /*
    delete의 경우, Long boardno를 받아서 해당 정보를 삭제한다.
     */
    public int deleteOne(Long boardno) throws Exception{
        String sql = "DELETE " +
                "FROM board " +
                "WHERE boardno = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, boardno);

        return pstmt.executeUpdate();
    }
}
