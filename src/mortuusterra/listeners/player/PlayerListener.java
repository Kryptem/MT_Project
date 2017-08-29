package mortuusterra.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import mortuusterra.Main;

public class PlayerListener implements Listener{
Main main = JavaPlugin.getPlugin(Main.class);

    Player deathPlayer;
    Player respawnPlayer;
    Zombie zombie;
    //Disguise disguise = DisguiseAPI.constructDisguise(zombie);
	
	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addRadPlayer(e.getPlayer());
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
