package com.chint.datapool.cloud.netty.server.channel;

import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 因netty4移除了Channel.id()，所以需自己封装一个NioServerSocketChannel
 * User: zhang.xingyu
 * Date: 13-11-11
 * Time: 上午10:47
 */
public class SimpleNioServerSocketChannel extends NioServerSocketChannel{

    private static final Logger logger = LoggerFactory.getLogger(SimpleNioServerSocketChannel.class);

    @Override
    protected int doReadMessages(List<Object> buf) throws Exception {
        SocketChannel ch = javaChannel().accept();

        try {
            if (ch != null) {
                //自己封装了一个NioSocketChannel，增加id属性
                buf.add(new SimpleNioSocketChannel(this, ch));
                return 1;
            }
        } catch (Throwable t) {
            logger.warn("Failed to create a new channel from an accepted socket.", t);

            try {
                ch.close();
            } catch (Throwable t2) {
                logger.warn("Failed to close a socket.", t2);
            }
        }

        return 0;
    }
}
