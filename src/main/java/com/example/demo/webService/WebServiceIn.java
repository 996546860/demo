package com.example.demo.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceIn {

    @WebMethod
    String sayHello(String name);

    @WebMethod
    String save(String name,String pwd);
}
