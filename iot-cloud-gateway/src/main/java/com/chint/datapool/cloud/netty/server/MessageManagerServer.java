package com.chint.datapool.cloud.netty.server;

import io.netty.bootstrap.ServerBootstrap;
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
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.chint.datapool.cloud.netty.server.channel.SimpleNioServerSocketChannel;
import com.chint.datapool.cloud.netty.server.channel.SimpleNioSocketChannel;
import com.chint.datapool.cloud.netty.server.handler.MessageServerHandler;

@Component
public class MessageManagerServer {

    
    protected static final Logger logger = LoggerFactory.getLogger(RelayManagerServer.class);
    private EventExecutorGroup eventExecutorGroup;
    @Resource
    private ChildChannelHandler childChannelHandler;
    private MessageServerHandler messageServerHandler;

    public void run(int port) throws Exception {
//    	new MqttDecoder();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口：" + port);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(SimpleNioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    //可能占用堆外内存，暂时不用
//                    .option(ChannelOption.ALLOCATOR, UnpooledByteBufAllocator.DEFAULT)
//                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .option(ChannelOption.TCP_NODELAY, true)
//                    .option(ChannelOption.SO_REUSEADDR, true)                    
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //.childHandler(childChannelHandler);
                    .childHandler(new ChannelInitializer<SocketChannel>(){
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline pipeline = ch.pipeline();
//							pipeline.addLast("heartbeat", new IdleStateHandler(loadBalanceTimeOut, loadBalanceTimeOut, loadBalanceTimeOut, TimeUnit.SECONDS));
							pipeline.addLast(new ChannelInboundHandlerAdapter(){
							            @Override
					                	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)	throws Exception {
					                		if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
					                			IdleStateEvent event = (IdleStateEvent) evt;
					                			if(event.state() == IdleState.READER_IDLE){
					        						SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
					        						String channelID = channel.getId();
					        						//移除channel和域名映射
					        						//StaticObject.relayServerChannelDomainMap.remove(channelID);
					                				ctx.close();
					                				logger.error("Read idle timeout.");
					                			}
					                		}
					                	}
									});
							        //pipeline.addLast("logger", new LoggingHandler(LogLevel.DEBUG));
									//pipeline.addLast("frameDecoder", new MagicCodeDecoder(MessageUtils.magicNumber, 1048576000, 4, 4, 0, 0));
							        //pipeline.addLast("frameEncoder", new MgicCodeEncoder(4));
									pipeline.addLast("bytesDecoder", new ByteArrayDecoder());
									pipeline.addLast("bytesEncoder", new ByteArrayEncoder());
									pipeline.addLast(eventExecutorGroup, messageServerHandler);
								}
							});
            //绑定端口，同步等待成功
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } finally {
            //退出，释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}