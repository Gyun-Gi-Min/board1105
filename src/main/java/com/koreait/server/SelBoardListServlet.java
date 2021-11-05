package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/list") //json 형태로 뿌려주는게 목표다.
public class SelBoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {



        List<boardVO> list = boardDAO.selBoardList();
        //list 객체화
        Gson gson = new Gson(); //json으로 바꿔야지 gson의 기능을 쓰자.
        String json = gson.toJson(list); //json으로 바꾸는거.

        res.setContentType("text/plain;charset=UTF-8"); //한글깨짐 방지

        PrintWriter out = res.getWriter(); //응답하는거
        out.print(json); //json형태로 응답함.

    }

}
