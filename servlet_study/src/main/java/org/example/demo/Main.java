package org.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setUsername("不加糖");
        user.setPassword("随便");
        //把对象序列化为json字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        System.out.println(json);

        String input = "{\"username\":\"加糖\",\"password\":\"随便\"}";
        //把json字符串反序列化为java对象
        User u2 = mapper.readValue(input,User.class);
        System.out.println(u2);
    }
}
