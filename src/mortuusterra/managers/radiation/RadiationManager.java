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

	private Location highestLocationAtPlayer;
	private PlayerManager playerMan;

	Main main = JavaPlugin.getPlugin(Main.class);

	public void CheckEachPlayerLocation() {
		//check if player is outside or under a building. 
		for (Player p : main.getServer().getOnlinePlayers()) {
			playerMan = main.getPlayerManager();
			playerMan.addRadPlayer(p);
			String uuid = p.getUniqueId().toString();

			Location playerLocation = p.getLocation();
			playerX = playerLocation.getBlockX();
			playerZ = playerLocation.getBlockZ();
			playerY = playerLocation.getWorld().getHighestBlockYAt(playerX, playerZ);

			highestLocationAtPlayer = new Location(p.getWorld(), playerX, playerY, playerZ);

			if ((playerLocation.getBlockY() - highestLocationAtPlayer.getBlockY()) < 0) {
				playerMan.addRadPlayer(p);
				playerMan.getRadPlayer(uuid).isPlayerInBuilding(true);
			} else {
				playerMan.getRadPlayer(uuid).isPlayerInBuilding(false);
				if(main.getGeckPowerListener().isPowered() && main.getGeckPowerListener().isIncorrect() == false) {
					main.GetRadiationDamageEvent().setCancelled(true);
				}else {
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
