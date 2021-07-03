package org.example;

import org.example.config.AppConfig;
import org.example.controller.LoginController;
import org.example.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        //根据Spring配置文件路径创建容器：应用上下文对象
        //启动容器：加载xml配置文件，扫描org.example包
        //该包及下边子包中的带Spring注解的类都会注册到容器中，称为Bean对象
        //容器可以简单理解为一个Map结构，key为bean的id或名称，value为bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //1.获取Bean对象的方式一：类型获取
        LoginController loginController = context.getBean(LoginController.class);
        System.out.println(loginController);

        //2.获取Bean对象的方式二：bean的名称或id获取
        LoginController loginController2 = (LoginController) context.getBean("loginController");
        System.out.println(loginController2);

        System.out.println(loginController == loginController2);

        //一个类型，可以注册多个bean对象到容器中
        //通过类型获取，容器只能存在该类型一个bean，否则就会报错
        //通过名称/id获取，就没有关系
//        LoginController.Tmp tmp = context.getBean(LoginController.Tmp.class);
//        System.out.println(tmp);
        LoginController.Tmp tmp = (LoginController.Tmp) context.getBean("tmp");
        System.out.println(tmp);

        LoginService loginService = context.getBean(LoginService.class);
        System.out.println(loginService.getTmp());
        System.out.println(loginService.getTmp() == tmp);

        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig);

        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}