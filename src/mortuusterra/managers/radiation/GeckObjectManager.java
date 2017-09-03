package mortuusterra.managers.radiation;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

import mortuusterra.objects.geck.GeckObject;

public class GeckObjectManager {

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
}

