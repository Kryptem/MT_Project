package mortuusterra.objects.supplydrop;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class SupplyDropObject {
	public SupplyDropObject(Location supplyDropLocation) {
		this.supplyDropLocation = supplyDropLocation;
	}
	
	private Location supplyDropLocation;
	
	private List<ItemStack> supplyDropLoot;
	
	private boolean isLooted = false;
	
	private String name;

	public Location getSupplyDropLocation() {
		return supplyDropLocation;
	}

	public void setSupplyDropLocation(Location supplyDropLocation) {
		this.supplyDropLocation = supplyDropLocation;
	}

	public List<ItemStack> getSupplyDropLoot() {
		return supplyDropLoot;
	}

	public void setSupplyDropLoot(List<ItemStack> supplyDropLoot) {
		this.supplyDropLoot = supplyDropLoot;
	}

	public boolean isLooted() {
		return isLooted;
	}

	public void setLooted(boolean isLooted) {
		this.isLooted = isLooted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
