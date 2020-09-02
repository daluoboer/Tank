package com.mashibing.tank.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description ServerFrame
 * @Author Radish
 * @Date 2020-09-01 22:55
 */
public class ServerFrame extends Frame {
    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.serverStart();
    }

    public static final ServerFrame INSTANCE = new ServerFrame();

    Button btnStart = new Button("start");
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Server server = new Server();

    private ServerFrame() {
        this.setTitle("Server");
        this.setSize(1600,600);
        this.setLocation(300,30);
        this.add(btnStart,BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1,2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public void updateServerMsg(String s) {
        this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + s);
    }

    public void updateClientMsg(String s) {
        this.taRight.setText(taRight.getText() + System.getProperty("line.separator") + s);
    }
}
