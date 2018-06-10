package me.codestring.networkcore;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Yonathan on 09.06.2018 19:40
 */
public class PacketDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> output) throws Exception {
    int packetId = byteBuf.readInt();
    int callbackId = byteBuf.readInt();
    int responeId = byteBuf.readInt();
    Class<? extends Packet> pClass = PacketManager.getIdWithPacket().get(packetId);
    if(pClass == null) {
      System.out.println("Could not get Packet by ID: " + packetId);
      return;
    }

    Packet packet = pClass.newInstance();
    packet.responseId = callbackId;
    packet.read(byteBuf, ctx);
    output.add(packet);

    if(responeId != -1)
      PacketManager.executeCallback(responeId, packet);

  }
}
