package cn.ckapp.ckbase;

import net.minecraft.world.entity.player.Player;

public class tool {
    public static void sendmassages(Player player,String msg){
        player.sendSystemMessage(net.minecraft.network.chat.Component.literal(msg));
    }
    public static net.minecraft.server.level.ServerPlayer getplayerbyname(String players,java.util.List<net.minecraft.server.level.ServerPlayer> playlist){
        String names;
        for (net.minecraft.server.level.ServerPlayer i:playlist) {
            names=i.getName().getString();
            if (players.equals(names)){
                return i;
            }
        }
        return null;
    }
    public static boolean playisonline(String players,java.util.List<net.minecraft.server.level.ServerPlayer> playlist){
        String names;
        for (net.minecraft.server.level.ServerPlayer i:playlist) {
            names=i.getName().getString();
            if (players.equals(names)){
                return true;
            }
        }
        return false;
    }
}
