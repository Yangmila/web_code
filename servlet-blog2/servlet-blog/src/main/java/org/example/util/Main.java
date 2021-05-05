package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.exception.AppException;
import org.example.model.JSONResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

    }

    //模拟用户登录
    public static void doPost(String username, String password) throws JsonProcessingException {

        JSONResponse json = new JSONResponse();
        //解析请求数据
        try {
            //业务处理
            jdbcMethod();
            json.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();

        }

        //返回响应
        System.out.println(JSONUtil.serialize(json));
    }

    //模拟登陆
    public static int jdbcMethod(){
        Connection c = null;
        PreparedStatement ps = null;
        try{
            //1
            c = DBUtil.getConnection();
            //2:
            String sql = "update article set title=?,content=? where id=?";
            ps = c.prepareStatement(sql);
            //3: 替换占位符+执行sql
//            ps.setString(1, a.getTitle());
//            ps.setString(2, a.getContent());
//            ps.setInt(3, a.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new AppException("LOG001", "用户登录操作失败", e);
        } finally {
//            DBUtil.close(c, ps);
        }
    }


}
