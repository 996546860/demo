package com.example.demo.java.DesignPatterns.Bridging;

import com.example.demo.java.DesignPatterns.Bridging.channel.WxPay;
import com.example.demo.java.DesignPatterns.Bridging.model.PayCypher;

import java.math.BigDecimal;

/**
 * @Author: fzh
 * @Date: 2020/6/5 18:27
 * @Content:
 */
public class test {


    public static void main(String[] args) {
        PayCypher payCypher = new PayCypher();
        WxPay pay = new WxPay(payCypher);
        pay.transfer("1","2", BigDecimal.ONE);
    }
}
