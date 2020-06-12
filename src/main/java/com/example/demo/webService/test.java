package com.example.demo.webService;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class test {


    public static void main(String[] args) {
        String address = "http://127.0.0.1:8989/WS_Server/Webservice";
        Endpoint.publish(address, new WebServiceImpl());
        System.out.println("Publish Success~");


    }
}
