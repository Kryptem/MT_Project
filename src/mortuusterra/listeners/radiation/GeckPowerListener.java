package mortuusterra.listeners.radiation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Lever;

public class GeckPowerListener implements Listener {

	private boolean incorrect = false;
	private boolean isPowered = false;
	private Location blockLocation;

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (e.getClickedBlock() != null) {
			Block block = e.getClickedBlock();

			if (block.getType() == Material.LEVER) {
				Lever lever = (Lever) block;

				if (block.getRelative(lever.getFacing().getOppositeFace()).getType() == Material.SPONGE) {
					setBlockLocation(block.getRelative(lever.getFacing().getOppositeFace()).getLocation());

					if (block.getRelative(lever.getFacing().getOppositeFace()).isBlockPowered()) {
						this.setPowered(true);

						for (BlockFace face : new BlockFace[] { BlockFace.NORTH, BlockFace.WEST, BlockFace.EAST,
								BlockFace.SOUTH }) {

							Block faced = e.getClickedBlock().getRelative(face);
							if (faced.getType() != Material.PISTON_BASE) {
								this.setIncorrect(true);
							} else {
								this.setIncorrect(false);
							}
						}
					}
				}
			}
		}
	}

	public boolean isIncorrect() {
		return incorrect;
	}

	public void setIncorrect(boolean incorrect) {
		this.incorrect = incorrect;
	}

	public Location getBlockLocation() {
		return blockLocation;
	}

	public void setBlockLocation(Location blockLocation) {
		this.blockLocation = blockLocation;
	}

	public boolean isPowered() {
		return isPowered;
	}

	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
	}
}
