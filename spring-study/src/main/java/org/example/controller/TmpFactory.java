package org.example.controller;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class TmpFactory implements FactoryBean<LoginController.Tmp> {
    @Override
    public LoginController.Tmp getObject() throws Exception {
        return new LoginController.Tmp();
    }

    @Override
    public Class<?> getObjectType() {
        return LoginController.Tmp.class;
    }
}
