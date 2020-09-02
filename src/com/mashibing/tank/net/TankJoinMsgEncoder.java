package com.mashibing.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Description TankMsgEncoder
 * @Author Radish
 * @Date 2020-09-02 11:05
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<TankJoinMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf buf) throws Exception {
        buf.writeBytes(msg.toBytes());
    }
}
