package com.mashibing.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;

/**
 * @Description TankMsgDecoder
 * @Author Radish
 * @Date 2020-09-02 11:09
 */
public class MsgDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if(in.readableBytes() < 8) return;
		
		in.markReaderIndex();
		
		MsgType msgType = MsgType.values()[in.readInt()];
		int length = in.readInt();
		
		if(in.readableBytes()< length) {
			in.resetReaderIndex();
			return;
		} 
		
		byte[] bytes = new byte[length];
		in.readBytes(bytes);
		
		Msg msg = null;
		
		switch(msgType) {
		case TankJoin:
			msg = new TankJoinMsg();
			break;
		case TankStartMoving:
			msg = new TankStartMovingMsg();
		default:
			break;
		}
		
		msg.parse(bytes);
		out.add(msg);
		
	}
}
