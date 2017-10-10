package com.mortuusterra.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.mortuusterra.MortuusTerraCore;

public class RadiationManager {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	public void startPlayerRadiationDamage() {
		new BukkitRunnable() {

			@Override
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					// PlayerObject mortuusPlayer =
					// main.getPlayerManager().getMortuusPlayer(p.getUniqueId());

					if (isPlayerInRad(p))
						p.damage(calculateRadiationDamage(p));
				}
			}
		}.runTaskTimer(main, 0L, 3 * 20L);
	}

	/**
	 * Method to calculate the radiation damage based on conditions.
	 * 
	 * @param player
	 *            The player whose damage should be calculated
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
