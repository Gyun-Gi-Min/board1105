package com.koreait.server;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/one")
public class SelBoardDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //iboard 키값이고 키값을 알면 value를 알수있음. 12번이면 string 12가 넘어오는것.
        int iboard = Integer.parseInt( req.getParameter("iboard"));

        boardVO vo = new boardVO();
        vo.setIboard(iboard);

        boardVO result = boardDAO.selboard(vo);
        Gson gson =new Gson();
        String json = gson.toJson(result);

        res.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.print(json);

    }


}
