package com.chint.datapool.cloud.netty.server.channel;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.UUID;



/**
 * 因netty4移除了Channel.id()，所以需自己封装一个增加唯一标识的属性
 */
public class SimpleNioSocketChannel extends NioSocketChannel {

    /**
     * 该连接的唯一标识
     */

    private String id = null;// UUID.generteUUID();

    /**
     * 连接事件
     */

    private long connectTime = System.currentTimeMillis();

    /**
     * 最新活动事件
     */

    private long lastActivatetime = System.currentTimeMillis();

    public SimpleNioSocketChannel() {
    }

    public SimpleNioSocketChannel(SocketChannel socket) {
        super(socket);
    }

    public SimpleNioSocketChannel(Channel parent, SocketChannel socket) {
        super(parent, socket);
    }

		public long getLastActivatetime() {
			return lastActivatetime;
		}

		public void setLastActivatetime(long lastActivatetime) {
			this.lastActivatetime = lastActivatetime;
		}

		public String getId() {
			return id;
		}

		public long getConnectTime() {
			return connectTime;
		}
}
