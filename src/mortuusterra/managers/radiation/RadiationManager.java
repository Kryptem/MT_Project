package mortuusterra.managers.radiation;

import java.util.concurrent.TimeUnit;

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

	private String uuid;

	private Location highestLocationAtPlayer;
	private Location playerLocation;

	private PlayerManager playerMan;

	Main main = JavaPlugin.getPlugin(Main.class);

	public void CheckEachPlayerLocation() {
		// check if player is outside or under a building. or in range of a GECK
		for (Player p : main.getServer().getOnlinePlayers()) {
			playerMan = main.getPlayerManager();
			playerMan.addRadPlayer(p);

			uuid = p.getUniqueId().toString();
			playerLocation = p.getLocation();
			playerX = playerLocation.getBlockX();
			playerZ = playerLocation.getBlockZ();
			playerY = playerLocation.getWorld().getHighestBlockYAt(playerX, playerZ);

			highestLocationAtPlayer = new Location(p.getWorld(), playerX, playerY, playerZ);

			// check if player is under a block/building
			if ((playerLocation.getBlockY() - highestLocationAtPlayer.getBlockY()) < 0) {
				playerMan.getRadPlayer(uuid).setPlayerInBuilding(true);
			} else {
				playerMan.getRadPlayer(uuid).setPlayerInBuilding(false);
				main.getGeckRangeManager().checkPlayers(p);
				playerMan.addGeckPlayer(p);
				if (playerMan.getGeckPlayer(uuid).getplayerInRangeOfGeck()) {
					return;
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
}
