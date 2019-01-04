package com.chint.datapool.cloud.netty.server.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 */
public class MgicCodeEncoder extends LengthFieldPrepender {
	
	public MgicCodeEncoder(int lengthFieldLength) {
    	super(lengthFieldLength);
    }

   // @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
//        if (out == null || out.readableBytes() == 0) {
//            out.writeShort(MessageUtils.magicNumber);
//        }
//        try{
//        	short type=(short) msg.readUnsignedShort();
//        	out.writeShort(type);
//        	msg.discardReadBytes();
//        }catch (Exception e){
//        	e.printStackTrace();
//        }
 //       super.encode(ctx, msg, out);
    }
}
