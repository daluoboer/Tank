package com.mashibing.tank.net;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.awt.*;

import com.mashibing.tank.Tank;
import com.mashibing.tank.TankFrame;

/**
 * @Description Client
 * @Author Radish
 * @Date 2020-09-02 08:15
 */
public class Client {
	public static final Client INSTANCE = new Client();
	
	private Client() {}
	
    public Channel channel = null;

    public void connect() {
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TankJoinMsgEncoder()).addLast(new TankJoinMsgDecoder()).addLast(new SimpleChannelInboundHandler<TankJoinMsg>(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
                                }

								@Override
								protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
									msg.handle();
								}
                            });
                        }
                    })
                    .channel(NioSocketChannel.class)
                    .connect("localhost",8888)
                    .addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            if (!future.isSuccess()) {
                                System.out.println("not connected!");
                            } else {
                                System.out.println("connected!");
                                channel = future.channel();
                            }
                        }
                    })
                    .sync()
                    .channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void send(TankJoinMsg tankJoinMsg) {
        channel.writeAndFlush(tankJoinMsg);
    }

    public void closeConnect() {
    }
}
