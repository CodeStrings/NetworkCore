package me.codestring.networkcore;

import io.netty.buffer.ByteBuf;

/**
 * Created by Yonathan on 20.05.2018 19:07
 */
public class StringUtil {

  public static String getString(ByteBuf byteBuf) {
    int length = byteBuf.readInt();
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      stringBuilder.append(getStringFromBytes(byteBuf.readByte()));
    }
    return stringBuilder.toString();
  }

  private static String getStringFromBytes(byte _bytes) {
    String s = "";
    s = String.valueOf((char) _bytes);
    return s;
  }

  public static void writeString(String text, ByteBuf byteBuf) {
    byteBuf.writeInt(text.getBytes().length);
    byteBuf.writeBytes(text.getBytes());
  }

}
