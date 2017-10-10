package com.mortuusterra.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.PlayerObject;
import com.mortuusterra.utils.files.FileType;
import com.mortuusterra.utils.files.PluginFile;

public class PlayerManager {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);
	private PluginFile file;

	/*
	 * List of all PlayerObject of players who have joined. I figured we did not
	 * need a Map for players in radiation since we always damage the players.
	 * But if they are not in radiation they'll get damaged for 0 dmg
	 */
	private List<PlayerObject> mtPlayers = new ArrayList<>();
	private List<PlayerObject> infected = new ArrayList<>();

	public void addPlayer(Player p) {
		PlayerObject mtPlayer = new PlayerObject(p.getUniqueId());
		if (!mtPlayers.contains(mtPlayer))
			mtPlayers.add(mtPlayer);
	}

	public PlayerObject getPlayerObjectByUuid(UUID uuid) {
		for (PlayerObject p : mtPlayers) {
			if (p.getUuid().equals(uuid))
				return p;
		}
		return null;
	}

	public void removePlayer(Player p) {
		PlayerObject mtPlayer = getPlayerObjectByUuid(p.getUniqueId());

		if (mtPlayers.contains(mtPlayer))
			mtPlayers.remove(mtPlayer);
	}

	public List<PlayerObject> getMtPlayers() {
		return mtPlayers;
	}

	public List<PlayerObject> getInfected() {
		return infected;
	}

	public boolean containsPlayer(Player p) {
		return mtPlayers.contains(getPlayerObjectByUuid(p.getUniqueId()));
	}

	public void savePlayersToDisk() {
		YamlConfiguration config = file.returnYaml();

		for (PlayerObject p : mtPlayers) {
			String uuid = p.getUuid().toString();
			config.set(uuid + ".ingame-name", p.getPlayer().getName());
			config.set(uuid + ".in-geck-range", p.isPlayerInRangeOfGeck());
			config.set(uuid + ".infected", p.isInfected());
			config.set(uuid + ".infected-state", p.getInfectedState());

//			if (config.get(uuid + ".first-join-time") == null)
//				config.set(uuid + ".first-join-time", p.getJoinTime());
		}

		file.save(config);
	}

	public void loadPlayersFromDisk() {
		file = new PluginFile("players", FileType.YAML);
		YamlConfiguration config = file.returnYaml();

		mtPlayers.clear();
		for (String key : config.getConfigurationSection("").getKeys(false)) {
			PlayerObject p = new PlayerObject(UUID.fromString(key));
			p.setInfected(config.getBoolean(key + ".infected"));
			p.setInfectedState(config.getInt(key + ".infected-state"));
			p.setPlayerInRangeOfGeck(config.getBoolean(key + ".in-geck-range"));
			
			mtPlayers.add(p);
		}

	}
}
