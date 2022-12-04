package com.servlet;

import com.dao.dao;
import com.sql.*;
import com.user.*;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null) {

            JDBCUtil db = new JDBCUtil();
            user user = new user(username, password);
            dao dao = new dao();

            try {
                Connection conn = db.getConn();
                user textuser=dao.login(conn, user);
                if (textuser != null) {
                    req.getSession().setAttribute("superuser", textuser.getSuperuser());
                    req.getSession().setAttribute("userid", textuser.getUserid());
                    req.getSession().setAttribute("username", textuser.getUsername());
                    resp.sendRedirect("../jsp-pages/page-jsp/homepage/homepage.jsp");
                } else {
                    System.out.println("error user");
                    resp.setHeader("refresh", "1;url=./login.html");
                    resp.getWriter().write("<script language=javascript>alert('ERROR USER');</script>");
                }
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error user");
            resp.setHeader("refresh", "1;url=./login.html");
            resp.getWriter().write("<script language=javascript>alert('ERROR USER');</script>");
        }
    }
}
