package com.dao;

// import java.security.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.user.*;
import com.book.*;
import com.booklog.booklog;

public class dao {

    // user数据库操作--登录模块
    public user login(Connection conn, user user) throws Exception {

        user resultUser = null;

        // 数据库指令
        String sql = "select * from user where username=? and password=?";

        // 获得对象
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());

        // 获得结果
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            resultUser = new user();
            resultUser.setUserid(rs.getInt("userid"));
            resultUser.setSuperuer(rs.getBoolean("superuser"));
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
            resultUser.setEmail(rs.getString("mail"));
            resultUser.setSex(rs.getString("sex"));
        }

        return resultUser;
    }

    // user数据库操作--注册模块
    public boolean regist(Connection conn, user user) throws Exception {

        // 定义一个布尔类，用于返回操作结果
        boolean flag = false;

        // sql指令操作
        String sql = "insert into user(superuser,username,password,mail,tel,sex) VALUES(0,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);

        // 接收前台回传的数据，匹配指令val
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getTel());
        pst.setString(5, user.getSex());

        // 执行指令，返回int数值用来表示注册是否成功
        int res = pst.executeUpdate();
        if (res > 0)
            flag = true;
        return flag;
    }

    // TODO：user数据库操作，回传user数据库信息

    // book数据库操作--回传全部书籍列表
    public List<book> see_allbook(Connection conn) throws Exception {
        List<book> booklist = new ArrayList<book>();
        String sql = "select * from book";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            book newbook = new book();
            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setDepot(rs.getBoolean("depot"));
            booklist.add(newbook);
        }
        return booklist;
    }

    // book数据库操作--接收书籍名，回传书籍借阅情况，没有就回传全部在库数目
    public List<book> getBook(Connection conn, String bookname) throws Exception {

        List<book> listbook = new ArrayList<book>();
        String sql = "select * from book where bookname like ? ";
        String sql1 = "select * from book where depot=01";
        PreparedStatement pst = null;

        if (bookname == "") {
            pst = conn.prepareStatement(sql1);
        } else {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + bookname + "%");
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {

            book newbook = new book();

            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setDepot(rs.getBoolean("depot"));
            listbook.add(newbook);

        }

        return listbook;
    }

    // book数据库操作--获取bookid,回传此book的信息，返回book类
    public book getBookmessage(Connection conn, int bookid) throws Exception {
        book newbook = new book();
        String sql = "SELECT * FROM book WHERE bookid = ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, bookid);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setDepot(rs.getBoolean("depot"));
        }
        return newbook;
    }

    // book数据库操作--判断书本id是否在库&正确，接收bookid，判断depot是否在库，回传depot判断
    public boolean booldepot(Connection conn, int bookid) throws Exception {
        boolean bool = false;
        String sql = "SELECT * FROM book WHERE bookid= ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, bookid);
        ResultSet rs = pst.executeQuery();
        if (rs.next())
            bool = rs.getBoolean("depot");
        rs.close();
        pst.close();
        return bool;
    }

    // 判断此用户id是否正确，接收userid
    public boolean booluser(Connection conn, int userid) throws Exception {
        boolean bool = false;
        String sql = "SELECT * FROM user WHERE userid= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userid);
        ResultSet rs = pst.executeQuery();
        bool = rs.next();
        rs.close();
        pst.close();
        return bool;
    }

    // booklog数据库操作--借书操作,返回布尔类
    public boolean borrow_book(Connection conn, booklog booklog) throws Exception {
        /*
         * 判断userid和bookid是否正确，以及书是否在库
         * 如何判断：执行查询userid和bookid指令，有回传则记录，没有会报错
         * 更新book表此bookid的数据
         * booklog执行语句插入
         */

        // 0,前置操作
        int bookid = booklog.getBookid();
        int userid = booklog.getUserid();
        boolean flag = false;
        boolean flag1=false;
        dao dao = new dao();

        // TODO：查询是否有重复，否就返回

        // 1,将book表里面此bookid的书信息更新
        if (dao.booldepot(conn, bookid)) {

            // 0,前置准备
            int borrow_number = 0;

            // 1,先通过bookid获取borrownum和receivenum
            String sql0 = "SELECT * FROM book WHERE bookid = ? ";
            PreparedStatement pst = conn.prepareStatement(sql0);
            pst.setInt(1, bookid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                borrow_number = rs.getInt("borrow-num");
            }
            rs.close();
            pst.close();
            
            //textcode
            // System.out.println("borrownum:"+ borrow_number);

            // 2,通过前面回传回来的值更新覆盖原来的的值
            String sql1 = "UPDATE book SET `borrow-num` = ? , depot = ? WHERE bookid= ? ";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setInt(1, borrow_number+1);
            pst1.setInt(2, 0);
            pst1.setInt(3, bookid);
            int res = pst1.executeUpdate();
            if(res!=0){
                System.out.println("更新book数据成功");
                flag1=true;//记录book数据库操作是否成功
            }
            // 3,完成更新，关闭指针
            pst1.close();

            // textcode
            // System.out.println("book操作成功");
        }

        // textcode
        // System.out.println("获取booklogdate是否正确传值");
        // System.out.println(booklog.getBorrowDate().getTime());
        // System.out.println(booklog.getReceiveDate().getTime());

        // 时间转换，util.date->sql.date
        java.sql.Date sql_borrowdate=new java.sql.Date(booklog.getBorrowDate().getTime());
        java.sql.Date sql_receivedate=new java.sql.Date(booklog.getReceiveDate().getTime());

        // textcode
        // sql_borrowdate=(java.sql.Date)booklog.getBorrowDate();
        // sql_receivedate=(java.sql.Date)booklog.getReceiveDate();
        // System.out.println(sql_borrowdate);
        // System.out.println(sql_receivedate);
        // System.out.println("book数据库操作,判断是否成功"+flag1);
        // System.out.println("booluser判断"+dao.booluser(conn, userid));
    

        // 2，在booklog里面插入语句
        if (flag1 && dao.booluser(conn, userid)) {
            String sql = "INSERT INTO booklog(bookid,userid,borrowday,receiveday,redepot,nullitem) VALUE(?,?,?,?,1,1)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, bookid);
            pst.setInt(2, userid);
            pst.setDate(3, sql_borrowdate);
            pst.setDate(4, sql_receivedate);

            int res = pst.executeUpdate();
            if (res > 0) 
                flag = true;
            pst.close();

            // textcode
            // System.out.println("插入语句成功");

        }
        return flag;
    }

    // TODO:booklog数据库操作

}
