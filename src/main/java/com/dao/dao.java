package com.dao;

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
            resultUser.setSuperuser(rs.getBoolean("superuser"));
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

    // user数据库操作--更新user数据库内容
    public boolean update_user(Connection conn, user user) throws Exception {

        // 前置
        boolean flag = false;
        dao dao = new dao();

        // 1，检索userid是否存在
        if (!dao.bool_user(conn, user.getUserid())) {
            System.out.println("检测userid不存在");
            return flag;
        }

        // 2，update user
        String sql = "UPDATE `user` SET superuser= ? , username= ? ,password= ? ,mail= ? ,tel= ? ,sex= ? WHERE userid= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setBoolean(1, user.getSuperuser());
        pst.setString(2, user.getUsername());
        pst.setString(3, user.getPassword());
        pst.setString(4, user.getEmail());
        pst.setString(5, user.getTel());
        pst.setString(6, user.getSex());
        pst.setInt(7, user.getUserid());

        int res = pst.executeUpdate();
        if (res != 0) {
            System.out.println("修改user数据成功,该userid为:" + user.getUserid());
            flag = true;
        }
        return flag;
    }

    // user数据库操作，回传user数据库列表信息
    public List<user> getUser_l(Connection conn, user user) throws Exception {
        boolean flag = false;
        boolean flag1 = false;
        user newuser = null;
        String sqlstr = "";
        List<user> userlist = new ArrayList<user>();
        int userid = user.getUserid();
        String username = user.getUsername();

        // textcode
        // System.out.println("dao======");
        // System.out.println("userid:" + userid);
        // System.out.println("username:" + username);
        // System.out.println("bool" + (user.getUsername() != null));

        // 接收判断，userid 还是 username,如果都为空则返回列表全部
        if (user.getUserid() != 0) {
            sqlstr = "select * from user where userid=?";
            flag = true;
        } else if (user.getUsername() != null) {
            sqlstr = "select * from user where username LIKE ? ";
            flag = false;
        } else {
            sqlstr = "select * from user";
            flag1 = true;
        }

        PreparedStatement pst = conn.prepareStatement(sqlstr);
        if (flag && flag1 == false) {
            pst.setInt(1, userid);
        } else if (flag == false && flag1 == false) {
            pst.setString(1, "%" + username + "%");
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newuser = new user();
            newuser.setUserid(rs.getInt("userid"));
            newuser.setUsername(rs.getString("username"));
            newuser.setPassword(rs.getString("password"));
            newuser.setEmail(rs.getString("mail"));
            newuser.setTel(rs.getString("tel"));
            newuser.setSex(rs.getString("sex"));
            newuser.setSuperuser(rs.getBoolean("superuser"));
            userlist.add(newuser);
        }

        // textcode
        // System.out.println("userlist.size:" + userlist.size());

        return userlist;

    }

    // user数据库操作，回传user数据库信息
    public user getUser_i(Connection conn, user user) throws Exception {
        boolean flag = false;
        user newuser = null;
        String sqlstr = "";
        int userid = user.getUserid();
        String username = user.getUsername();

        // 接收判断，userid 还是 username
        if (user.getUserid() != 0) {
            sqlstr = "select * from user where userid=?";
            flag = true;

        } else if (user.getUsername() != null) {
            sqlstr = "select * from user where username=?";
            flag = false;
        }

        PreparedStatement pst = conn.prepareStatement(sqlstr);
        if (flag) {
            pst.setInt(1, userid);
        } else {
            pst.setString(1, username);
        }

        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            newuser = new user();
            newuser.setUserid(rs.getInt("userid"));
            newuser.setUsername(rs.getString("username"));
            newuser.setPassword(rs.getString("password"));
            newuser.setEmail(rs.getString("mail"));
            newuser.setTel(rs.getString("tel"));
            newuser.setSex(rs.getString("sex"));
        }
        return newuser;
    }

    // book数据库操作--接收book，回传book数据库信息
    public List<book> getBook_l(Connection conn, book book) throws Exception {
        boolean flag = false;
        boolean flag1 = false;
        book newbook = null;
        String sqlstr = "";
        List<book> booklist = new ArrayList<book>();
        int bookid = book.getBookid();
        String bookname = book.getBookname();

        if (book.getBookid() != 0) {
            sqlstr = "select * from book where bookid=?";
            flag = true;
        } else if (book.getBookname() != null) {
            sqlstr = "select * from book where bookname LIKE ? ";
            flag = false;
        } else {
            sqlstr = "select * from book";
            flag1 = true;
        }

        PreparedStatement pst = conn.prepareStatement(sqlstr);
        if (flag && flag1 == false) {
            pst.setInt(1, bookid);
        } else if (flag == false && flag1 == false) {
            pst.setString(1, "%" + bookname + "%");
        }

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            newbook = new book();
            newbook.setBookid(rs.getInt("bookid"));
            newbook.setBookname(rs.getString("bookname"));
            newbook.setReceive_num(rs.getInt("receive-num"));
            newbook.setBorrow_num(rs.getInt("borrow-num"));
            newbook.setDepot(rs.getBoolean("depot"));
            booklist.add(newbook);
        }

        return booklist;

    }

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

    // book数据库操作--接收书籍名，回传书籍借阅情况，没有就回传全部书籍
    public List<book> getBook(Connection conn, String bookname) throws Exception {

        List<book> listbook = new ArrayList<book>();
        String sql = "select * from book where bookname like ? ";
        String sql1 = "select * from book";
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
    public book getBook_i(Connection conn, int bookid) throws Exception {
        book newbook = new book();

        // System.out.println("getbookmessage bookid:"+bookid);

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

    public book getBookmessage(Connection conn,int bookid) throws Exception{
        return getBook_i(conn, bookid);
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

    // book数据库操作--增加book
    public boolean insert_book(Connection conn,book book)throws Exception{
        boolean flag=false;
        String sql="INSERT INTO book(bookname,`borrow-num`,`receive-num`,depot) VALUE(?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, book.getBookname());
        pst.setInt(2, book.getBorrow_num());
        pst.setInt(3, book.getReceive_num());
        pst.setBoolean(4, book.getDepot());
        int res = pst.executeUpdate();
        flag=(res>0)?true:false;
        pst.close();

        return flag;
    }
    
    // book数据库操作--更新book
    public boolean update_book(Connection conn,book book) throws Exception{
        boolean flag=false;
        String sql="UPDATE book SET bookname= ? , `borrow-num` = ? ,`receive-num`= ? , depot = ? WHERE bookid= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, '\"'+book.getBookname()+'\"');
        pst.setInt(2, book.getBorrow_num());
        pst.setInt(3, book.getReceive_num());
        pst.setBoolean(4, book.getDepot());
        pst.setInt(5, book.getBookid());

        int res = pst.executeUpdate();
        if (res != 0) {
            System.out.println("更新book数据成功");
            flag = true;// 记录book数据库操作是否成功
        }
        // 3,完成更新，关闭指针
        pst.close();

        return flag;
    }



    // user数据库操作--判断此用户id是否正确，接收userid
    public boolean bool_user(Connection conn, int userid) throws Exception {

        // textcode
        System.out.println("bool_user.userid:" + userid);

        boolean flag = false;
        String sql = "SELECT * FROM user WHERE userid= ? ";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, userid);
        ResultSet rs = pst.executeQuery();
        flag = (rs.next()) ? true : false;

        // textcode
        System.out.println("bool_user.bool:" + flag);

        rs.close();
        pst.close();
        return flag;
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
        ResultSet rs = null;

        // text code
        // System.out.println(booklog.getBookid());
        // System.out.println(booklog.getUserid());

        // 1.获取回传的数据是bookID还是userID,如果只有bookid，那就只有1本书，如果是userID，那就有可能不止1本
        if (booklog.getBookid() != 0 && booklog.getUserid() == 0) {
            // 通过bookid获取书籍,返回此bookid的booklog
            sqlstr = "select * from booklog where bookid= ? ";
            var1 = booklog.getBookid();
        } else if (booklog.getBookid() == 0 && booklog.getUserid() != 0) {
            // 通过userid获取书籍，返回此userid的booklog
            sqlstr = "select * from booklog where userid= ? ";
            var1 = booklog.getUserid();
        } else if (booklog.getBookid() != 0 && booklog.getUserid() != 0) {
            // 两个值都能获取，就返回对应具体的booklog行
            sqlstr = "select * from booklog where bookid = ? and userid = ?";
            var1 = booklog.getBookid();
            var2 = booklog.getUserid();
            flag = false;
        } else {
            return bookloglist;
        }

        // text code
        // System.out.println("sqlstr:"+sqlstr);
        // System.out.println("flag:"+flag);
        // System.out.println("var1:"+var1);

        // 2,执行数据库操作
        PreparedStatement pst = conn.prepareStatement(sqlstr);
        if (flag == true) {
            pst.setInt(1, var1);
        } else {
            pst.setInt(1, var1);
            pst.setInt(2, var2);
        }
        rs = pst.executeQuery();
        while (rs.next()) {
            booklog newbooklog = new booklog();
            newbooklog.setLogid(rs.getInt("logid"));
            newbooklog.setBookid(rs.getInt("bookid"));
            newbooklog.setUserid(rs.getInt("userid"));
            newbooklog.setBorrowDate(new java.util.Date(rs.getDate("borrowday").getTime()));
            newbooklog.setReceiveDate(new java.util.Date(rs.getDate("receiveday").getTime()));
            newbooklog.setRedepot(rs.getBoolean("redepot"));
            newbooklog.setNullitem(rs.getBoolean("nullitem"));
            bookloglist.add(newbooklog);
        }
        return bookloglist;
    }

    // booklog数据库操作--查询操作，返回为单个booklog条目
    public booklog check_booklog_i(Connection conn, booklog booklog) throws Exception {
        // 在booklog里面检索userid的这个bookid

        booklog newbooklog = new booklog();
        // int bookid=booklog.getBookid();
        // int userid=booklog.getUserid();

        String sqlstr = "select * from booklog where bookid = ? and userid = ?";
        PreparedStatement pst = conn.prepareStatement(sqlstr);
        pst.setInt(1, booklog.getBookid());
        pst.setInt(2, booklog.getUserid());
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
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
        // if(bookloglist!=null) textbooklog1 = bookloglist.get(i);
        // }

        int booklogid = textbooklog1.getLogid();
        if (dao.bool_user(conn, userid) != true && textbooklog1.getRedepot() != true
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
            pst.setBoolean(2, true);
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
                flag = true;
            }
        }

        return flag;
    }
}
