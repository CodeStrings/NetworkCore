package me.codestring.networkcore;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by Yonathan on 09.06.2018 19:40
 */
public class PacketManager {

  private static List<Integer> avaibleIds = new ArrayList<>();

  @Getter
  private static Map<Class<? extends Packet>, Integer> packetIds = new HashMap<>();

  @Getter
  private static Map<Integer, Class<? extends Packet>> idWithPacket = new HashMap<>();

  @Getter
  private static Map<Integer, Consumer<Packet>> packetCallbacks = new HashMap<>();

  public static void addCallback(Packet packet, Consumer<Packet> consumer) {

    int id;
    if(!avaibleIds.isEmpty()) {
      id = avaibleIds.get(0);
      avaibleIds.remove(0);
    } else {
      id = avaibleIds.size();
    }
    packet.id = id;
    packetCallbacks.put(id, consumer);
  }

  public static void registerPacket(Packet packet, int id) {
    packetIds.put(packet.getClass(), id);
    idWithPacket.put(id, packet.getClass());
  }

  public static void executeCallback(int id, Packet packet) {
    if(packetCallbacks.containsKey(id)) {
      packetCallbacks.get(id).accept(packet);
      packetCallbacks.remove(id);
      avaibleIds.add(id);
    } else {
      System.out.println("CallBack was send, but no Callback was requested...");
    }
  }

}
