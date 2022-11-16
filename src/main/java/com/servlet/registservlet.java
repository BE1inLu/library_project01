package com.servlet;

import com.dao.dao;
import com.sql.JDBCUtil;
import com.user.*;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username").trim();
        String password = req.getParameter("passwd").trim();
        String password2 = req.getParameter("passwd2").trim();
        String email = req.getParameter("email").trim();
        String tel = req.getParameter("tel").trim();
        String sex = req.getParameter("sex");

        if (password.equals(password2)==false) {
            resp.setHeader("refrash", "1;url=./regist.html");
            resp.getWriter().write("<script language=javascript>alert('again password error');</script>");
        }

        JDBCUtil db = new JDBCUtil();
        user user = new user(username, password, email, tel, sex);
        dao dao = new dao();

        try {
            Connection conn = db.getConn();
            if (dao.regist(conn, user)) {
                // System.out.println("add user success");
                resp.setHeader("refresh", "1;url=./login.html");
                resp.getWriter().write("<script language=javascript>alert('add user success');</script>");
            }else{
                // System.out.println("add user error");
                resp.setHeader("refresh", "1;url=./regist.html");
                resp.getWriter().write("<script language=javascript>alert('add user error');</script>");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
