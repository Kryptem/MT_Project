package mortuusterra.managers.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import mortuusterra.objects.player.PlayerObject;

public class PlayerManager {

	/*
	 * radsPlayerMap is for all players in Radiation
	 * GeckPlayerMap is for all players in range of a GECK
	 */
	
	private Map<String, PlayerObject> radsPlayerMap = new HashMap<>();
	private Map<String, PlayerObject> GeckPlayerMap = new HashMap<>();

	
	// radsPlayerMap
	
	public Map<String, PlayerObject> getPlayerMap() {
		return radsPlayerMap;
	}

	public PlayerObject getRadPlayer(String uuid) {
		return radsPlayerMap.get(uuid);
	}

	public void addRadPlayer(Player p) {
		if (containsRadPlayer(p.getUniqueId().toString())) {
			return;
		}
		radsPlayerMap.put(p.getUniqueId().toString(), new PlayerObject(p.getUniqueId()));
	}

	public void removeRadPlayer(Player p) {
		if (!containsRadPlayer(p.getUniqueId().toString())) {
			return;
		}
		radsPlayerMap.remove(p.getUniqueId().toString());
	}

	public boolean containsRadPlayer(String uuid) {
		return radsPlayerMap.containsKey(uuid);
	}
	
	// GeckPlayerMap
	
	public Map<String, PlayerObject> GeckPlayerMap() {
		return GeckPlayerMap;
	}

	public PlayerObject getGeckPlayer(String uuid) {
		return GeckPlayerMap.get(uuid);
	}

	public void addGeckPlayer(Player p) {
		if (containsGeckPlayer(p.getUniqueId().toString())) {
			return;
		}
		GeckPlayerMap.put(p.getUniqueId().toString(), new PlayerObject(p.getUniqueId()));
	}

	public void removeGeckPlayer(Player p) {
		if (!containsGeckPlayer(p.getUniqueId().toString())) {
			return;
		}
		GeckPlayerMap.remove(p.getUniqueId().toString());
	}

	public boolean containsGeckPlayer(String uuid) {
		return GeckPlayerMap.containsKey(uuid);
	}

}
