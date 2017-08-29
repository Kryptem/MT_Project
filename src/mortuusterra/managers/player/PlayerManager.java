package mortuusterra.managers.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import mortuusterra.objects.player.PlayerObject;

public class PlayerManager {

	private Map<String, PlayerObject> radsPlayerMap = new HashMap<>();
	/*
	 * radsPlayerMap is for all players in Radiation
	 */

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
}
