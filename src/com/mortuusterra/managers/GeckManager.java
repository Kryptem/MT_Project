package com.mortuusterra.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.GeckObject;
import com.mortuusterra.objects.PlayerObject;
import com.mortuusterra.utils.files.FileType;
import com.mortuusterra.utils.files.PluginFile;
import com.mortuusterra.utils.others.StringUtilities;

public class GeckManager {

	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);
	private PluginFile file;
	
	private List<Location> gecks = new ArrayList<>();
	

	/**
	 * @param player The player in question
	 * @return <CODE>True </CODE> If the player is inside geck range and geck is
	 *         powered and built correctly.
	 *         <p>
	 *         <CODE>False </CODE> If the player is not in range. Also if the
	 *         player is in range but geck is not powered or incorrect.
	 */
	public boolean isPlayerInGeckRange(Player player) {
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			Location geckLocation = geckObject.getGeckLocation();
			Location playerLocation = player.getLocation();
			if (!geckLocation.getWorld().equals(playerLocation.getWorld()))
				continue;

			double distance = geckLocation.distanceSquared(playerLocation);

			// if the distance is less than or = to x and the GECK is correct
			// and powered then you are in range of the geck
			// this is the squared range of blocks. the block range is 15
			// blocks.
			int x = 225;
			UUID uuid = player.getUniqueId();

			// If the player is not inside the radiationMap
			if (!main.getPlayerManager().containsPlayer(player))
				return false;

			PlayerObject mortuusPlayer = main.getPlayerManager().getPlayerObjectByUuid(uuid);

			if (distance <= x) {
				if (geckObject.isCorrect() && geckObject.isPowered()) {
					// Player inside radius and geck is powered + correct
					mortuusPlayer.setPlayerInRangeOfGeck(true);
					return true;
				} else {
					// Player is inside but either geck not powered or not
					// correct or both
					mortuusPlayer.setPlayerInRangeOfGeck(false);
					return false;
				}
			}
		}
		return false;
	}
	
	public List<Location> getGecks() {
		return gecks;
	}
	
	public void saveGecksToDisk() {
		YamlConfiguration config = file.returnYaml();
		List<String> toSave = new ArrayList<>();
		for (Location loc : gecks) {
			toSave.add(StringUtilities.locationToString(loc));
		}
		
		config.set("gecks", toSave);
		file.save(config);
	}
	
	public void loadGecksFromDisk() {
		file = new PluginFile("gecks", FileType.YAML);
		YamlConfiguration config = file.returnYaml();
		
		gecks.clear();
		
		for (String key : config.getStringList("gecks")) {
			gecks.add(StringUtilities.stringToLocation(key));
		}
	}
	
	
}
