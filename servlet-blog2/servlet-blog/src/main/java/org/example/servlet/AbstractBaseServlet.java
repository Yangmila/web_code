package org.example.servlet;

import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractBaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        JSONResponse json = new JSONResponse();
        try{

            Object data = process(req);
            json.setSuccess(true);
            json.setData(data);
        }catch (Exception e){
            e.printStackTrace();
            if(e instanceof AppException){
                AppException ae = (AppException) e;
                json.setCode(ae.getCode());
                json.setMessage(ae.getMessage());
            }else {
                json.setCode("ERROR");
                json.setMessage("系统出错，请联系管理员");
            }
        }

        //返回响应（设置json字符串到body）
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }

    public abstract Object process(HttpServletRequest req) throws Exception;
}
