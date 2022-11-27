package com.servlet;

import java.io.IOException;
import java.util.Date;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.booklog.booklog;
import com.dao.dao;
import com.sql.JDBCUtil;

public class borrowservlet extends HttpServlet {

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
        int userid=(int)Integer.parseInt(useridstr);
        int bookid=(int)Integer.parseInt(bookidstr);
        long borrowday = (long)Integer.parseInt(req.getParameter("borrowday"));
        long borrowday_text = borrowday * 86400000;// 备注：时间戳1天=86400000

        // 获取借出时间和归还时间
        Date borrowDate = new Date();
        Date receivDate = new Date(borrowDate.getTime() + borrowday_text);

        // 创建一个booklog,用于dao操作
        booklog booklog = new booklog(bookid, userid, borrowDate, receivDate, false);

        // booklogDAO操作调用，回传参数，后台数据库操作，返回布尔类
        dao dao = new dao();
        JDBCUtil db = new JDBCUtil();
        if (bookidstr != "") {
            try {
                Connection conn = db.getConn();

                if (dao.borrow_book(conn, booklog)) {
                    // 返回信息,跳转到主页
                    conn.close();
                    resp.getWriter().write("<script language=javascript>alert('借阅书籍成功');</script>");
                    resp.sendRedirect("./borrowpage.jsp");
                    
                }
                conn.close();
            } catch (Exception e) {
                System.out.println("error");
                resp.getWriter().write("<script language=javascript>alert('error');</script>");
                e.printStackTrace();
                resp.setHeader("refresh", "1;url=./borrowpage.jsp");
                // 返回报错信息
            }
        }else{
            resp.setHeader("refresh", "1;url=./borrowpage.jsp");
            resp.getWriter().write("<script language=javascript>alert('书籍id未填写');</script>");
        }
    }

}
