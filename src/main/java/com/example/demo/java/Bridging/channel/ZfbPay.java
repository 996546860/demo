package com.example.demo.java.Bridging.channel;

import com.example.demo.java.Bridging.model.IPayMode;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @Author: fzh
 * @Date: 2020/6/5 18:04
 * @Content:
 */
@Slf4j
public class ZfbPay extends Pay {

    private ZfbPay(IPayMode iPayMode){
        super(iPayMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        log.info("模拟支付宝渠道支付划账开始。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        boolean security = iPayMode.security(uId);
        log.info("模拟支付宝渠道支付风控校验。uId：{} tradeId：{} security：{}", uId, tradeId, security);
        if (!security) {
            log.info("模拟支付宝渠道支付划账拦截。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
            return "0001";
        }
        log.info("模拟支付宝渠道支付划账成功。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        return "0000";    }
}
