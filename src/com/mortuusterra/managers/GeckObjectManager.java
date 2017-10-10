package com.mortuusterra.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import com.mortuusterra.objects.GeckObject;
import com.mortuusterra.utils.files.FileType;
import com.mortuusterra.utils.files.PluginFile;
import com.mortuusterra.utils.others.StringUtilities;

public class GeckObjectManager {

	private PluginFile file;

	private Map<Location, GeckObject> gecklocationMap = new HashMap<>();

	public Map<Location, GeckObject> getGecklocationMap() {
		return gecklocationMap;
	}

	public GeckObject getGeckObject(Location geckLocation) {
		return gecklocationMap.get(geckLocation);
	}

	public void addGeckLocation(Location geckLocation) {
		if (containsGeckLocation(geckLocation)) {
			return;
		}
		gecklocationMap.put(geckLocation, new GeckObject(geckLocation));
	}

	public void removeGeckLocation(Location geckLocation) {
		if (!containsGeckLocation(geckLocation)) {
			return;
		}
		gecklocationMap.remove(geckLocation);
	}

	public boolean containsGeckLocation(Location geckLocation) {
		return gecklocationMap.containsKey(geckLocation);
	}

	public void saveGecksToDisk() {
		YamlConfiguration config = file.returnYaml();
		
		List<String> toSave = new ArrayList<>();
		for (GeckObject geck : gecklocationMap.values()) {
			toSave.add(StringUtilities.locationToString(geck.getGeckLocation()));
		}

		config.set("gecks", toSave);
		gecklocationMap.clear();
		file.save(config);
	}

	public void loadGecksFromDisk() {
		file = new PluginFile("gecks", FileType.YAML);
		YamlConfiguration config = file.returnYaml();

		for (String locaString : config.getStringList("gecks")) {
			Location loc = StringUtilities.stringToLocation(locaString);
			GeckObject geck = new GeckObject(loc);
			geck.setCorrect(true);
			geck.setPowered(true);
			
			gecklocationMap.put(loc, geck);
		}
	}
}
