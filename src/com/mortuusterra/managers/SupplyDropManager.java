/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.SupplyDropContent;
import com.mortuusterra.objects.SupplyDropObject;
import com.mortuusterra.utils.files.FileType;
import com.mortuusterra.utils.files.PluginFile;
import com.mortuusterra.utils.others.StringUtilities;

public class SupplyDropManager {

	private MortuusTerraCore main;
	private PluginFile file;

	private List<SupplyDropObject> supplyDrops;
	private List<SupplyDropContent> supplyContent;

	public SupplyDropManager(MortuusTerraCore main) {
		this.main = main;
		supplyDrops = new ArrayList<>();
		supplyContent = new ArrayList<>();
	}
	
	public void deliverSupplyDrop(World world) {
		Random r = new Random();
		
		// Supply drops between -500, + 500 
		double x = r.nextInt(1000) - 500;
		double y = 0.0D;
		double z = r.nextInt(1000) - 500;
		
		Location dropLocation = new Location(world, x, y, z);
		
		// Get the highest block at that location.
		dropLocation.setY(world.getHighestBlockYAt(dropLocation));
		
		dropLocation.getBlock().setType(Material.CHEST);
		Chest dropChest = (Chest) dropLocation.getBlock().getState();
		
		SupplyDropObject supplyDrop = new SupplyDropObject(dropLocation, dropChest, dropChest.getInventory());
		addSupplyDrop(supplyDrop);
		
		for (Player p : world.getPlayers()) {
			p.sendMessage(MortuusTerraCore.ALERT_PREFIX + StringUtilities.color("&eSupply Drop spotted at: &6" + x + ", " + dropLocation.getY() + ", " + z + "&e!"));
		}
	}

	public SupplyDropObject getSupplyDropByLocation(Location location) {
		for (SupplyDropObject sd : supplyDrops) {
			if (sd.getDropLocation().equals(location)) {
				return sd;
			}
		}
		return null;
	}
	
	public void addSupplyDrop(SupplyDropObject sd) {
		supplyDrops.add(sd);
	}
	
	public void removeSupplyDrop(Location location) {
		supplyDrops.remove(getSupplyDropByLocation(location));
	}

	public boolean isEmpty(Location location) {
		SupplyDropObject sd = getSupplyDropByLocation(location);
		if (sd == null)
		return true;
		
		for (int i = 0; i < sd.getDropInventory().getSize(); i++) {
			if (sd.getDropInventory().getContents()[i] != null)
				return false;
		}
		supplyDrops.remove(sd);
		
		return true;
	}
	
	public Inventory fillSupplyDropContent(Inventory inventory) {
		Random r = new Random();
		for (SupplyDropContent content : supplyContent) {
			if (r.nextInt(100) < content.getItemChance()) {
				
				int i = 0;
				int slot = r.nextInt(inventory.getSize());
				
				// Add the items to the inventory on random slots.
				while (i < content.getItemAmount()) {
					while (inventory.getItem(slot) != null)
						slot = r.nextInt(inventory.getSize());
					inventory.setItem(slot, new ItemStack(content.getItemMaterial(), 1));
					i++;
				}
			}
		}
		
		return inventory;
	}

	public void loadSupplyData() {
		file = new PluginFile("supplyDrops", FileType.YAML);
		YamlConfiguration config = file.returnYaml();

		// Supply content
		for (String key : config.getConfigurationSection("supply-drops.items").getKeys(false)) {

			Material itemMaterial = Material.valueOf(config.getString("supply-drops.items." + key + ".material").toUpperCase());
			int itemChance = config.getInt("supply-drops.items." + key + ".chance");
			int itemAmount = config.getInt("supply-drops.items." + key + ".amount");

			supplyContent.add(new SupplyDropContent(itemMaterial, itemChance, itemAmount));
		}

		// Supply drops
		for (String s : config.getStringList("supply-drops.objects")) {
			Location loc = StringUtilities.stringToLocation(s);
			
			loc.getBlock().setType(Material.CHEST);
			Chest chest = (Chest) loc.getBlock().getState();
			supplyDrops.add(new SupplyDropObject(loc, chest, chest.getInventory()));
		}
	}

	public void saveSupplyData() {
		YamlConfiguration config = file.returnYaml();
		
		// If no content for supply drops is set in config, a a default one to avoid a NPE.
		if (config.getConfigurationSection("supply-drops.items") == null) {
			String path = "supply-drops.items";
			config.set(path + ".material", "STONE");
			config.set(path + ".chance", 75);
			config.set(path + ".amount", 32);
		}

		List<String> locString = new ArrayList<>();
		for (SupplyDropObject sd : supplyDrops) {
			locString.add(StringUtilities.locationToString(sd.getDropLocation()));
		}

		config.set("supply-drops.objects", locString);
		file.save(config);
	}

	public List<SupplyDropObject> getSupplyDrops() {
		return supplyDrops;
	}

	public List<SupplyDropContent> getSupplyContent() {
		return supplyContent;
	}

}
