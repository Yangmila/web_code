package org.example.severlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goto")
public class GoToServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //请求时发送请求数据type=1为转发，2为重定向
        String type = req.getParameter("type");
        if("1".equals(type)){
            //转发
            req.getRequestDispatcher("home.html").forward(req,resp);
        }else {
            //转发
            resp.sendRedirect("home.html");
        }
    }
}
