package org.example.service;

import lombok.Getter;
import org.example.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class LoginService {
    //容器启动时，会自动注入bean对象的属性
    @Autowired
    private LoginController.Tmp tmp;
}
