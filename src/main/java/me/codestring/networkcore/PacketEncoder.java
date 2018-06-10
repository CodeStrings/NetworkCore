package me.codestring.networkcore;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Yonathan on 09.06.2018 19:40
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {


  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) {
   byteBuf.writeInt(PacketManager.getPacketIds().get(packet.getClass()));
   byteBuf.writeInt(packet.id);
   byteBuf.writeInt(packet.responseId);
   packet.write(byteBuf);
  }

}
