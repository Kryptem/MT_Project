package mortuusterra.events.block;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class CellTowerBlockEvent implements Listener {
	Main main = JavaPlugin.getPlugin(Main.class);

	private Location blockLocation;

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if (e.getItemInHand().getItemMeta().getDisplayName() == "CellTower") {
			this.blockLocation = e.getBlock().getLocation();
			main.getCellTowerManager().addCellTowerLocations(blockLocation);
		}
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		if(main.getCellTowerManager().containsCellTowerLocations(e.getBlock().getLocation())) {
			main.getCellTowerManager().removeCellTowerLocations(e.getBlock().getLocation());
		}
	}

	public Location getBlockLocation() {
		return blockLocation;
	}
}