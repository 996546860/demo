package com.example.demo.java.Bridging.channel;

import com.example.demo.java.Bridging.model.IPayMode;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author: fzh
 * @Date: 2020/6/5 18:03
 * @Content:
 */
@Slf4j
public abstract class Pay {


    protected IPayMode iPayMode;

    public Pay(IPayMode iPayMode) {
        this.iPayMode = iPayMode;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);

}
