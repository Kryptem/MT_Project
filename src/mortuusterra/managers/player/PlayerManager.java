package mortuusterra.managers.player;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import mortuusterra.objects.player.PlayerObject;

public class PlayerManager {

	private Map<String, PlayerObject> radsPlayerMap = new HashMap<>();
	private Map<String, PlayerObject> chatPlayermap = new HashMap<>();

	public Map<String, PlayerObject> getPlayerMap() {
		return radsPlayerMap;
	}

	public PlayerObject getRadPlayer(String uuid) {
		return radsPlayerMap.get(uuid);
	}

	public void addRadPlayer(Player p) {
		if (containsChatPlayer(p.getUniqueId().toString())) {
			return;
		}
		radsPlayerMap.put(p.getUniqueId().toString(), new PlayerObject(p.getUniqueId()));
	}

	public void removeRadPlayer(Player p) {
		if (!containsChatPlayer(p.getUniqueId().toString())) {
			return;
		}
		radsPlayerMap.remove(p.getUniqueId().toString());
	}

	public boolean containsRadPlayer(String uuid) {
		return radsPlayerMap.containsKey(uuid);
	}
	
	
	
	
	
	
	public Map<String, PlayerObject> chatPlayermap() {
		return chatPlayermap;
	}

	public PlayerObject getChatPlayer(String uuid) {
		return chatPlayermap.get(uuid);
	}

	public void addChatPlayer(Player p) {
		if (containsChatPlayer(p.getUniqueId().toString())) {
			return;
		}
		chatPlayermap.put(p.getUniqueId().toString(), new PlayerObject(p.getUniqueId()));
	}

	public void removeChatPlayer(Player p) {
		if (!containsChatPlayer(p.getUniqueId().toString())) {
			return;
		}
		chatPlayermap.remove(p.getUniqueId().toString());
	}

	public boolean containsChatPlayer(String uuid) {
		return chatPlayermap.containsKey(uuid);
	}

}
