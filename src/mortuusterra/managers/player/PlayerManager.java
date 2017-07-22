package mortuusterra.managers.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import mortuusterra.objects.player.PlayerObject;

public class PlayerManager {

	private Map<String, PlayerObject> playerMap = new HashMap<>();

	public Map<String, PlayerObject> getPlayerMap() {
		return playerMap;
	}

	public PlayerObject getPlayer(String uuid) {
		return playerMap.get(uuid);
	}

	public void addPlayer(Player p) {
		if (containsPlayer(p.getUniqueId().toString())) {
			return;
		}
		playerMap.put(p.getUniqueId().toString(), new PlayerObject(p.getUniqueId()));
	}

	public void removePlayer(Player p) {
		if (!containsPlayer(p.getUniqueId().toString())) {
			return;
		}
		playerMap.remove(p.getUniqueId().toString());
	}

	public boolean containsPlayer(String uuid) {
		return playerMap.containsKey(uuid);
	}

}
