package mortuusterra.objects.player;

import java.util.UUID;

public class PlayerObject {
	
	private UUID uuid;
	
	private boolean playerInBuilding = false;

	public PlayerObject(UUID uniqueId) {
		this.uuid = uniqueId;
	}

	public UUID getUuid() {
		return uuid;
	}
	
	public void isPlayerInBuilding(boolean bool) {
		this.playerInBuilding = bool;
	}
}
