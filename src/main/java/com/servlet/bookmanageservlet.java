package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.book;
import com.dao.dao;
import com.sql.JDBCUtil;

public class bookmanageservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String change = req.getParameter("change");
        int changeid = (int) Integer.parseInt(change);

        JDBCUtil db = new JDBCUtil();
        book book = new book();
        dao dao = new dao();

        if (changeid == 1) {
            // 搜索框
            String booksearchtext = req.getParameter("search-text");

            List<book> booklist = new ArrayList<book>();

            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            boolean flag = pattern.matcher(booksearchtext).matches();

            if (flag) {
                if (booksearchtext != "") {
                    int bookid = (int) Integer.parseInt(booksearchtext);
                    book.setBookid(bookid);
                }
            } else {
                book.setBookname(booksearchtext);
            }

            try {
                Connection conn = db.getConn();
                booklist = dao.getBook_l(conn, book);
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getSession().setAttribute("booklist1", booklist);

            resp.sendRedirect("bookmanagepage.jsp");
        } else {
            // 修改&增加框
            String bookchange = req.getParameter("bookchange");
            int bookchangeid = (int) Integer.parseInt(bookchange);

            int bookid = 0;
            String bookname = req.getParameter("bookname");
            String bookborrow_numstr = req.getParameter("bookborrow_num");
            String bookreceive_numstr = req.getParameter("bookreceive_num");
            String bookdepotstr = req.getParameter("bookdepot");

            int borrow_num = 0;
            int receive_num = 0;

            boolean bookdepot = false;

            if (bookborrow_numstr != "") {
                borrow_num = (int) Integer.parseInt(bookborrow_numstr);
            }
            if (bookreceive_numstr != "") {
                receive_num = (int) Integer.parseInt(bookreceive_numstr);
            }
            bookdepot = ((int) Integer.parseInt(bookdepotstr) == 1) ? true : false;

            book rebook = null;

            if (bookchangeid == 1) {
                // 选项为 修改book ,需要输入bookid
                String bookidstr = req.getParameter("bookid");
                bookid = (int) Integer.parseInt(bookidstr);

                // 判断输入的bookID是否合法
                // 提取原book内容
                try {
                    Connection conn = db.getConn();
                    rebook = dao.getBook_i(conn, bookid);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // 判断输入的字符,为空/0则不覆盖book
                if (bookname != "")
                    rebook.setBookname(bookname);
                if (borrow_num != 0)
                    rebook.setBorrow_num(borrow_num);
                if (receive_num != 0)
                    rebook.setReceive_num(receive_num);
                if (bookdepot != rebook.getDepot())
                    rebook.setDepot(bookdepot);

                // 更新book数据库内容
                try {
                    Connection conn = db.getConn();
                    dao.update_book(conn, rebook);
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // TODO:回传alert提示

                resp.sendRedirect("usermanagepage.jsp");

            } else {
                // 选项为增加book，不需要输入bookid
                // bookname必填
                book newbook = new book();
                if (bookname != "") {
                    newbook.setBookname(bookname);
                    newbook.setBorrow_num(borrow_num);
                    newbook.setReceive_num(receive_num);
                    newbook.setDepot(bookdepot);
                    try {
                        Connection conn = db.getConn();
                        if (dao.insert_book(conn, newbook)) {
                            System.out.println("已添加书籍");
                        }
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("bookname为空,无法添加");
                }
                resp.sendRedirect("usermanagepage.jsp");
            }
        }

    }

}
