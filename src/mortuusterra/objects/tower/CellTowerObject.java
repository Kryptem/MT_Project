package mortuusterra.objects.tower;

import org.bukkit.Location;

public class CellTowerObject {
	
	private Location towerLocation;
	
	
	public CellTowerObject(Location towerLocation) {
		this.towerLocation = towerLocation;
	}
	
	public Location getTowerLocation() {
		return this.towerLocation;
	}

}
