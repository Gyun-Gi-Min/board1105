package com.koreait.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class boardDAO {

    public static boardVO selboard(boardVO param){

        boardVO vo =null;
        Connection con = null;
        PreparedStatement ps =null;
        ResultSet rs = null;  //select에선 ResultSet 써야쥐
        String sql = "SELECT * FROM t_board WHERE iboard = ? ";
        //iboard 값에 1123123이런수 넣으면 0개나옴(없는값). 그러므로 경우의수 2가지. 나오냐? 안나오냐?

        try {
            // con , ps ,rs 지정
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            rs = ps.executeQuery(); //

            if(rs.next()){  //쿼리수만큼 검색하는거
                vo = new boardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setCtnt(rs.getString("ctnt"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);  //닫아줘야지
        }
        return vo;

    }



    public static int insBoard(boardVO param){
        //쿼리문 써야지
        Connection con =null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_board (title, ctnt, writer) VALUES (?, ?, ?)";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);

            ps.setString(1,param.getTitle());
            ps.setString(2,param.getCtnt());
            ps.setString(3,param.getWriter());

           // ps.executeQuery();  //return  ResultSet >> select만쓴다
            int result = ps.executeUpdate(); // return int >>영향받은행.
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }
        return 0;

    }






    public static List<boardVO> selBoardList(){  //() 아무값이 안들어가있다면 정보를 다 주겠다는 말.
       List<boardVO> list = new ArrayList(); //list를 객체화

        Connection con = null;   // 데이터베이스 - 자바 다리역할
        PreparedStatement ps =null;  // 쿼리문실행
        ResultSet rs = null; //select했을때 사용함. 담는 공간이라고 생각.
        String sql = "select iboard, title, writer, rdt from t_board order by iboard DESC ";

        //스코프 문제로 인해 try{} 전에 적어줌. try안에 적어준다면 finally에서 못 닫겠네.
        try{
            con = DbUtils.getCon(); //데이터베이스에 연결
            ps= con.prepareStatement(sql); //sql 쿼리문 실행
            rs= ps.executeQuery(); //select때 쓰는것. 공간에 담는다? 저장? 보여줌?

            while (rs.next()){ //데이터베이스10줄있다고 하면 10줄 다 읽을때 까지 돌리는 while문
                boardVO vo = new boardVO(); //객체화  -- 주소값을 정하는거지. 값넣으려고
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setWriter(rs.getString("writer"));
                vo.setRdt(rs.getString("rdt"));
                list.add(vo); // 위에 정보들을 list에 담음.
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs); // 다돌리면 list로 DbUtils 종료하고 list로 반환.
        }
        return list;
    }


}
