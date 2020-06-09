package com.example.demo.webService;


import javax.jws.WebService;

@WebService
public class WebServiceImpl implements WebServiceIn {


    @Override
    public String sayHello(String name) {
        System.out.println("say hello word ");
        return "sayhello " + name;
    }

    @Override
    public String save(String name, String pwd) {
        System.out.println("姓名 " + name + " 密码 " + pwd);
        return "save Success";
    }
}
