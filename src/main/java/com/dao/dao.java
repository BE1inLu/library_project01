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
    public boolean bool_depot(Connection conn, int bookid) throws Exception {
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

    // user数据库操作--判断此用户id是否正确，接收userid
    public boolean bool_user(Connection conn, int userid) throws Exception {
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

    // booklog数据库操作--借书操作，在booklog插入一条新语句,返回布尔类
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
        boolean flag1 = false;
        dao dao = new dao();

        // 1,将book表里面此bookid的书信息更新
        // 这里的判断条件是这本书是否在库和是否没有被借阅
        if (dao.bool_depot(conn, bookid)) {

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

            // 2,通过前面回传回来的值更新覆盖原来的的值
            String sql1 = "UPDATE book SET `borrow-num` = ? , depot = ? WHERE bookid= ? ";
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setInt(1, borrow_number + 1);
            pst1.setInt(2, 0);
            pst1.setInt(3, bookid);
            int res = pst1.executeUpdate();
            if (res != 0) {
                System.out.println("更新book数据成功");
                flag1 = true;// 记录book数据库操作是否成功
            }
            // 3,完成更新，关闭指针
            pst1.close();

        }

        // 时间转换，util.date->sql.date
        java.sql.Date sql_borrowdate = new java.sql.Date(booklog.getBorrowDate().getTime());
        java.sql.Date sql_receivedate = new java.sql.Date(booklog.getReceiveDate().getTime());

        // 2，在booklog里面插入语句
        if (flag1 && dao.bool_user(conn, userid)) {
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

    // booklog数据库操作--查询操作
    public List<booklog> check_booklog_l(Connection conn, booklog booklog) throws Exception {

        /*
         * 会调用到此方法的操作：在booklog插入操作中判断是否有重复的booklog，用户借阅书籍记录（包括已还的书），要还书时查询书是否已经归还还是说没有归还
         * 通过book的状态判断
         * 
         * 1,接收状况
         * 1.接收bookid
         * 2.接收userid
         * 2，返回接收的参数调用的booklog
         */

        // 0.前置操作
        List<booklog> bookloglist = new ArrayList<booklog>();
        int var1 = 0;
        int var2 = 0;
        boolean flag = true;
        String sqlstr = "";

        // text code
        System.out.println(booklog.getBookid());
        System.out.println(booklog.getUserid());

        // 1.获取回传的数据是bookID还是userID,如果只有bookid，那就只有1本书，如果是userID，那就有可能不止1本
        if (booklog.getBookid() != 0 && booklog.getUserid() == 0) {
            // 通过bookid获取书籍,返回此bookid的booklog
            sqlstr = "select * into booklog where bookid= ? ";
            var1 = booklog.getBookid();
        } else if (booklog.getBookid() == 0 && booklog.getUserid() != 0) {
            // 通过userid获取书籍，返回此userid的booklog
            sqlstr = "select * into booklog where userid= ? ";
            var1 = booklog.getUserid();
        } else if (booklog.getBookid() != 0 && booklog.getUserid() != 0) {
            // 两个值都能获取，就返回对应具体的booklog行
            sqlstr = "select * into booklog where bookid = ? and userid = ?";
            var1 = booklog.getBookid();
            var2 = booklog.getUserid();
            flag = false;
        }else{
            return bookloglist;
        }

        // 2,执行数据库操作
        PreparedStatement pst = conn.prepareStatement(sqlstr);
        if (flag) {
            pst.setInt(1, var1);
        } else {
            pst.setInt(1, var1);
            pst.setInt(2, var2);
        }
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            booklog newbooklog = new booklog();
            newbooklog.setLogid(rs.getInt("logid"));
            newbooklog.setBookid(rs.getInt("bookid"));
            newbooklog.setUserid(rs.getInt("userid"));
            newbooklog.setBorrowDate(new java.util.Date(rs.getDate("borrowday").getTime()));
            newbooklog.setReceiveDate(new java.util.Date(rs.getDate("receiveday").getTime()));
            bookloglist.add(newbooklog);
        }
        return bookloglist;
    }

    // booklog数据库操作--查询操作，返回为单个booklog条目
    public booklog check_booklog_i(Connection conn, booklog booklog) throws Exception {
        //在booklog里面检索userid的这个bookid

        booklog newbooklog=new booklog();
        // int bookid=booklog.getBookid();
        // int userid=booklog.getUserid();

        String sqlstr = "select * from booklog where bookid = ? and userid = ?";
        PreparedStatement pst = conn.prepareStatement(sqlstr);
        pst.setInt(1, booklog.getBookid());
        pst.setInt(2, booklog.getUserid());
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            newbooklog.setLogid(rs.getInt("logid"));
            newbooklog.setBookid(rs.getInt("bookid"));
            newbooklog.setUserid(rs.getInt("userid"));
            newbooklog.setBorrowDate(new java.util.Date(rs.getDate("borrowday").getTime()));
            newbooklog.setReceiveDate(new java.util.Date(rs.getDate("receiveday").getTime()));
        }
        return newbooklog;
    }

    // booklog数据库操作--还书操作，返回布尔类
    public boolean Receive_book(Connection conn, booklog booklog) throws Exception {
        /*
         * 会调用此方法的操作：还书servlet会调用此方法
         * 用途：在booklog里面检索userid的这个bookid，判断是否为不在库，然后把book表里面这本书的receivenum+1和depot置true
         * 执行上述操作后再将这个booklog表的redepot，nullitem置0
         * 接收状况：
         * 接收booklog内的bookid和userid
         * 回传状况：
         * 布尔类
         * 
         */

        // 0,前置操作
        boolean flag = false;
        boolean flag1 = false;

        int bookid = booklog.getBookid();
        int userid = booklog.getUserid();
        dao dao = new dao();
        String sql;

        booklog textbooklog = new booklog();
        booklog textbooklog1 = null;
        textbooklog.setUserid(userid);
        textbooklog.setBookid(bookid);

        // 1，在booklog里面检索userid的这个bookid，判断redepot是否为true,有1个不是true则返回null
        textbooklog1 = dao.check_booklog_i(conn, textbooklog);

        // for(int i=0;i<bookloglist.size();i++){
        //     if(bookloglist!=null) textbooklog1 = bookloglist.get(i);
        // }

        int booklogid = textbooklog1.getLogid();
        if (dao.bool_user(conn, userid)!=true && textbooklog1.getRedepot() != true
                && textbooklog1.getNullitem() != true) {
            return flag;
        }

        // 2，book数据库操作，update这本书的receivenum和depot
        if (dao.bool_depot(conn, bookid) != false) {

            int receive_number = 0;
            sql = "SELECT * FROM book WHERE bookid = ? ";
            PreparedStatement pst = null;
            ResultSet rs = null;
            pst = conn.prepareStatement(sql);
            pst.setInt(1, bookid);
            rs = pst.executeQuery();
            if (rs.next()) {
                receive_number = rs.getInt("receive-num");
            }
            rs.close();
            pst.close();

            sql = "UPDATE book SET `receive-num` = ? , depot = ? WHERE bookid= ? ";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, receive_number + 1);
            pst.setInt(2, 0);
            pst.setInt(3, bookid);
            int res = pst.executeUpdate();
            if (res != 0) {
                System.out.println("更新book数据成功");
                flag1 = true;// 记录book数据库操作是否成功
            }
            pst.close();

        }

        // 3，将booklog里面这个条目的redepot和nullitem置0
        if (flag1) {
            sql = "UPDATE booklog SET redepot = ? , nullitem = ? WHERE logid= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, false);
            pst.setBoolean(2, false);
            pst.setInt(3, booklogid);
            int res = pst.executeUpdate();
            if (res != 0) {
                System.out.println("更新booklog数据成功,此booklogid为:" + booklogid);
                flag=true;
            }
        }

        return flag;
    }
}
