package mortuusterra.objects.geck;

import org.bukkit.Location;

public class GeckObject {

	private Location GeckLocation;
	private Boolean ispowered = false;
	private boolean isCorrect = false;
	private Location geckLocation;

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

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Location getGeckLocation() {
		return geckLocation;
	}

	public void setGeckLocation(Location geckLocation) {
		this.geckLocation = geckLocation;
	}
}
