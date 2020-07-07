package com.example.demo.java.DesignPatterns.Bridging.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: fzh
 * @Date: 2020/6/5 18:05
 * @Content:
 */
@Slf4j
public class PayFaceMode implements IPayMode {
    @Override
    public boolean security(String uId) {
        log.info("人脸识别 验证");
        return true;
    }
}
