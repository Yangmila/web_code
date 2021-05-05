package org.example.filter;

import org.example.model.JSONResponse;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//用于用户会话的统一管理
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //获取请求的服务路径
        String servletPath = req.getServletPath();
        //前端敏感资源
        if(servletPath.startsWith("/jsp/")){
            if(!isLogin(req)){//未登陆，跳转到登录页面
                String scheme = req.getScheme();//http
                String host = req.getServerName();//ip或域名
                int port = req.getServerPort();//port
                String contextPath = req.getContextPath();//应用上下文路径
                String basePath = scheme+"://"+host+":"+port+contextPath;
                resp.sendRedirect(basePath+"/login.html");
                return;
            }
        //else if已排除前端敏感资源，条件逻辑排除前端/后端公开资源
        }else if(!servletPath.startsWith("/static/")
            && !servletPath.equals("/login.html")
            && !servletPath.equals("/login2.html")
            && !servletPath.equals("/login")
            && !servletPath.equals("/login2")){//后端敏感资源：
            if(!isLogin(req)){//未登陆，返回401状态码，响应体是json格式
                resp.setStatus(401);
                JSONResponse json = new JSONResponse();
                json.setCode("LOG000");
                json.setMessage("用户未登陆，不允许访问");
                resp.getWriter().println(JSONUtil.serialize(json));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public static boolean isLogin(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user != null){
                return true;//有session并且有保存的用户信息，是登录
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
