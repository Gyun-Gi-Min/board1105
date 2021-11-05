package com.koreait.server;

import com.google.gson.Gson;
import com.mysql.cj.log.Log;
import com.sun.org.apache.regexp.internal.RE;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ins")
public class InsBoardServlet extends HttpServlet {

    @Override   //post하는이유는 양이 많을떄!
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            //제목 내용 작성자 넘어온다
        String json = Utils.getJson(req); //
        Gson gson = new Gson(); //gson 객체 생성하고
        //보드에서 받아야지.
        boardVO param = gson.fromJson(json,boardVO.class);
        //값찍어

        int result =boardDAO.insBoard(param);


        ResultVO resultVO = new ResultVO();
        resultVO.setResult(result);
        String resultJson = gson.toJson(resultVO);

        PrintWriter out = res.getWriter();
        out.print(resultJson);


    }
}
