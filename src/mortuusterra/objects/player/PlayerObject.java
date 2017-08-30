package mortuusterra.objects.player;

import java.util.UUID;

import org.bukkit.Location;

public class PlayerObject {
	
	private UUID uuid;
	
	//private boolean playerInBuilding = false;
	private boolean PlayerInRangeOfGeck = false;
	private Location playerLocation;

	public PlayerObject(UUID uniqueId) {
		this.uuid = uniqueId;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	//Didn't see any meaningful usage, feel free to uncomment if you wish to use. 
	/*public void setPlayerInBuilding(boolean bool) {
		this.playerInBuilding = bool;
	}
	*/
	public void setPlayerInRangeOfGeck(boolean bool) {
		this.PlayerInRangeOfGeck = bool;
	}
	public boolean getplayerInRangeOfGeck() {
		return PlayerInRangeOfGeck;
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public void setPlayerLocation(Location playerLocation) {
		this.playerLocation = playerLocation;
	}
	
}
