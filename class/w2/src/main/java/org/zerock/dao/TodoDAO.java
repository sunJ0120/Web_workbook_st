package org.zerock.dao;

import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
import org.zerock.domain.TodoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public String getTime(){
        String now = null;
        try (Connection connection = ConnectionUtil.INTSTANCE.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("select now()");
             ResultSet rs = pstmt.executeQuery();
        ) {
            if(rs.next()){
                now = rs.getString(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception{
        String now = null;
        @Cleanup Connection connection = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement("select now()");
        @Cleanup ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            now = rs.getString(1);
        }

        return now;
    }

    public void insert(TodoVO vo) throws Exception{
        String sql = "INSERT INTO tbl_todo (title, dueDate, finished) VALUES (?,?,?)";

        @Cleanup Connection connection = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1,vo.getTitle());
        pstmt.setDate(2, Date.valueOf(vo.getDueDate()));
        pstmt.setBoolean(3, vo.isFinished());

        pstmt.executeQuery();
    }

    public List<TodoVO> selectAll() throws Exception{
        String sql = "SELECT * FROM tbl_todo";

        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<TodoVO> list = new ArrayList<>();

        while (rs.next()) {
            TodoVO vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();

            list.add(vo);
        }

        return list;
    }

    public TodoVO selectOne(Long tno) throws Exception{
        String sql = "SELECT * FROM tbl_todo WHERE tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1, tno);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        TodoVO vo = null;

        if(rs.next()){
            vo = TodoVO.builder()
                    .tno(rs.getLong("tno"))
                    .title(rs.getString("title"))
                    .dueDate(rs.getDate("dueDate").toLocalDate())
                    .finished(rs.getBoolean("finished"))
                    .build();
        }

        return vo;
    }

    public void deleteOne(Long tno) throws Exception{
        String sql = "DELETE FROM tbl_todo WHERE tno = ?";

        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setLong(1,tno);
        pstmt.executeQuery();
    }

    public void updateOne(TodoVO todoVO)throws Exception {
        String sql = "UPDATE tbl_todo SET title = ?, dueDate = ?, finished = ? WHERE tno = ?";
        @Cleanup Connection conn = ConnectionUtil.INTSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1,todoVO.getTitle());
        pstmt.setDate(2, Date.valueOf(todoVO.getDueDate()));
        pstmt.setBoolean(3, todoVO.isFinished());
        pstmt.setLong(4, todoVO.getTno());

        pstmt.executeUpdate();
    }
}
