package com.mortuusterra.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import com.mortuusterra.objects.GeckObject;

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

	/**
	 * Checks if the geck structure is correct.
	 * 
	 * @param center
	 *            The center block.
	 * @return true if the build is correct, false otherwise.
	 */
	public boolean isGeckBuildCorrect(Block center) {

		BlockFace[] faces = { BlockFace.EAST, BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH };
		for (BlockFace f : faces) {
			if (center.getRelative(f).getType() != Material.PISTON_BASE)
				return false;
		}
		return true;
	}
}
