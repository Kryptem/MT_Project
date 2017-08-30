/* 
  We are not using this class (for now).
  
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

	Location newLoc;
	int x;
	int y;
	int z;


	// this is called by the radiationTimer
	public void deliverSupplyDrop() {
		makeSupplyDrop();
		addSupplyDropLocation(this.randomLocation);
		setChestInventory(this.randomLocation);
		brodcastMessage();
	}

	private void makeSupplyDrop() {
		this.randomLocation = getRandomLocation(1, 1, world);
		this.randomLocation.getBlock().setType(Material.CHEST);
	}

	private Location getRandomLocation(int Xoffset, int Zoffset, World world) {
		// this is the method that makes the random location close to a random player.
		Player ranPlayer = Bukkit.getOnlinePlayers().stream().findAny().get();
		Location loc = ranPlayer.getLocation();


		y = loc.getWorld().getHighestBlockYAt(loc);
		z = loc.getBlockX();
		x = loc.getBlockX();

		this.newLoc = new Location(world, (x + Xoffset), y, (z + Zoffset));

		return newLoc;
	}

	private void setChestInventory(Location l) {
		// this method will set the inventory of the SupplyDrop
		Chest chest = (Chest) Bukkit.getWorld("world").getBlockAt(newLoc).getState();
		chest.setCustomName("Supply Drop");

		test = new ItemStack(Material.ANVIL);

		chest.getInventory().addItem(test);
		chest.update();
	}

	private void brodcastMessage() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.RED + "Delivering Supply Drop at: " + ChatColor.BLUE + "X= " + ChatColor.YELLOW + x
					+ ChatColor.BLUE + " Z= " + ChatColor.YELLOW + z + ChatColor.BLUE + " Y= " + ChatColor.YELLOW + y);
		}
	}

	// hashmap getters and setters and other things to do with the hashmap.

	public Map<Location, SupplyDropObject> getSupplyDropObjectMap() {
		return supplyDropObjectMap;
	}

	public SupplyDropObject getSupplyDropObject(Location supplyDropLocation) {
		return supplyDropObjectMap.get(supplyDropLocation);
	}

	public void addSupplyDropLocation(Location supplyDropLocation) {
		if (supplyDropObjectMap.containsKey(supplyDropLocation)) {
			return;
		} else {
			supplyDropObjectMap.put(supplyDropLocation, new SupplyDropObject(supplyDropLocation));
		}
	}

	public void removeSupplyDropLocation(Location supplyDropLocation) {
		if (supplyDropObjectMap.containsKey(supplyDropLocation)) {
			supplyDropObjectMap.remove(supplyDropLocation);
		} else {
			return;
		}
	}

	public boolean containSupplyDropLocation(Location supplyDropLocation) {
		return supplyDropObjectMap.containsKey(supplyDropLocation);
	}
}*/
