package com.chint.datapool.cloud.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.MessageUtils;
import com.chint.datapool.cloud.netty.server.channel.SimpleNioSocketChannel;

@ChannelHandler.Sharable
@Component("relayServerHandler")
public class MessageServerHandler extends ChannelInboundHandlerAdapter {
//	@Autowired
//	private RelayReportService relayReportService;

	private static final Logger logger = LoggerFactory.getLogger(MessageServerHandler.class);
	private final Map<String, SimpleNioSocketChannel> channels = new HashMap<String, SimpleNioSocketChannel>();
//	@Autowired
//	private RelayServerService relayServerService;
//	@Autowired
//	private AccountManagerWebService accountManagerService;
//	@Autowired
//	private	RelayServerTokenWebService relayServerTokenWebService;
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
		channels.put(channel.getId(), channel);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			if (msg != null) {
				byte[] msgTmp = (byte[]) msg;
				if (msgTmp.length > 0) {
					ByteBuf buf = Unpooled.copiedBuffer(msgTmp);
					short receiveMagicCode = (short) buf.readUnsignedShort();// 读取固定值得Magic并验证，2个字节长度
//					logger.info("Receive magic code:" + receiveMagicCode);
					if (receiveMagicCode == 1){//MessageUtils.MAGIC_NUMBER) {
						short type = (short) buf.readUnsignedShort();// 读取消息类型，2个字节长度
						buf.resetReaderIndex();
						SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
						String channelID = channel.getId();
						String str = null;
						SimpleNioSocketChannel c = (SimpleNioSocketChannel) ctx.channel();
						String channelIp = c.remoteAddress().getAddress().getHostAddress();//获取客户端ip
						if (type == 1){//MessageUtils.MESSAGE_TYPE_CONNECT) {// 0x0001 建立连接消息
							buf = buf.copy(8, buf.capacity() - 8);
							str = new String(buf.array());
							Object requestBean = null;//JsonUtils.fromJSON(str, RelayServerRequest.class);
							return;
						} else if (type == 1){//MessageUtils.MESSAGE_TYPE_DISCONNECT) {// 0x0005 断开连接
							logger.info("Receive client disconnect message success,IP:" + channelIp);
							//StaticObject.relayServerChannelDomainMap.remove(channelID);
							ctx.close();// 关闭连接
							return;
						} else if (type == 1){//MessageUtils.MESSAGE_TYPE_HEART_BEAT) {// 0x0007 Heart Beat
							try {
								//relayReportService.receiveHeartBeatMsg(channelID, channelIp, ctx);
							} catch (Exception e) {
								logger.error("Receive heart beat message failed,ip" + channelIp);
							}
							return;
						} else if (type == 1){//MessageUtils.MESSAGE_TYPE_STATUS) {// 0x0009 负载信息
							buf = buf.copy(8, buf.capacity() - 8);
							str = new String(buf.array());
							//RelayServerRequest requestBean = JsonUtils.fromJSON(str, RelayServerRequest.class);
							try {
								//relayReportService.receiveLoadBalanceMsg(requestBean, channelID, channelIp, ctx);
							} catch (Exception e) {
								logger.error("Receive load balance message failed,ip" + channelIp);
							}
							return;
						} else if (type == 1){//MessageUtils.MESSAGE_TYPE_GET_TOKEN) {// 0x0021 Get Token
							buf = buf.copy(8, buf.capacity() - 8);
							str = new String(buf.array());
							// 收到RelayServer发送的获取 Token 请求对象
//							RelayAuthRequest requestBean = JsonUtils.fromJSON(str, RelayAuthRequest.class);
//							if(requestBean == null){
//								logger.error("Receive relay get token error,RelayAuthRequest is null。");
//							}
							// 向Hub发送验证请求对象
//							GetTokenRequest getTokenRequest = new GetTokenRequest();
//							getTokenRequest.setSerialNo(requestBean.getSerialNo().trim());
//							getTokenRequest.setServiceName(requestBean.getService().trim());
//							GetTokenResponse getTokenResponse = relayServerTokenWebService.getToken(getTokenRequest);
//							// 收到Hub返回的应答对象
//							GetTokenACKResponse responseBean = new GetTokenACKResponse();
//							responseBean.setSerialNo(requestBean.getSerialNo());
//							responseBean.setService(requestBean.getService());
//							responseBean.setToken(getTokenResponse.getToken());
//							responseBean.setMsgType(MessageUtils.MESSAGE_TYPE_GET_TOKEN_ACK);
//							responseBean.setMsgId(requestBean.getMsgId());
							// 向RelayServer发送应答消息Get Token Ack
//							sendGetTokenACK(responseBean, channel);
						}
					}
				}
			}
	}

	// 发送拒绝报文
//	private void sendRefuse(int errorCode, short feedBackType, SimpleNioSocketChannel channel,final ChannelHandlerContext ctx) {
//		RefuseResponse bean = new RefuseResponse();
//		bean.setReason(errorCode + "");
//		bean.setMessage("");
//		String jasonStr = null;//JsonUtils.toJSON(bean);
//		byte[] information = jasonStr.getBytes(Charset.forName("UTF-8"));
//		ByteBuf result = Unpooled.buffer(information.length + 8);
//		result.writeShort(MessageUtils.MAGIC_NUMBER);
//		result.writeShort(feedBackType);
//		result.writeInt(information.length);
//		result.writeInt(errorCode);
//		StaticObject.relayServerChannelDomainMap.remove(channel.getId());
//	    channel.writeAndFlush(result.array()).addListener(new ChannelFutureListener() {
//	      @Override
//	      public void operationComplete(ChannelFuture f) throws Exception {
//	          if (f.isSuccess()) {
//	              ctx.close();
//	          }
//	      }
//	    });
//	}

	// 发送应答报文
	private void sendAccept(short feedBackType, SimpleNioSocketChannel channel) {
		ByteBuf result = Unpooled.buffer(8);
//		result.writeShort(MessageUtils.MAGIC_NUMBER);
		result.writeShort(feedBackType);
		result.writeInt(0);
		ByteBuf buf = Unpooled.buffer(result.array().length);
		buf.writeBytes(result.array());
		channel.writeAndFlush(buf.array());
	}

	// 向RelayClient发送GetTokenACK应答报文
//	private void sendGetTokenACK(GetTokenACKResponse responseBean, SimpleNioSocketChannel channel) {
//		String jasonStr = JsonUtils.toJSON(responseBean);
//		byte[] information = jasonStr.getBytes(Charset.forName("UTF-8"));
//		ByteBuf result = Unpooled.buffer(information.length + 8);
//		result.writeShort(MessageUtils.MAGIC_NUMBER);
//		result.writeShort(responseBean.getMsgType());
//		result.writeInt(information.length);
//		result.writeBytes(information);
//		channel.writeAndFlush(result.array());
//	}


//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		ctx.flush();
//	}

//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		logger.error("connection exception.", cause);
//		SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
//		String channelID = channel.getId();
//		StaticObject.relayServerChannelDomainMap.remove(channelID);
//		ctx.close();
//	}

//	@Override
//	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
//		logger.error("channelUnregistered");
//		SimpleNioSocketChannel channel = (SimpleNioSocketChannel) ctx.channel();
//		String channelID = channel.getId();
//		StaticObject.relayServerChannelDomainMap.remove(channelID);
//		ctx.close();
//	}

	/**
	 * 用户事件
	 */
//	@Override
//	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//		logger.error("userEventTriggered: {}", evt.getClass().getName());
//		super.userEventTriggered(ctx, evt);
//	}
}
