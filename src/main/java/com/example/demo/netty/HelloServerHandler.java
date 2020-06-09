package com.example.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 输出消息
 */

@Component

public class HelloServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {

            ByteBuf in = (ByteBuf) msg;
            System.out.println("初始化加载netty");
            System.out.println(CharsetUtil.UTF_8);

        } finally {

            ReferenceCountUtil.release(msg);

        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
