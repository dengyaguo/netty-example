package com.mynetty.netty.example01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName:      NettyServer
 * @Description:    服务端
 * @Author:         buyi
 * @Version:        1.0
*/
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //boss线程
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //工作线程
        NioEventLoopGroup  workerGroup= new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new MyServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(9999).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
