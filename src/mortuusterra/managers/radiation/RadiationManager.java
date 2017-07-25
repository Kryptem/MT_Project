package mortuusterra.managers.radiation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;
import mortuusterra.managers.player.PlayerManager;

public class RadiationManager {

	private int playerX;
	private int playerZ;
	private int playerY;
	
	private long elapsedTime;
	private long radStrangth;
	private long radDecrement;
	private long seconds;

	private Location highestLocationAtPlayer;
	private PlayerManager playerMan;

	Main main  = JavaPlugin.getPlugin(Main.class);
	public void CheckEachPlayerLocation() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			main.getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + "There are no online players right now!");
		} else {
			for (Player p : main.getServer().getOnlinePlayers()) {
				playerMan.addPlayer(p);
				String uuid = p.getUniqueId().toString();

				Location playerLocation = p.getLocation();
				playerX = playerLocation.getBlockX();
				playerZ = playerLocation.getBlockZ();
				playerY = playerLocation.getWorld().getHighestBlockYAt(playerX, playerZ);

				highestLocationAtPlayer = new Location(p.getWorld(), playerX, playerY, playerZ);

				if ((playerLocation.getBlockY() - highestLocationAtPlayer.getBlockY()) < 0) {
					playerMan.getPlayer(uuid).isPlayerInBuilding(true);
				} else {
					playerMan.getPlayer(uuid).isPlayerInBuilding(false);
					main.getServer().getConsoleSender()
					.sendMessage(ChatColor.DARK_GREEN + "Apply Rads!");
				}
			}
		}
	}

	public void givePlayerRads(Player p) {
		// calculate the rads for the player according to the elapsed time.
		main.getElapsedTime().setupElapsedtime();
		elapsedTime = main.getElapsedTime().getElapsedTime();
		seconds = (elapsedTime / 1000000000); // 1 second = 1e+9 or 1000000000 nanoseconds. 
		radDecrement = (seconds / 30);
		radStrangth = 1 - (radDecrement);
		main.getServer().getConsoleSender().sendMessage("giving " + radStrangth + "much rads");
		
		p.damage(radStrangth);
	}
}
