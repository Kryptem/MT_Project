package com.mortuusterra.managers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.mortuusterra.MortuusTerraCore;

public class RadiationManager {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	// This is were we check if each player online is in the hashmap, and if
	// they
	// are not in a building, and checks if the player is not in range of a
	// GECK,
	// then give them radiation.

	// Changed method so this is independent for each player. Use

	/**
	 * public void CheckEachPlayerLocation() { // check if player is outside or
	 * under a building. or in range of a GECK
	 * 
	 * main.getServer().getOnlinePlayers().stream().filter(this::
	 * isPlayerInBuilding).forEach(p -> { checkPlayerRange(p); if
	 * (!(main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).
	 * getPlayerInRangeOfGeck())) givePlayerRads(p); }); }
	 * 
	 **/

	public void damage(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
	
				if (isPlayerInRad(player)) {
					player.damage(calculateRadiationDamage(player));
				}
			}

		}.runTaskTimer(main, 0L, 3 * 20L);
	}

	
	/** Method to calculate the radiation damage based on conditions.
	 * @param player The player whose damage should be calculated
	 * @return The calculated damage
	 */
	private double calculateRadiationDamage(Player player) {
		double receivedDamage = 1D;

		if (isPlayerInBuilding(player)) {
			return 0D;
		}

		if (player.getWorld().hasStorm()) {
			receivedDamage += 1D;
		}

		if (player.getLocation().getBlock().getType() == Material.WATER
				|| player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
			receivedDamage += 2D;
		}

		return receivedDamage;
	}

	private boolean isPlayerInBuilding(Player p) {
		Location playerLocation = p.getLocation();
		int highestY = playerLocation.getWorld().getHighestBlockYAt(playerLocation);

		return (playerLocation.getBlockY() < highestY - 1);
	}

	public boolean isPlayerInRad(Player player) {
		// Returns true if player is in building and in range of geck
		return (!isPlayerInBuilding(player) && !main.getGeckManager().isPlayerInGeckRange(player));

	}

}
