package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.book;
import com.dao.dao;
import com.sql.JDBCUtil;

public class bookservlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String bookname = req.getParameter("bookname");

        book newbook=new book();
        newbook.setBookname(bookname);

        JDBCUtil db = new JDBCUtil();
        List<book> booklist = null;

        dao dao = new dao();

        try {
            Connection conn = db.getConn();
            booklist = dao.getBook(conn, newbook);
            
            // text code
            // System.out.println(booklist.get(1).getBookname());
            // System.out.println(booklist.get(2).getBookname());
            // System.out.println(booklist.get(3).getBookname());

            //回传booklist
            req.getSession().setAttribute("booklist", booklist);

            //text code
            // System.out.println("==servlet======");
            // System.out.println("回传的书籍名："+bookname);
            // System.out.println("newbook->name:"+newbook.getBookname());
            // System.out.println("回传booklist成功");  

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("searchbook.jsp");
    }

}
