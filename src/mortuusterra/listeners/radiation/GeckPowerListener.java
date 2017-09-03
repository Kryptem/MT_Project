package mortuusterra.listeners.radiation;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class GeckPowerListener implements Listener {
	Main main = JavaPlugin.getPlugin(Main.class);
	
	private Location blockLocation;

	// Every time a player interacts with a block
    // Added disabled message. 9/3/17
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

					e.getPlayer().sendMessage(ChatColor.RED + "You must build the GECK correctly!");
				} else {
					this.blockLocation = sponge.getLocation();

					main.getGeckObjectManager().addGeckLocation(blockLocation);
					main.getGeckObjectManager().getGeckObject(blockLocation).setCorrect(true);
					main.getGeckObjectManager().getGeckObject(blockLocation).setIspowered(true);
					
					main.getGeckObjectManager().getGeckObject(blockLocation).setGeckLocation(blockLocation);
					
					e.getPlayer().sendMessage(ChatColor.GREEN + "GECK Enabled!");
				}
			} else if (sponge.getType().equals(Material.SPONGE) && (sponge.isBlockPowered() && main.getGeckObjectManager().getGeckObject(blockLocation) != null)) {
				main.getGeckObjectManager().getGeckObject(blockLocation).setIspowered(false);
				e.getPlayer().sendMessage(ChatColor.RED + "GECK Disabled!");
			}
		}
	}
	
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent event){
		Player player = event.getPlayer();
		Block broken = event.getBlock();
		
		
		if (broken.getType() == Material.LEVER){
			
			if (main.getGeckObjectManager().getGeckObject(broken.getRelative(0, -1, 0).getLocation()) != null){
				main.getGeckObjectManager().removeGeckLocation(broken.getRelative(0, -1, 0).getLocation());
				player.sendMessage(ChatColor.RED + "GECK disabled.");
			}
			
		}else if (broken.getType() == Material.SPONGE && main.getGeckObjectManager().getGeckObject(broken.getLocation()) != null){
			main.getGeckObjectManager().removeGeckLocation(broken.getLocation());
			player.sendMessage(ChatColor.RED + "GECK disabled.");
		}else if (broken.getType() == Material.PISTON_BASE){
			
			List<BlockFace> sides = Arrays.asList(BlockFace.EAST, BlockFace.NORTH, BlockFace.WEST, BlockFace.SOUTH);
			sides.forEach(side -> {
				Location bloc = broken.getRelative(side).getLocation();
				
				if (main.getGeckObjectManager().getGeckObject(bloc) != null){
					main.getGeckObjectManager().removeGeckLocation(bloc);
					player.sendMessage(ChatColor.RED + "GECK disabled.");
				}
				
			});
			
			
		}
		
	}
}
