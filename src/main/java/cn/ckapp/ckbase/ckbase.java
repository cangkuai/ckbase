package cn.ckapp.ckbase;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import java.util.HashMap;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ckbase.MODID)
public class ckbase
{
    public static final HashMap<String, String> tpalist = new HashMap<String, String>();
    public static HashMap<String, String> sets = new HashMap<String, String>();
    public static final String MODID = "ckbase";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String dir = System.getProperty("user.dir")+"/mods/ckbase";
    public final iohelper iohelpers=new iohelper();
    public final gamemodechange gamemodechanges =new gamemodechange();
    public static String[] languages;
    public ckbase()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("ckbase loading");
        ckloader.opening(dir);
        iohelpers.setdir(dir);
        iohelpers.read();
        sets=ckloader.getsets(dir);
        languages=ckloader.getlanguage(sets.get("language"),dir);
        gamemodechanges.setLanguages(languages);
        LOGGER.info("ckbase load successfully");
    }
    @SubscribeEvent
    public void onServerClose(ServerStoppingEvent event) {
        LOGGER.info("ckbase saving data");
        iohelpers.savedata();
        LOGGER.info("ckbase`s data save successfully");
    }
    @SubscribeEvent
    public void playerdead(LivingDeathEvent event)
    {
        Entity entity=event.getEntity();
        if (!(entity instanceof Player)) return;
        iohelpers.setdata("back",entity.getName().getString(),new Double[]{entity.getX(),entity.getY(),entity.getZ()});
    }
    @SubscribeEvent
    public void onPlayerRunCommand(CommandEvent event) {
        Entity entity = event.getParseResults().getContext().getSource().getEntity();
        java.util.List<net.minecraft.server.level.ServerPlayer> playlist=event.getParseResults().getContext().getSource().getServer().getPlayerList().getPlayers();
        if (!(entity instanceof Player)) return;
        Player player = event.getParseResults().getContext().getSource().getPlayer();
        String raw_command = event.getParseResults().getReader().getString();
        String[] command = raw_command.substring(0).split(" ");
        switch (command[0]){
            case "tpa":
                if(tool.playisonline(command[1],playlist)){
                    Player tpkeys=tool.getplayerbyname(command[1],playlist);
                    if (tpkeys==null){
                        tool.sendmassages(player,languages[0]);
                        break;
                    }
                    else {
                        tpalist.put(tpkeys.getName().getString(), player.getName().getString());
                        tool.sendmassages(player,languages[1]);
                        tool.sendmassages(tpkeys,player.getName().getString()+languages[2]);
                    }
                }
                else {
                    tool.sendmassages(player,languages[3]);
                }
                event.setCanceled(true);
                break;
            case "tpaccept":
                String names=tpalist.get(player.getName().getString());
                if(names==null){
                    tool.sendmassages(player,languages[4]);
                    event.setCanceled(true);
                    break;
                }else {
                    Player otherplay=tool.getplayerbyname(names,playlist);
                    otherplay.teleportTo(player.getX(),player.getY(),player.getZ());
                    tool.sendmassages(otherplay,languages[5]);
                    iohelpers.setdata("back",otherplay.getName().getString(),new Double[]{player.getX(),player.getY(),player.getZ()});
                    event.setCanceled(true);
                    break;
                }
            case "gm":
                if(!(ServerLifecycleHooks.getCurrentServer().getPlayerList().isOp(player.getGameProfile()))){
                    tool.sendmassages(player,languages[6]);
                    event.setCanceled(true);
                    break;
                }else {
                    int modename= gamemodechanges.getmodename(command);
                    if(modename==4){
                        tool.sendmassages(player,languages[0]);
                        break;
                    }else {
                        if(!(gamemodechanges.gamemodechange(command,modename,player,playlist))){
                            tool.sendmassages(player,languages[0]);
                            break;
                        }
                    }
                }
                event.setCanceled(true);
                break;
            case "home":
                Double[] ls=iohelpers.getdata("home",player.getName().getString());
                if (ls==null){
                    tool.sendmassages(player,languages[7]);
                    event.setCanceled(true);
                    break;
                }else {
                    player.teleportTo(ls[0],ls[1],ls[2]);
                    tool.sendmassages(player,languages[5]);
                    iohelpers.setdata("back",player.getName().getString(),ls);
                    event.setCanceled(true);
                    break;
                }
            case "sethome":
                iohelpers.setdata("home",player.getName().getString(),new Double[]{player.getX(),player.getY(),player.getZ()});
                tool.sendmassages(player,languages[8]);
                event.setCanceled(true);
                break;
            case "setwarp":
                if(!(ServerLifecycleHooks.getCurrentServer().getPlayerList().isOp(player.getGameProfile()))) {
                    tool.sendmassages(player, languages[6]);
                    event.setCanceled(true);
                    break;
                }else {
                    iohelpers.setdata("warp",command[1],new Double[]{player.getX(),player.getY(),player.getZ()});
                    tool.sendmassages(player,languages[8]);
                    event.setCanceled(true);
                    break;
                }
            case "delwarp":
                if(!(ServerLifecycleHooks.getCurrentServer().getPlayerList().isOp(player.getGameProfile()))) {
                    tool.sendmassages(player, languages[6]);
                    event.setCanceled(true);
                    break;
                }else {
                    iohelpers.deldata("warp", command[1]);
                    tool.sendmassages(player, languages[8]);
                    event.setCanceled(true);
                    break;
                }
            case "warp":
                Double[] ls1=iohelpers.getdata("warp",command[1]);
                if (ls1==null){
                    tool.sendmassages(player,languages[9]);
                    event.setCanceled(true);
                    break;
                }else {
                    player.teleportTo(ls1[0],ls1[1],ls1[2]);
                    tool.sendmassages(player,languages[5]);
                    iohelpers.setdata("back",player.getName().getString(),ls1);
                    event.setCanceled(true);
                    break;
                }
            case "back":
                Double[] ls2=iohelpers.getdata("back",player.getName().getString());
                if (ls2==null){
                    tool.sendmassages(player,languages[10]);
                    event.setCanceled(true);
                    break;
                }else {
                    player.teleportTo(ls2[0],ls2[1],ls2[2]);
                    tool.sendmassages(player,languages[5]);
                    iohelpers.setdata("back",player.getName().getString(),ls2);
                    event.setCanceled(true);
                    break;
                }
        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}