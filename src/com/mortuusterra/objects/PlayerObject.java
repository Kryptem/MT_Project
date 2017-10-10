package com.mortuusterra.objects;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PlayerObject {

	private UUID uuid;

	// private boolean playerInBuilding = false;
	private boolean playerInRangeOfGeck;
	private boolean isInfected;

	// Maybe track the first join for rewards, etc. later on depending on how
	// long the player has been playing
	// private Calendar firstJoin; ^
	private int infectedState;
//	private long joinTime;

	private String currentIngameName;
	private Location playerLocation;

	public PlayerObject(UUID uniqueId) {
		this.isInfected = false;
		this.infectedState = -1;
		this.uuid = uniqueId;
		this.playerInRangeOfGeck = false;
		this.isInfected = false;
//		this.joinTime = joinTime;
		this.currentIngameName = Bukkit.getOfflinePlayer(uniqueId).getName();
	}

	public UUID getUuid() {
		return uuid;
	}

	// Didn't see any meaningful usage, feel free to uncomment if you wish to
	// use.
	/*
	 * public void setPlayerInBuilding(boolean bool) { this.playerInBuilding =
	 * bool; }
	 */

	public int getInfectedState() {
		return infectedState;
	}
	
	public void setInfectedState(int infectedState) {
		this.infectedState = infectedState;
	}
	
	public String getCurrentIngameName() {
		return currentIngameName;
	}

//	public long getJoinTime() {
//		return joinTime;
//	}
//
//	public void setJoinTime(long joinTime) {
//		this.joinTime = joinTime;
//	}

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
