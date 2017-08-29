package mortuusterra.objects.electricity;

import org.bukkit.Location;

public class Generator {

	private Location genLoc;
	
	private boolean toggle;
	
	private int fuelAmount;
	
	public Generator(Location genLoc) {
		this.setGenLoc(genLoc);
	}

	public int getFuelAmount() {
		return fuelAmount;
	}

	public void setFuelAmount(int fuelAmount) {
		this.fuelAmount = fuelAmount;
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}

	public Location getGenLoc() {
		return genLoc;
	}

	public void setGenLoc(Location genLoc) {
		this.genLoc = genLoc;
	}
	
	
}
