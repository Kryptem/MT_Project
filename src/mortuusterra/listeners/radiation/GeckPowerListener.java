package mortuusterra.listeners.radiation;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class GeckPowerListener implements Listener {
	Main main = JavaPlugin.getPlugin(Main.class);

	private boolean correct = false;
	private boolean isPowered = false;
	private Location blockLocation;

	// Every time a player interacts with a block
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		// if the block is not null then get the block location
		if (e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.LEVER)) {

			Block lever = e.getClickedBlock();
			Block sponge = lever.getRelative(BlockFace.DOWN);

			if (sponge.getType().equals(Material.SPONGE) && (!sponge.isBlockPowered())) {

				Block piston1 = sponge.getRelative(BlockFace.EAST);
				Block piston2 = sponge.getRelative(BlockFace.WEST);
				Block piston3 = sponge.getRelative(BlockFace.NORTH);
				Block piston4 = sponge.getRelative(BlockFace.SOUTH);

				if (!(piston1.getType().equals(Material.PISTON_BASE) && piston2.getType().equals(Material.PISTON_BASE)
						&& piston3.getType().equals(Material.PISTON_BASE)
						&& piston4.getType().equals(Material.PISTON_BASE))) {

					e.getPlayer().sendMessage(ChatColor.RED + "You must build the GECK corectly!");
					return;
				} else {
					this.blockLocation = sponge.getLocation();

					main.getGeckObjectManager().addGeckLocation(blockLocation);
					main.getGeckObjectManager().getGeckObject(blockLocation).setCorrect(true);
					main.getGeckObjectManager().getGeckObject(blockLocation).setGeckLocation(blockLocation);
					return;
				}
			} else if (sponge.getType().equals(Material.SPONGE) && (sponge.isBlockPowered())) {

				main.getGeckObjectManager().getGeckObject(blockLocation).setIspowered(false);
				main.getPlayerManager().getRadPlayer(e.getPlayer().getUniqueId().toString()).setPlayerInRangeOfGeck(false);
				main.getGeckObjectManager().removeGeckLocation(blockLocation);
				main.getPlayerManager().removeGeckPlayer(e.getPlayer());
			}
		}
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setIncorrect(boolean correct) {
		this.correct = correct;
	}

	public Location getBlockLocation() {
		return blockLocation;
	}

	public void setBlockLocation(Location blockLocation) {
		this.blockLocation = blockLocation;
		return;
	}

	public boolean isPowered() {
		return isPowered;
	}

	public void setPowered(boolean isPowered) {
		this.isPowered = isPowered;
		return;
	}
}
