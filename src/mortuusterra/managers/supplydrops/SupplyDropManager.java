package mortuusterra.managers.supplydrops;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;
import mortuusterra.objects.supplydrop.SupplyDropObject;

public class SupplyDropManager {
	
	Main main = JavaPlugin.getPlugin(Main.class);
	
	private Location randomLocation;
	private int randomX;
	private int randomZ;
	private int Y;
	
	private int Xmax = 1000;
	private int Xmin = -1000;
	private int Zmax = 1000;
	private int Zmin = -1000;
	private World world = main.getServer().getWorld("world");

	private Map<Location, SupplyDropObject> supplyDropObjectMap = new HashMap<>();
	
	private ItemStack test;
	
	public void deliverSupplyDrop() {
		getRandomLocation(Xmin, Xmax, Zmin, Zmax, Y, world);
		makeSupplyDrop();
		addSupplyDropLocation(this.randomLocation);
		this.randomLocation.getBlock().setType(Material.CHEST);
		setChestInventory(this.randomLocation);
		main.getServer().getConsoleSender().sendMessage("Delivering Supply Drop at" + this.randomLocation.toString());
	}
	
	public void makeSupplyDrop() {
		this.randomLocation = getRandomLocation(Xmin, Xmax, Zmin, Zmax, Y, world);
	}
	private Location getRandomLocation(int Xmin, int Xmax, int Zmin, int Zmax, int Y, World world) {
		this.randomX = new Random().nextInt(((Xmax - Xmin) + 1) + Xmin);
		this.randomZ = new Random().nextInt(((Zmax - Zmin) + 1) + Zmin);
		this.Y = getHighestBlock(randomX, randomZ, world);
		Location randomLocation = new Location(world, this.randomX, this.Y, this.randomZ);
		return randomLocation;
	}
	private int getHighestBlock(int X, int Z, World world) {
		Location loc = new Location(world, X, 0, Z);
		return loc.getWorld().getHighestBlockYAt(X, Z);
	}
	private void setChestInventory(Location l) {
		Chest chest = (Chest) Bukkit.getWorld("world").getBlockAt(randomLocation).getState();
		chest.setCustomName("Supply Drop");
		test = new ItemStack(Material.ANVIL);
		chest.getInventory().addItem(test);
		chest.update();
	}
	
	public Map<Location, SupplyDropObject> getSupplyDropObjectMap(){
		return supplyDropObjectMap;
	}
	public SupplyDropObject getSupplyDropObject(Location supplyDropLocation) {
		return supplyDropObjectMap.get(supplyDropLocation);
	}
	public void addSupplyDropLocation(Location supplyDropLocation) {
		if(supplyDropObjectMap.containsKey(supplyDropLocation)) {
			return;
		}else {
			supplyDropObjectMap.put(supplyDropLocation, new SupplyDropObject(supplyDropLocation));
		}
	}
	public void removeSupplyDropLocation(Location supplyDropLocation) {
		if(supplyDropObjectMap.containsKey(supplyDropLocation)) {
			supplyDropObjectMap.remove(supplyDropLocation);
		}else {
			return;
		}
	}
	public boolean containSupplyDropLocation(Location supplyDropLocation) {
		return supplyDropObjectMap.containsKey(supplyDropLocation);
	}
}
