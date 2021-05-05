package org.example.servlet;

import org.example.dao.UserDAO;
import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/login2")
public class Login2Servlet extends AbstractBaseServlet {
    @Override
    public Object process(HttpServletRequest req) throws Exception {
        //1.解析请求数据
        User input = JSONUtil.deserialize(req.getInputStream(), User.class);
        //根据账号在数据库查询用户
        User query = UserDAO.query(input.getUsername());
        if(query == null){//查不到
            throw new AppException("LOG001", "用户不存在");
        }else if(!query.getPassword().equals(input.getPassword())){
            throw new AppException("LOG002", "用户名或密码错误");
        }
        //账号密码校验通过
        HttpSession session = req.getSession();
        session.setAttribute("user", query);
        //2.业务处理
        return null;
    }
}
