package com.mortuusterra.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.FalloutShelter;
import com.mortuusterra.objects.PKStates;
import com.mortuusterra.objects.PlayerObject;

public class PlayerListener implements Listener {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	// Disguise disguise = DisguiseAPI.constructDisguise(zombie);

	@EventHandler
	private void onPlayerJoinEvent(PlayerJoinEvent e) {
		main.getPlayerManager().addMortuusPlayer(e.getPlayer());
		
		if (!main.getScoreboards().isHostile(e.getPlayer()))
			main.getScoreboards().addPlayer(e.getPlayer(), "Neutral");
	}

	@EventHandler
	public void onPlayerKillsPlayer(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player) || !(e.getEntity() instanceof Player))
			return;
		
		Player killer = (Player) e.getDamager();
		Player target = (Player) e.getEntity();
		
		// Check if the target is dead
		if (target.getHealth() - e.getDamage() <= 0) {
			PlayerObject killerObject = main.getPlayerManager().getMortuusPlayer(killer.getUniqueId());
			
			// Sets the time in SECONDS.
			killerObject.setLastPlayerKillTime(System.currentTimeMillis() / 1000);
			killerObject.addPlayerKills(1);
			
			for (PKStates state : PKStates.values()) {
				if (killerObject.getPlayerKills() == state.getRequiredKills()) {
					killerObject.setPkState(state);
					main.getScoreboards().switchTeam(killer, "Orange");
					break;
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		PlayerObject pObject = main.getPlayerManager().getMortuusPlayer(p.getUniqueId());
		
		// if 25 minutes have passed since the last kill set to neutral.
		if (pObject.getLastPlayerKillTime() + 1500  > (System.currentTimeMillis() / 1000)) {
			main.getScoreboards().switchTeam(p, "Neutral");
			pObject.setPkState(PKStates.NEUTRAL);
			pObject.setPlayerKills(0);
		}
	}

	@EventHandler
	public void test(PlayerInteractEvent e) {
		if (e.getHand() == EquipmentSlot.OFF_HAND)
			return;
		
		if (e.getAction() == Action.LEFT_CLICK_BLOCK)
			main.getPlayerManager().getMortuusPlayer(e.getPlayer().getUniqueId()).setPkState(PKStates.ORANGE);;

		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (e.getItem() == null)
				return;

			if (e.getItem().getType() != Material.DIAMOND_HOE)
				return;
			FalloutShelter s = new FalloutShelter(e.getClickedBlock().getLocation().clone());
			s.generateFalloutShelter();
		}
	}
	
	
	
	

}