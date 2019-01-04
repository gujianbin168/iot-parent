package com.chint.datapool.cloud.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import sun.misc.MessageUtils;
import com.chint.datapool.cloud.netty.server.channel.SimpleNioServerSocketChannel;
import com.chint.datapool.cloud.netty.server.channel.SimpleNioSocketChannel;
import com.chint.datapool.cloud.netty.server.handler.MessageServerHandler;
import com.chint.datapool.cloud.netty.server.handler.codec.MagicCodeDecoder;

/**
 * RelayServerManager实现类
 * 
 * @author gu.jianbin *
 */
public class RelayManagerServer {
	protected static final Logger logger = LoggerFactory.getLogger(RelayManagerServer.class);
	private int localPort = 18081;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ServerBootstrap serverBootstrap;
	private EventExecutorGroup eventExecutorGroup;
	// relayServer负载超时时间(秒)
	@Value("${load.balance.time.out}")
	private Long loadBalanceTimeOut;
	
	private MessageServerHandler relayServerHandler;
	private int threadcount = 100;

	public RelayManagerServer(MessageServerHandler relayServerHandler, int localPort, int threadcount) {
		this.localPort = localPort;
		this.relayServerHandler = relayServerHandler;
		this.threadcount = threadcount;
	}

	public void run() throws Exception {
		logger.info("RelayServerManager start & Listening on*:" + localPort + "...");
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		eventExecutorGroup = new DefaultEventExecutorGroup(threadcount);
		try {
			serverBootstrap = new ServerBootstrap();
			serverBootstrap.group(bossGroup, workerGroup)
					.channel(SimpleNioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    //可能占用堆外内存，暂时不用
                    .option(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
//                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 62*1000)
//                    .option(ChannelOption.SO_TIMEOUT, 62*1000)
					   .childHandler(new ChannelInitializer<SocketChannel>(){
												@Override
												public void initChannel(SocketChannel ch) throws Exception {
													ChannelPipeline pipeline = ch.pipeline();
													pipeline.addLast("heartbeat", new IdleStateHandler(loadBalanceTimeOut, loadBalanceTimeOut, loadBalanceTimeOut, TimeUnit.SECONDS));
													pipeline.addLast(new ChannelInboundHandlerAdapter(){
							            	@Override
					                	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)	throws Exception {
					                		if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
					                			IdleStateEvent event = (IdleStateEvent) evt;
					                			if(event.state() == IdleState.READER_IDLE){
					        						SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
					        						String channelID = channel.getId();
					        						//移除relayServer的channel和域名映射
					        						//StaticObject.relayServerChannelDomainMap.remove(channelID);
					                				ctx.close();
					                				logger.error("Read idle timeout.");
					                			}
					                		}
					                	}
													});
//													pipeline.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
													//pipeline.addLast("frameDecoder", new MagicCodeDecoder(MessageUtils.magicNumber, 1048576000, 4, 4, 0, 0));
//													pipeline.addLast("frameEncoder", new MgicCodeEncoder(4));
													pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
													pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
													pipeline.addLast(eventExecutorGroup, relayServerHandler);
												}
											});
			serverBootstrap.bind(localPort).sync().channel().closeFuture().sync();
		} catch (Exception e) {
			logger.info("ProxyRelayServer start error:" + e);
		} finally {
//			 close();
		}
	}

	public void close() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}

}
