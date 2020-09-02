package com.mashibing.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import com.mashibing.tank.Dir;
import com.mashibing.tank.Group;

/**
 * @Description TankMsgCodecTest
 *  
 * @最后两个方法才是
 * @Author Radish
 * @Date 2020-09-02 11:23
 */
public class TankJoinMsgCodecTest {
    @Test
    public void testTankMsgEncoder() {
    	UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5,5,Dir.DOWN,false,Group.BAD,id);
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankJoinMsgEncoder());
        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf) ch.readOutbound();
        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        boolean moving = buf.readBoolean();
        Group group = Group.values()[buf.readInt()];
        UUID id1 = new UUID(buf.readLong(),buf.readLong());

        Assert.assertEquals(5, x);
        Assert.assertEquals(5, y);
        Assert.assertEquals(Dir.DOWN, dir);
        Assert.assertEquals(false, moving);
        Assert.assertEquals(Group.BAD, group);
        Assert.assertEquals(id, id1);
        buf.release();
    }

    @Test
    public void testTankMsgEncoder2() {
        ByteBuf buf = Unpooled.buffer();    	
        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(10, 10,Dir.DOWN,false,Group.BAD,id);
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);
        buf.writeInt(msg.dir.ordinal());
        buf.writeBoolean(msg.moving);
        buf.writeInt(msg.group.ordinal());
        buf.writeLong(msg.id.getMostSignificantBits());
        buf.writeLong(msg.id.getLeastSignificantBits());

        EmbeddedChannel ch = new EmbeddedChannel(new TankJoinMsgEncoder(), new TankJoinMsgDecoder());
        ch.writeInbound(buf.duplicate());

        TankJoinMsg tm = (TankJoinMsg) ch.readInbound();
        Assert.assertEquals(10, tm.x);
        Assert.assertEquals(10, tm.y);
        Assert.assertEquals(Dir.DOWN, tm.dir);
        Assert.assertEquals(false, tm.moving);
        Assert.assertEquals(Group.BAD, tm.group);
        Assert.assertEquals(id, tm.id);
    }
    
    @Test
    public void testDecoder() {
    	EmbeddedChannel ch = new EmbeddedChannel();
    	UUID id = UUID.randomUUID();
    	TankJoinMsg msg1 = new TankJoinMsg(10, 10,Dir.DOWN,false,Group.BAD,id);
    	
    	ch.pipeline().addLast(new TankJoinMsgDecoder());
    	ByteBuf buf = Unpooled.buffer();
    	buf.writeBytes(msg1.toBytes());
    	ch.writeInbound(buf.duplicate());
    	
    	TankJoinMsg tm = (TankJoinMsg) ch.readInbound();
    	Assert.assertEquals(10, tm.x);
        Assert.assertEquals(10, tm.y);
        Assert.assertEquals(Dir.DOWN, tm.dir);
        Assert.assertEquals(false, tm.moving);
        Assert.assertEquals(Group.BAD, tm.group);
        Assert.assertEquals(id, tm.id);
    }
    
    @Test
	public void testEncoder() {
		EmbeddedChannel ch = new EmbeddedChannel();
		
		
		UUID id = UUID.randomUUID();
		TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD, id);
		ch.pipeline()
			.addLast(new TankJoinMsgEncoder());
		
		ch.writeOutbound(msg);
		
		ByteBuf buf = (ByteBuf)ch.readOutbound();
		
		int x = buf.readInt();
		int y = buf.readInt();
		int dirOrdinal = buf.readInt();
		Dir dir = Dir.values()[dirOrdinal];
		boolean moving = buf.readBoolean();
		int groupOrdinal = buf.readInt();
		Group g = Group.values()[groupOrdinal];
		UUID uuid = new UUID(buf.readLong(), buf.readLong());
		
		assertEquals(5, x);
		assertEquals(10, y);
		assertEquals(Dir.DOWN, dir);
		assertEquals(true, moving);
		assertEquals(Group.BAD, g);
		assertEquals(id, uuid);
	}
	
	@Test
	public void testDecoder1() {
		EmbeddedChannel ch = new EmbeddedChannel();
		
		
		UUID id = UUID.randomUUID();
		TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD, id);
		ch.pipeline()
			.addLast(new TankJoinMsgDecoder());
		
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(msg.toBytes());
		
		ch.writeInbound(buf.duplicate());
		
		TankJoinMsg msgR = (TankJoinMsg)ch.readInbound();
		
		
		
		assertEquals(5, msgR.x);
		assertEquals(10, msgR.y);
		assertEquals(Dir.DOWN, msgR.dir);
		assertEquals(true, msgR.moving);
		assertEquals(Group.BAD, msgR.group);
		assertEquals(id, msgR.id);
	}
}
