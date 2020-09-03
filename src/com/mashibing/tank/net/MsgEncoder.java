package com.mashibing.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description TankMsgEncoder
 * @Author Radish
 * @Date 2020-09-02 11:05
 */
public class MsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
    	buf.writeInt(msg.getMsgType().ordinal());
    	byte[] bytes = msg.toBytes();
    	buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }
}
