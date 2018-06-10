package me.codestring.networkcore;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Yonathan on 09.06.2018 19:31
 */
public abstract class Packet {

  int id = -1;
  int responseId = -1;
  abstract void read(ByteBuf byteBuf, ChannelHandlerContext ctx);
  abstract void write(ByteBuf byteBuf);

  void sendRespone(Packet packet) {
    packet.responseId = responseId;
  }

  String getString(ByteBuf byteBuf) {
    int length = byteBuf.readInt();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      stringBuilder.append(getStringFromBytes(byteBuf.readByte()));
    }
    return stringBuilder.toString();
  }

  String getStringFromBytes(byte _bytes) {
    String s = "";
    s = String.valueOf((char) _bytes);
    return s;
  }

  void writeString(String string, ByteBuf byteBuf) {
    byteBuf.writeInt(string.getBytes().length);
    byteBuf.writeBytes(string.getBytes());
  }

}
