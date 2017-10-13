package com.mortuusterra.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

import com.mortuusterra.objects.CellTowerObject;

public class CellTowerManager {

	private Map<Location, CellTowerObject> cellTowerLocationsMap = new HashMap<>();

	public Map<Location, CellTowerObject> getCellTowerLocationsMap() {
		return cellTowerLocationsMap;
	}

	public CellTowerObject getCellTowerMapObject(Location towerLocation) {
		return cellTowerLocationsMap.get(towerLocation);
	}

	public void addCellTowerLocations(Location towerLocation) {
		if (containsCellTowerLocations(towerLocation)) {
			return;
		}
		cellTowerLocationsMap.put(towerLocation, new CellTowerObject(towerLocation));
	}

	public void removeCellTowerLocations(Location towerLocation) {
		if (!containsCellTowerLocations(towerLocation)) {
			return;
		}
		cellTowerLocationsMap.remove(towerLocation);
	}

	public boolean containsCellTowerLocations(Location towerLocation) {
		return cellTowerLocationsMap.containsKey(towerLocation);
	}

}
