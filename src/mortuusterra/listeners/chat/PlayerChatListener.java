package mortuusterra.listeners.chat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class PlayerChatListener implements Listener {
	Main main = JavaPlugin.getPlugin(Main.class);

	private long distance;

	private Location senderLocation;
	private Location recieverLocation;
	private Location cellTowerLocation;

	private boolean senderHasRadio = false;
	private boolean recieverHasRadio = false;

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		senderLocation = e.getPlayer().getLocation();
		// check if the sender has a radio, then check if the player is in range of a
		// cell tower or another player, then check if there are other players in range
		// if cell tower and if so then send the masage raw only to the players that
		// have a raio, all other players recieve a mixed up messaages. if not then dont
		// send message.
		if (isSenderHasRadio(e.getPlayer()) == true) {
			
		}
	}

	public boolean isSenderHasRadio(Player p) {
		if(p.getInventory().contains(Material.JUKEBOX)) {
			return true;
		}else return false;
		
	}
	public boolean isInRangeOfCellTower(Player p) {
		for(Location loc : main.getCellTowerManager().getCellTowerLocationsMap().keySet()) {
			if()
		}
		return false;
	}
}
