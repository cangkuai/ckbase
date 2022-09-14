package cn.ckapp.ckbase;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
public class gamemodechange {
    public static String[] languages;
    public void setLanguages(String[] languages1){
        languages=languages1;
    }
    public  boolean gamemodechange(String[] command,int modename,Player player,java.util.List<net.minecraft.server.level.ServerPlayer> playerList){
        ServerPlayer player1;
        if (command.length==3){
            player1=tool.getplayerbyname(command[2],playerList);
            if(player1==null){
                tool.sendmassages(player,languages[3]);
                return true;
            }
        }else {
            player1=tool.getplayerbyname(player.getName().getString(),playerList);
        }
        switch (modename){
            case 0:
                player1.setGameMode(GameType.SURVIVAL);
                tool.sendmassages(player,languages[11]);
                return true;
            case 1:
                player1.setGameMode(GameType.CREATIVE);
                tool.sendmassages(player,languages[11]);
                return true;
            case 2:
                player1.setGameMode(GameType.ADVENTURE);
                tool.sendmassages(player,languages[11]);
                return true;
            case 3:
                player1.setGameMode(GameType.SPECTATOR);
                tool.sendmassages(player,languages[11]);
                return true;
        }
        return false;
    }
    public int getmodename(String[] command){
        switch (command[1]){
            case "0":
            case "survival":
                return 0;
            case "1":
            case "creative":
                return 1;
            case "2":
            case "adventure":
                return 2;
            case "3":
            case "spectator":
                return 3;
        }
        return 4;
    }
}
