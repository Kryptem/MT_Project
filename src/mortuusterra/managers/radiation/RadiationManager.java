package mortuusterra.managers.radiation;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class RadiationManager {

	private int playerX;
	private int playerZ;
	private int playerY;
	private int radStrangth = 1;

	private String uuid;

	private Location highestLocationAtPlayer;
	private Location playerLocation;

	Main main = JavaPlugin.getPlugin(Main.class);

	// This is were we check if each player online is in the hashmap, and if they
	// are not in a building, and checks if the player is not in range of a GECK,
	// then give them radiation.
	public void CheckEachPlayerLocation() {
		// check if player is outside or under a building. or in range of a GECK
		for (Player p : main.getServer().getOnlinePlayers()) {
			if (!(isPlayerInBuilding(p))) {
				checkPlayerRange(p);
				if (!(main.getPlayerManager().getRadPlayer(uuid).getplayerInRangeOfGeck())) {
					givePlayerRads(p);
				}
			}
		}
	}

	public void givePlayerRads(Player p) {
		p.damage(radStrangth);
	}

	private boolean isPlayerInBuilding(Player p) {
		this.uuid = p.getUniqueId().toString();
		this.playerLocation = p.getLocation();
		this.playerX = playerLocation.getBlockX();
		this.playerZ = playerLocation.getBlockZ();
		this.playerY = playerLocation.getWorld().getHighestBlockYAt(playerX, playerZ);
		this.highestLocationAtPlayer = new Location(p.getWorld(), playerX, playerY, playerZ);

		if ((playerLocation.getBlockY() - highestLocationAtPlayer.getBlockY()) < 0) {
			main.getPlayerManager().getRadPlayer(uuid).setPlayerInBuilding(true);
			return true;
		}
		main.getPlayerManager().getRadPlayer(uuid).setPlayerInBuilding(false);
		return false;
	}

	private void checkPlayerRange(Player p) {
		main.getGeckRangeManager().checkPlayers(p);
	}
}
