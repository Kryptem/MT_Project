package mortuusterra.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import mortuusterra.Main;

public class PlayerListener implements Listener{
	Main main;
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		main.getPlayerManager().addPlayer(e.getPlayer());
		main.getRadaionTimer().run();
	}
}
