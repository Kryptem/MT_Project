package mortuusterra.managers.radiation;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.MortuusTerraCore;

public class RadiationManager {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	// This is were we check if each player online is in the hashmap, and if they
	// are not in a building, and checks if the player is not in range of a GECK,
	// then give them radiation.

	// Changed method so this is independent for each player. Use
	// checkPlayerLoc(player) instead.
	
	/**
	public void CheckEachPlayerLocation() {
		// check if player is outside or under a building. or in range of a GECK

		main.getServer().getOnlinePlayers().stream().filter(this::isPlayerInBuilding).forEach(p -> {
			checkPlayerRange(p);
			if (!(main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).getPlayerInRangeOfGeck()))
				givePlayerRads(p);
		});
	}
	
	**/

	// Will damage player if they are not in range of a GECK and they are not in a
	// building.
	public void checkPlayerLoc(Player player) {

		if (!isPlayerInBuilding(player)) {

			checkPlayerRange(player);

			if (!main.getPlayerManager().getRadPlayer(player.getUniqueId().toString()).getPlayerInRangeOfGeck()) {
				givePlayerRads(player);
			}
		}

	}

	private void givePlayerRads(Player p) {
		double radStrength = 1.0D;
		p.damage(radStrength);
	}

	private boolean isPlayerInBuilding(Player p) {
		Location playerLocation = p.getLocation();
		int highestY = playerLocation.getWorld().getHighestBlockYAt(playerLocation);
		return (playerLocation.getBlockY() < highestY - 1);
	}

	public boolean isPlayerInRad(Player p) {
		if (!isPlayerInBuilding(p)) {
			checkPlayerRange(p);
			if (!main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).getPlayerInRangeOfGeck()) {
				return true;
			}
		}
		return false;

	}

	private void checkPlayerRange(Player p) {
		main.getGeckRangeManager().checkPlayers(p);
	}
}
