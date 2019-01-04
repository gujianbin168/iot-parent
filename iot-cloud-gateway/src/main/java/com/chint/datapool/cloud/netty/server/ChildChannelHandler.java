package com.chint.datapool.cloud.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Resource
    private DiscardServerHandler discardServerHandler;

    public void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(discardServerHandler);
        ChannelPipeline pipeline = socketChannel.pipeline();
    }
}
