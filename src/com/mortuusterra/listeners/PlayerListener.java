package com.mortuusterra.listeners;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.PlayerObject;
import com.mortuusterra.utils.others.StringUtilities;

public class PlayerListener implements Listener {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	// Disguise disguise = DisguiseAPI.constructDisguise(zombie);

	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addMortuusPlayer(e.getPlayer());
	}

	@EventHandler
	private void onPlayerLeaveEvent(PlayerQuitEvent e) {

	}

	@EventHandler
	public void onPlayerGetDamagedByZombie(EntityDamageByEntityEvent e) {
		Entity victim = e.getEntity();
		Entity damager = e.getDamager();

		if (!(victim instanceof Player) || !(damager instanceof Zombie))
			return;

		Player p = (Player) victim;
		Random r = new Random();

		int infectionChance = r.nextInt(100);

		// 15% chance of getting infected
		if (infectionChance < 15) {
			PlayerObject mtPlayer = main.getPlayerManager().getMortuusPlayer(p.getUniqueId());
			if (!mtPlayer.isInfected() && mtPlayer != null) {
				p.sendMessage(MortuusTerraCore.NOTI_PREFIX + StringUtilities.color("&2I feel strange..."));
				mtPlayer.setInfected(true);
			}
		}

	}

	// @SuppressWarnings("static-access")
	// @EventHandler
	// private void onPlayerDeathEvent(PlayerDeathEvent e) {
	// MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
	// main.getDisguiseAPI().disguiseEntity(e.getEntity(), disguise);
	// return;
	// }
}
