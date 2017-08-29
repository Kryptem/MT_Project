package mortuusterra.events.block;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class ElectricityBlockEvent implements Listener {
	Main main = JavaPlugin.getPlugin(Main.class);

	private Location generatorBlockLocation;
	private Location powerOutletBlockLocation;
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if (e.getItemInHand().getItemMeta().getDisplayName() == "Generator") {
		}
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		/**if() {
			
		}
		**/
	}

	public Location getGeneratorBlockLocation() {
		return generatorBlockLocation;
	}
	public Location getPowerOutletBlockLocation() {
		return powerOutletBlockLocation;
	}

}
