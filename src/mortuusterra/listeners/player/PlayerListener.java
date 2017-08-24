package mortuusterra.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class PlayerListener implements Listener{
Main main = JavaPlugin.getPlugin(Main.class);
	
	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addRadPlayer(e.getPlayer());
	}
	
	@EventHandler
	private void onPlayerLeaveEvent(PlayerQuitEvent e) {
		main.getPlayerManager().removeRadPlayer(e.getPlayer());
	}
}
