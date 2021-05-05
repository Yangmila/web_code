package org.example.severlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login2")
public class login2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //ajax请求响应，比较固定的代码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        //1解析请求数据
        //请求体数据都可以通过输入流获取，只是表单格式使用get
        InputStream is = req.getInputStream();

        //使用jackson框架，把json字符串反序列化为java对象
        ObjectMapper mapper = new ObjectMapper();
        User input = mapper.readValue(is,User.class);

        PrintWriter pw = resp.getWriter();
        //提供一个对象，徐丽华为json字符串作为响应体的内容
        Map<String,Object> map = new HashMap<>();

        //业务处理
        if("abc".equals(input.getUsername()) && "123".equals(input.getPassword())){
            //业务操作是否成功
            map.put("ok",true);
            //业务操作成功时，可能需要业务数据，前端会根据响应的业务数据字段，来渲染网页内容
        }else {
            map.put("ok",false);
            //业务失败，一般提供错误码（开发人员用）和错误消息（用户用）
            map.put("code","LOG001");
            map.put("msg","用户名或密码错误");
        }

        //3 返回响应数据（json字符串）
        pw.println(mapper.writeValueAsString(map));
    }
}
