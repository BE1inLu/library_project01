package com.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.dao;
import com.sql.JDBCUtil;
import com.user.user;

public class usermanageservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String change = req.getParameter("change");
        int changeid = (int) Integer.parseInt(change);

        // textcode
        // System.out.println("change:" + change);

        JDBCUtil db = new JDBCUtil();
        user user = new user();
        dao dao = new dao();

        if (changeid == 1) {
            // 判断为搜索框
            String usertext = req.getParameter("search-text");

            // text code
            System.out.println("进入search");
            System.out.println("usertext:" + usertext);

            List<user> userlist = new ArrayList<user>();

            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            boolean flag = pattern.matcher(usertext).matches();

            // textcode
            // System.out.println("flag:" + flag);

            if (flag) {
                // 判断是否不为空
                if (usertext != "") {
                    int userid = (int) Integer.parseInt(usertext);
                    // textcode
                    // System.out.println("userid:" + userid);

                    user.setUserid(userid);
                }
            } else {
                user.setUsername(usertext);
            }

            // textcode
            // System.out.println("user.userid:" + user.getUserid());
            // System.out.println("user.username:" + user.getUsername());

            try {
                Connection conn = db.getConn();
                userlist = dao.getUser_l(conn, user);
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            req.getSession().setAttribute("userlist", userlist);
            resp.sendRedirect("usermanagepage.jsp");

        } else {
            // 判断为修改框
            // id默认必须输入
            String useridstr = req.getParameter("userid");
            int userid = (int) Integer.parseInt(useridstr);

            String username = req.getParameter("username");
            String userpassword = req.getParameter("userpassword");
            String usermail = req.getParameter("useremail");
            String usertel = req.getParameter("usertel");
            String usersex = req.getParameter("sex");

            String superuserstr = req.getParameter("supreuser");
            boolean superuser = false;
            if (superuserstr == "yes")
                superuser = true;

            // textcode
            // // 检测有无获取值
            // System.out.println("进入修改框");
            // System.out.println("userid:" + userid);
            // System.out.println("userpassword:" + userpassword);
            // System.out.println("useremail:" + usermail);
            // System.out.println("usertel:" + usertel);
            // System.out.println("sex:" + usersex);
            // System.out.println("superuser:" + superuser);

            user newuser = new user();

            // 先从该用户id提取出原内容
            try {
                Connection conn = db.getConn();

                newuser.setUserid(userid);
                newuser = dao.getUser_i(conn, newuser);
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 判断输入的字符为空则不覆盖newbook
            // System.out.println("查询与覆盖=====");

            if(username!="") newuser.setUsername(username);
            if(userpassword!="") newuser.setPassword(userpassword);
            if(usermail!="") newuser.setEmail(usermail);
            if(usertel!="") newuser.setTel(usertel);

            if(usersex!=null) newuser.setSex(usersex);
            if(superuser==false&&superuser==true) newuser.setSuperuser(superuser);

            // textcode
            // System.out.println("==============");
            // System.out.println("newuserlog");
            // System.out.println("userid:" + newuser.getUserid());
            // System.out.println("username:"+newuser.getUsername());
            // System.out.println("userpassword:" + newuser.getPassword());
            // System.out.println("useremail:" + newuser.getEmail());
            // System.out.println("usertel:" + newuser.getTel());
            // System.out.println("sex:" + newuser.getSex());
            // System.out.println("superuser:" + newuser.getSuperuser());

            // 更新user数据库内容
            try {
                Connection conn = db.getConn();
                boolean bool1 = dao.update_user(conn, newuser);
                System.out.println("执行判断："+bool1);
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resp.sendRedirect("usermanagepage.jsp");

            // text code
            // System.out.println("servlet结束=========");
        }
    }

}
