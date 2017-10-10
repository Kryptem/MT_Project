package com.mortuusterra.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.mortuusterra.objects.PlayerObject;
import com.mortuusterra.utils.files.FileType;
import com.mortuusterra.utils.files.PluginFile;

public class PlayerManager {

	private PluginFile file;

	/*
	 * Map of all PlayerObject of players who are online. I figured we did not
	 * need a Map for players in radiation since we always damage the players.
	 * But if they are not in radiation they'll get damaged for 0 dmg
	 */
	private Map<UUID, PlayerObject> mtPlayers = new HashMap<>();
	private List<PlayerObject> infectedPlayers = new ArrayList<>();

	/**
	 * Adds an MT-Player -if not already present - to the Map.
	 * 
	 * @param p
	 *            The Player to be added.
	 */
	public void addMortuusPlayer(Player p) {
		if (!mtPlayers.containsKey(p.getUniqueId()))
			mtPlayers.put(p.getUniqueId(), new PlayerObject(p.getUniqueId()));
	}

	public void removeMortuusPlayer(Player p) {
		if (mtPlayers.containsKey(p.getUniqueId()))
			mtPlayers.remove(p.getUniqueId());
	}

	public PlayerObject getMortuusPlayer(UUID uuid) {
		if (mtPlayers.containsKey(uuid))
			return mtPlayers.get(uuid);
		return null;
	}

	public boolean containsMortuusPlayer(UUID uuid) {
		if (mtPlayers.containsKey(uuid))
			return true;
		return false;
	}

	public Map<UUID, PlayerObject> getMtPlayers() {
		return mtPlayers;
	}

	public List<PlayerObject> getInfected() {
		return infectedPlayers;
	}

	public void savePlayersToDisk() {
		YamlConfiguration config = file.returnYaml();
		if (mtPlayers.isEmpty())
			return;

		for (PlayerObject p : mtPlayers.values()) {
			String uuid = p.getUuid().toString();
			config.set(uuid + ".ingame-name", p.getCurrentIngameName());
			config.set(uuid + ".in-geck-range", p.isPlayerInRangeOfGeck());
			config.set(uuid + ".infected", p.isInfected());
			config.set(uuid + ".infected-state", p.getInfectedState());

			// if (config.get(uuid + ".first-join-time") == null)
			// config.set(uuid + ".first-join-time", p.getJoinTime());
		}
		mtPlayers.clear();
		file.save(config);
	}

	public void loadPlayersFromDisk() {
		file = new PluginFile("players", FileType.YAML);
		YamlConfiguration config = file.returnYaml();

		for (String key : config.getConfigurationSection("").getKeys(false)) {

			// Only convert online players to PlayerObject and add to Map.
			for (Player online : Bukkit.getOnlinePlayers()) {
				UUID uuid = UUID.fromString(key);

				if (!online.getUniqueId().equals(uuid))
					continue;
				
				PlayerObject p = new PlayerObject(uuid);

				boolean infected = config.getBoolean(key + ".infected");
				boolean inGeckRange = config.getBoolean(key + ".in-geck-range");
				int infectedState = config.getInt(key + ".infected-state");

				p.setInfected(infected);
				p.setInfectedState(infectedState);
				p.setPlayerInRangeOfGeck(inGeckRange);

				mtPlayers.put(uuid, p);
			}
		}

	}
}
