package mortuusterra.managers.supplydrops;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import mortuusterra.Main;
import mortuusterra.objects.supplydrop.SupplyDropObject;
import net.md_5.bungee.api.ChatColor;

public class SupplyDropManager {
	
	Main main = JavaPlugin.getPlugin(Main.class);
	
	private Location randomLocation;
	private World world = main.getServer().getWorld("world");

	private Map<Location, SupplyDropObject> supplyDropObjectMap = new HashMap<>();
	
	private ItemStack test;
	
	//this is called by the radiationTimer
	public void deliverSupplyDrop() {
		makeSupplyDrop();
		addSupplyDropLocation(this.randomLocation);
		this.randomLocation.getBlock().setType(Material.CHEST);
		setChestInventory(this.randomLocation);
		main.getServer().broadcastMessage(ChatColor.RED + "Delivering Supply Drop at: " + ChatColor.BLUE + "X= " + ChatColor.YELLOW + this.randomLocation.getX() +  ChatColor.BLUE + " Z= " + ChatColor.YELLOW + this.randomLocation.getBlockZ() +  ChatColor.BLUE + " Y= " + ChatColor.YELLOW + this.randomLocation.getY());
	}
	
	private void makeSupplyDrop() {
		//this will make a random location for the supply Drop to spawn on. 
		this.randomLocation = getRandomLocation(1, 1, world);
	}
	private Location getRandomLocation(int Xoffset, int Zoffset, World world) {
		//this is the method that makes the random location close to a random player.
		Player ranPlayer = Bukkit.getOnlinePlayers().stream().findAny().get();
		Location loc = ranPlayer.getLocation();
		Location newLoc = new Location(world, (loc.getX() + Xoffset), loc.getY(), (loc.getZ() + Zoffset));
				
		/** this is old code, needs to be removed after testing new code
		 * 
		this.randomX = new Random().nextInt(((Xmax - Xmin) + 1) + Xmin);
		this.randomZ = new Random().nextInt(((Zmax - Zmin) + 1) + Zmin);
		this.Y = getHighestBlock(randomX, randomZ, world);
		Location randomLocation = new Location(world, this.randomX, this.Y, this.randomZ);
		**/
		return newLoc;
	}
	private void setChestInventory(Location l) {
		//this method will set the inventory of the SupplyDrop
		Chest chest = (Chest) Bukkit.getWorld("world").getBlockAt(l).getState();
		chest.setCustomName("Supply Drop");
		
		test = new ItemStack(Material.ANVIL);
		
		chest.getInventory().addItem(test);
		chest.update();
	}
	
	//hashmap getters and setters and other things to do with the hashmap. 
	
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
