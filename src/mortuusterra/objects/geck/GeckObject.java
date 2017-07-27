package mortuusterra.objects.geck;

import org.bukkit.Location;

public class GeckObject {

	private Location GeckLocation;
	private Boolean ispowered = false;

	public GeckObject(Location GeckLocation) {
		this.GeckLocation = GeckLocation;
	}

	public Location getTowerLocation() {
		return this.GeckLocation;
	}

	public Boolean getIspowered() {
		return ispowered;
	}

	public void setIspowered(Boolean ispowered) {
		this.ispowered = ispowered;
	}
	
}
