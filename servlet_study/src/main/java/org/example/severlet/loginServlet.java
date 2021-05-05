package org.example.severlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login1")
public class loginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        //1.解析获取请求数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //响应response对象的输出流，输出数据到body
        PrintWriter pw = resp.getWriter();

        //2.拿到请求数据，根据业务来执行相关CRUD操作
        //3.返回响应的HTTP数据
        if("abc".equals(username) && "123".equals(password)){
            pw.println("<h1>欢迎你，" + username + "</h1>");
        }else {
            pw.println("<h1>用户名或密码错误</h1>");
        }

    }
}
