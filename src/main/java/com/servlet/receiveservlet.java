package com.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booklog.booklog;
import com.dao.dao;
import com.sql.JDBCUtil;

public class receiveservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // borrowpageservlet，用于借书页后台信息操作
        req.setCharacterEncoding("utf-8");

        // 获取前台回传的值
        String useridstr = req.getParameter("borrowuserid");
        String bookidstr = req.getParameter("bookid");

        // str转int，用于后续操作
        int userid = (int) Integer.parseInt(useridstr);
        int bookid = (int) Integer.parseInt(bookidstr);

        // 创建一个booklog,用于dao操作
        booklog booklog = new booklog();
        booklog.setBookid(bookid);
        booklog.setUserid(userid);

        // booklogDAO操作调用，回传参数，后台数据库操作，返回布尔类
        dao dao = new dao();
        JDBCUtil db = new JDBCUtil();

        if (bookidstr != "") {
            try {
                Connection conn = db.getConn();
                if(dao.Receive_book(conn, booklog)){
                    conn.close();
                    resp.getWriter().write("<script language=javascript>alert('归还书籍成功');</script>");
                    resp.sendRedirect("./receivepage.jsp");
                }
                conn.close();
            } catch (Exception e) {
                System.out.println("receivepage error");
                e.printStackTrace();
            }
        }else{
            resp.setHeader("refresh", "1;url=./receivepage.jsp");
            resp.getWriter().write("<script language=javascript>alert('书籍id未填写');</script>");
        }
    }
}
