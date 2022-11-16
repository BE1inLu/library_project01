package com.dao;

import java.sql.*;
import com.user.*;
// import com.sql.JDBCUtil;

public class dao {

    // 数据库操作--登录模块
    public user login(Connection conn,user user) throws Exception{

        user resultUser=null;

        //数据库指令
        String sql="select * from text01 where username=? and password=?";

        //获得对象
        PreparedStatement pst=conn.prepareStatement(sql);
        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());

        //获得结果
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            resultUser =new user();
            resultUser.setUsername(rs.getString("username"));
            resultUser.setPassword(rs.getString("password"));
        }

        return resultUser;
    }

    //数据库操作--注册模块
    public boolean regist(Connection conn,user user)throws Exception{
        
        boolean flag=false;

        String sql="insert into text01(username,password,mail,tel,sex) VALUES(?,?,?,?,?)";
        PreparedStatement pst=conn.prepareStatement(sql);

        pst.setString(1, user.getUsername());
        pst.setString(2, user.getPassword());
        pst.setString(3, user.getEmail());
        pst.setString(4, user.getTel());
        pst.setString(5, user.getSex());

        int res=pst.executeUpdate();
        if(res>0) flag=true;
        return flag;
    }
}
