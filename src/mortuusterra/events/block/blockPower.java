package mortuusterra.events.block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class blockPower implements Listener{

	@EventHandler
	private void onBlockPowerEvent(BlockRedstoneEvent e) {
		if (e.getBlock().isBlockPowered() == true) {
		}
	}
	
}
