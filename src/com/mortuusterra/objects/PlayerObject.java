package com.mortuusterra.objects;

import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.utils.others.StringUtilities;

public class PlayerObject {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	private UUID uuid;

	private boolean playerInRangeOfGeck;

	private boolean isInfected;
	private BukkitTask task;
	private int infectedState;
	private int timeInfected;
	// private long joinTime;

	private String currentIngameName;
	private Location playerLocation;

	public PlayerObject(UUID uniqueId) {
		this.timeInfected = 0;
		this.isInfected = false;
		this.infectedState = -1;
		this.uuid = uniqueId;
		this.playerInRangeOfGeck = false;
		this.isInfected = false;
		// this.joinTime = joinTime; 
		this.currentIngameName = Bukkit.getOfflinePlayer(uniqueId).getName();
	}

	/**
	 * Starts the infection of a player object.
	 * The player will have certain effects or messaged displayed
	 * based on the current InfectedState.
	 */
	public void startInfection() {
		Random r = new Random();
		Player p = main.getServer().getPlayer(uuid);

		this.task = new BukkitRunnable() {
			
			int amountEffectsAtOnce = 1;
			

			@Override
			public void run() {
				p.sendMessage("task");
				InfectedStates state = InfectedStates.getStateById(getInfectedState());
				int chance = r.nextInt(100);
				
				if (getInfectedState() == 0) {
					if (chance < 20) {
						p.addPotionEffect(new PotionEffect(state.getPossibleEffects().get(r.nextInt(state.getPossibleEffects().size())), 7 * 20, 1));
						p.sendMessage(StringUtilities.color("&2Something is not quite right..."));
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 2));
					//p.damage(1D);

				} else if (getInfectedState() == 1) {
					if (chance < 35) {
						amountEffectsAtOnce += r.nextInt(3);
						
						for (int i = 0; i < amountEffectsAtOnce; i++) 
							p.addPotionEffect(new PotionEffect(state.getPossibleEffects().get(r.nextInt(state.getPossibleEffects().size())), 5 * 20, 1));
						
						p.sendMessage(StringUtilities.color("&eSomething is &knot &equite right..."));
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 2));
					//p.damage(2D);
					
				} else if (getInfectedState() == 2) {
					if (chance < 40) {
						amountEffectsAtOnce += r.nextInt(4);
						
						for (int i = 0; i < amountEffectsAtOnce; i++) 
							p.addPotionEffect(new PotionEffect(state.getPossibleEffects().get(r.nextInt(state.getPossibleEffects().size())), 3 * 20, 1));
						
						p.sendMessage(StringUtilities.color("&5I &ksuddenly &5crave rotten &kflesh&5..."));
					}
					p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10 * 20, 2));
					//p.damage(3D);
				}
				
				timeInfected += 1;
			}

		}.runTaskTimer(main, 40L, 3 * 20L);
	}

	public UUID getUuid() {
		return uuid;
	}

	public int getInfectedState() {
		return infectedState;
	}
	
	public int getTimeInfected() {
		return timeInfected;
	}
	
	public void setTimeInfected(int timeInfected) {
		this.timeInfected = timeInfected;
	}
	
	public BukkitTask getTask() {
		return task;
	}

	public void setInfectedState(int infectedState) {
		this.infectedState = infectedState;
	}

	public String getCurrentIngameName() {
		return currentIngameName;
	}

	// public long getJoinTime() {
	// return joinTime;
	// }
	//
	// public void setJoinTime(long joinTime) {
	// this.joinTime = joinTime;
	// }

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected = isInfected;
	}

	public void setPlayerInRangeOfGeck(boolean bool) {
		this.playerInRangeOfGeck = bool;
	}

	public boolean isPlayerInRangeOfGeck() {
		return playerInRangeOfGeck;
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Location playerLocation) {
		this.playerLocation = playerLocation;
	}

}
