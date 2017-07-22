package mortuusterra.managers.radiation;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import mortuusterra.Main;
import net.md_5.bungee.api.ChatColor;

public class RadiationManager {
	
	protected int playerX;
	protected int playerZ;
	protected int playerY;
	
	protected boolean playerInBuilding = false;
	
	protected Location highestLocationAtPlayer;

	public void CheckEachPlayerLocation() {
		if (Main.plugin.getServer().getOnlinePlayers().isEmpty()) {
			Main.plugin.getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + "There are no online players right now!");
		} else {
			for (Player p : Main.plugin.getServer().getOnlinePlayers()) {
				Location playerLocation = p.getLocation();
				playerX = playerLocation.getBlockX();
				playerZ = playerLocation.getBlockZ();
				playerY = playerLocation.getWorld().getHighestBlockYAt(playerX, playerZ);
				
				highestLocationAtPlayer = new Location(p.getWorld(), playerX, playerY, playerZ);
				
				if((playerLocation.getBlockY() - highestLocationAtPlayer.getBlockY()) < 0) {
					isPlayerInBuilding(true);
				}else {
					isPlayerInBuilding(false);
				}
			}
		}
	}

	public void isPlayerInBuilding(boolean bool) {
		this.playerInBuilding = bool;
	}
	
	public void givePlayerRads(Player p) {
		//calculate the rads for the player according to the elapsed time.
	}
}
