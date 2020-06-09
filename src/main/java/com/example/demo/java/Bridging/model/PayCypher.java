package com.example.demo.java.Bridging.model;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: fzh
 * @Date: 2020/6/5 18:04
 * @Content:
 */
@Slf4j
public class PayCypher implements IPayMode {
    @Override
    public boolean security(String uId) {
        log.info("密码验证 .");
        return true;
    }
}
