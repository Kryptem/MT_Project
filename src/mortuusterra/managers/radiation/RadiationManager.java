package mortuusterra.managers.radiation;

import java.util.concurrent.TimeUnit;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class RadiationManager {

	private int playerX;
	private int playerZ;
	private int playerY;

	private long elapsedTime;
	private long radStrangth;
	private long radDecrement;
	private long seconds;

	private String uuid;

	private Location highestLocationAtPlayer;
	private Location playerLocation;

	Main main = JavaPlugin.getPlugin(Main.class);

	public void CheckEachPlayerLocation() {
		// check if player is outside or under a building. or in range of a GECK
		for (Player p : main.getServer().getOnlinePlayers()) {
			main.getPlayerManager().addRadPlayer(p);

			if (isPlayerInBuilding(p)) {
				return;
			} else {
				main.getPlayerManager().getRadPlayer(uuid).setPlayerInBuilding(false);
				main.getGeckRangeManager().checkPlayers(p);
				if (main.getPlayerManager().containsGeckPlayer(uuid)) {
					if (main.getPlayerManager().getGeckPlayer(uuid).getplayerInRangeOfGeck()) {
						return;
					} else {
						givePlayerRads(p);
					}
				} else {
					givePlayerRads(p);
				}
			}
		}
	}

	public void givePlayerRads(Player p) {
		// calculate the rads for the player according to the elapsed time.
		main.getElapsedTime().setupElapsedtime();
		elapsedTime = main.getElapsedTime().getElapsedTime();
		seconds = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
		radDecrement = 0;
		radStrangth = 1 - ((seconds / 50) - radDecrement);
		p.damage(radStrangth);

		if (radStrangth < 2) {
			return;
		} else if (radStrangth >= 2) {
			main.getElapsedTime().setTimeStart(0L);
			radDecrement = (radDecrement - 1);
		}
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
		return false;
	}
}
