package com.mortuusterra.listeners;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.FalloutShelter;
import com.mortuusterra.objects.PlayerObject;

public class PlayerListener implements Listener {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	// Disguise disguise = DisguiseAPI.constructDisguise(zombie);

	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addMortuusPlayer(e.getPlayer());
		PlayerObject mortuusPlayer = main.getPlayerManager().getMortuusPlayer(e.getPlayer().getUniqueId());

		if (mortuusPlayer.isInfected() && mortuusPlayer.getInfectedState() > 0) {
			mortuusPlayer.startInfection();
		}
	}

	@EventHandler
	private void onPlayerLeaveEvent(PlayerQuitEvent e) {
		PlayerObject mortuusPlayer = main.getPlayerManager().getMortuusPlayer(e.getPlayer().getUniqueId());

		if (mortuusPlayer != null) {
			if (mortuusPlayer.getTask() != null) {
				mortuusPlayer.getTask().cancel();
			}
		}
	}

	@EventHandler
	public void test(PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.OFF_HAND)
			return;
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
		if (e.getItem() == null)
			return;
			
		if (e.getItem().getType() != Material.DIAMOND_HOE)
			return;
			FalloutShelter s = new FalloutShelter(e.getClickedBlock().getLocation().clone());
			s.generateFalloutShelter();
		}
	}

	// @EventHandler
	// public void onPlayerGetDamagedByZombie(EntityDamageByEntityEvent e) {
	// Entity victim = e.getEntity();
	// Entity damager = e.getDamager();
	//
	// if (!(victim instanceof Player) || !(damager instanceof Zombie))
	// return;
	//
	// Player p = (Player) victim;
	// Random r = new Random();
	//
	// int infectionChance = r.nextInt(100);
	//
	// // 5% chance of getting infected on hit
	// if (infectionChance < 5) {
	// PlayerObject mtPlayer =
	// main.getPlayerManager().getMortuusPlayer(p.getUniqueId());
	//
	// if (mtPlayer != null && !mtPlayer.isInfected() &&
	// mtPlayer.getInfectedState() == -1) {
	// p.sendMessage(MortuusTerraCore.NOTI_PREFIX + StringUtilities.color("&2I
	// feel strange..."));
	// mtPlayer.setInfected(true);
	// mtPlayer.setInfectedState(0);
	// mtPlayer.startInfection();
	//
	// }
	// }
	// }

	@EventHandler
	public void onPlayerEat(PlayerItemConsumeEvent e) {

	}

	// @SuppressWarnings("static-access")
	// @EventHandler
	// private void onPlayerDeathEvent(PlayerDeathEvent e) {
	// MobDisguise disguise = new MobDisguise(DisguiseType.ZOMBIE);
	// main.getDisguiseAPI().disguiseEntity(e.getEntity(), disguise);
	// return;
	// }
}
