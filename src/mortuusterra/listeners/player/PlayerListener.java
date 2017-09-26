package mortuusterra.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.MortuusTerraCore;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener{

MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);
  
    //Disguise disguise = DisguiseAPI.constructDisguise(zombie);
	
	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addRadPlayer(e.getPlayer());
		new BukkitRunnable(){

            @Override
            public void run() {

                if (e.getPlayer() == null || !Bukkit.getOnlinePlayers().contains(e.getPlayer())){
                    cancel();
                    return;
                }

                main.getRadiationManager().checkPlayerLoc(e.getPlayer());

            }
        }.runTaskTimer(main, 0L, 100L);
	}
	
	@EventHandler
	private void onPlayerLeaveEvent(PlayerQuitEvent e) {
		main.getPlayerManager().removeRadPlayer(e.getPlayer());
	}
	
	//@SuppressWarnings("static-access")
	//@EventHandler
	//private void onPlayerDeathEvent(PlayerDeathEvent e) {
		//MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
		//main.getDisguiseAPI().disguiseEntity(e.getEntity(), disguise);
	//	return;
	//}
}
