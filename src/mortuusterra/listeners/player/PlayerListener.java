package mortuusterra.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerListener implements Listener{
Main main = JavaPlugin.getPlugin(Main.class);

    Player deathPlayer;
    Player respawnPlayer;
    Zombie zombie;
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
        }.runTaskTimer(main, 0L, 40L);
	}
	
	@EventHandler
	private void onPlayerLeaveEvent(PlayerQuitEvent e) {
		main.getPlayerManager().removeRadPlayer(e.getPlayer());
	}
	
	//@SuppressWarnings("static-access")
	@EventHandler
	private void onPlayerDeathEvent(PlayerDeathEvent e) {
		//MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
		//main.getDisguiseAPI().disguiseEntity(e.getEntity(), disguise);
		return;
	}
}
