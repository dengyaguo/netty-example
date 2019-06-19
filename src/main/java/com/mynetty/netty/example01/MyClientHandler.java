package com.mynetty.netty.example01;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
@Slf4j
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        log.info(ctx.channel().remoteAddress() + "client receive message：" + msg);

        DataInputStream dataInputStream = new DataInputStream(System.in);
        byte[] bytes = new byte[1024];
        dataInputStream.read(bytes);
        ctx.writeAndFlush("from client: " +new String(bytes));
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自于客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
