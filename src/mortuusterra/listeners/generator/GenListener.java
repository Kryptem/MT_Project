package mortuusterra.listeners.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Lever;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.MortuusTerraCore;
import mortuusterra.managers.crafting.RecipeManager;
import mortuusterra.utils.files.FileType;
import mortuusterra.utils.files.PluginFile;

/**
 * Listens and manages generators. 9/22/17
 * @author Horsey
 *
 */
public class GenListener implements Listener {

	// Variables :D
    private MortuusTerraCore main = MortuusTerraCore.getPlugin(MortuusTerraCore.class);
    private PluginFile file;
    
    // A special hierachial map, in the order of World -> Chunk -> Location to make it as fast as possible
    private Map<String, ManyMap<String, Location>> powerable = new HashMap<>();
    private Map<String, ManyMap<String, Location>> generators = new HashMap<>();
    private List<Location> inUse = new ArrayList<>();
    
    
    // Called when a player tries to interact with a generator while it's turning on/off
    @EventHandler
    public void onInteractBusy(PlayerInteractEvent event) {
    	if (event.getAction() == Action.PHYSICAL) System.out.println("called");
    	if (event.getClickedBlock() == null) return;
    	if (!inUse.contains(event.getClickedBlock().getLocation())) return;
    	
    	event.setCancelled(true);
    	
    	if (event.getHand() == EquipmentSlot.HAND) 
    		event.getPlayer().sendMessage(ChatColor.RED + "That generator is busy right now!");
    }
    
    // Called when a player tries to break a generator while it's turning on/off
    @EventHandler
    public void onBreakBusy(BlockBreakEvent event) {
    	if (!inUse.contains(event.getBlock().getLocation())) return;
    	
    	event.setCancelled(true);
    	
    	event.getPlayer().sendMessage(ChatColor.RED + "That generator is busy right now!");
    }
    

    // Initailize file; lists from config.
    public GenListener(){
        file =  new PluginFile("powerable", FileType.YAML);
        
        for (World w : Bukkit.getWorlds()) {
        	powerable.put(w.getName(), new ManyMap<>());
        	generators.put(w.getName(), new ManyMap<>());
        }
        
        YamlConfiguration config = file.returnYaml();
        

        for (Object obj : config.getList("gens") != null ? config.getList("gens") : Collections.emptyList()){
        	Location loc = (Location)obj;
        	ManyMap<String, Location> mm = generators.get(loc.getWorld().getName());
            mm.addValue(loc.getChunk().getX() + ";" + loc.getChunk().getZ(), loc);
            generators.put(loc.getWorld().getName(), mm);
            
            for (Block bloc : getNearbyBlocks(loc.getBlock(), 15)) {
				Location ploc = bloc.getLocation();
				ManyMap<String, Location> pmm = powerable.get(ploc.getWorld().getName());
				pmm.addValue(ploc.getChunk().getX() + ";" + ploc.getChunk().getZ(), ploc);
				powerable.put(ploc.getWorld().getName(), pmm);
			}
        }
        
    }

    // Called when the server is shutting down to save the file
    public void saveFile(){
        YamlConfiguration config = file.returnYaml();
        List<Location> toSave = new ArrayList<>();
        for (ManyMap<String, Location> mm : generators.values()){
        	for (List<Location> vals : mm.values()){
        		toSave.addAll(vals);
        	}
        }
        config.set("gens", toSave);
        file.save(config);
    }

    // Called when a player places a generator
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
    	
        String chunk = event.getBlock().getLocation().getChunk().getX() + ";" + event.getBlock().getLocation().getChunk().getZ();

        if (event.getItemInHand().hasItemMeta() && event.getItemInHand().getItemMeta().hasDisplayName()){

            if (!event.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(RecipeManager.GENERATOR_NAME)) return;
            // A generator was placed

            inUse.add(event.getBlock().getLocation());
            
            event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.GREEN + " Powering up generator...");

            // Run everything async; creates a dramatic delay + saves performance
            new BukkitRunnable(){
                @Override
                public void run() {
                    List<Block> blocks = getNearbyBlocks(event.getBlock(), 15); 

                    for (Block block : blocks){
                        String chunk = block.getLocation().getChunk().getX() + ";" + block.getLocation().getChunk().getZ();
                    	ManyMap<String, Location> mm = powerable.get(block.getLocation().getWorld().getName());
                        mm.addValue(chunk, block.getLocation());
                        powerable.put(block.getLocation().getWorld().getName(), mm);
                    }

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.GREEN + " Generator Ready!");
                            inUse.remove(event.getBlock().getLocation());
                        }
                    }.runTask(main);

                }
            }.runTaskLaterAsynchronously(main, 10L); // It's a little too fast.
            
            Location loc = event.getBlock().getLocation();
            // add the generator
        	ManyMap<String, Location> mm = generators.get(loc.getWorld().getName());
            mm.addValue(loc.getChunk().getX() + ";" + loc.getChunk().getZ(), loc);
            generators.put(loc.getWorld().getName(), mm);
            // Disable torches
        }else if (event.getItemInHand().getType() == Material.REDSTONE_TORCH_ON &&
                !generators.get(event.getBlock().getWorld().getName()).getList(chunk).contains(event.getBlock().getLocation())){
            event.getBlock().setType(Material.REDSTONE_TORCH_OFF);
        }
    }

    // Called when a player breaks a generator
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onBreak(BlockBreakEvent event){
        String chunk = event.getBlock().getLocation().getChunk().getX() + ";" + event.getBlock().getLocation().getChunk().getZ();
        
        if (!generators.get(event.getBlock().getWorld().getName()).getList(chunk).contains(event.getBlock().getLocation())) return;

        event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.RED + " Un-powering generator");
        
        inUse.add(event.getBlock().getLocation());
        event.setCancelled(true);
        
		
        
        // Split the ~28000 blocks into 300 blocks to scan every tick; sounds like a lot, but it's fast enough. 
        int i = 0;
        List<List<Block>> sublists = subList(getNearbyBlocks(event.getBlock(), 15), 300);
        for (List<Block> blocks : sublists) {
        	new BukkitRunnable() {

        		@Override
				public void run() {
        			for (Block block : blocks){
        				// Unregister the block
                        ManyMap<String, Location> mm = powerable.get(block.getWorld().getName());
                        String chunks = block.getLocation().getChunk().getX() + ";" + block.getLocation().getChunk().getZ();
                        
                        mm.removeValue(chunks, block.getLocation());
                        
                        // revert the block to unpowered (if it can be)
                        if (block.getType() == Material.REDSTONE_WIRE) 
                        	block.setData((byte) 0, true); 
                        else if (block.getType() == Material.REDSTONE_TORCH_ON)
                        	block.setType(Material.REDSTONE_TORCH_OFF);
                        else if (block.getType() == Material.LEVER) {
                        	BlockState state = block.getState(); 
                        	Lever lever = (Lever) state.getData();
                    		lever.setPowered(false);
                    		state.setData(lever);
                    		state.update();
                        }
                        
                        powerable.put(block.getWorld().getName(), mm);
                    }
				}		
        	}.runTaskLater(main, ++i);
         }
        // Later we drop the generator
        new BukkitRunnable(){

			@Override
			public void run() {
				event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.RED + " Generator deactivated!");
				event.getBlock().setType(Material.AIR);
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), RecipeManager.getGenerator());
				inUse.remove(event.getBlock().getLocation());
			}
        	
        }.runTaskLater(main, i);

        // We unregister the generator
        ManyMap<String, Location> mm = generators.get(event.getBlock().getWorld().getName());

        mm.removeValue(chunk, event.getBlock().getLocation());

        generators.put(event.getBlock().getWorld().getName(), mm);
    }

    // Called when a player tries to activate a lever or a button
	@EventHandler
    public void onLeverOrButton(PlayerInteractEvent event){
    	if (event.getClickedBlock() == null) return;
    	
    	String chunk = event.getClickedBlock().getLocation().getChunk().getX() + ";" + event.getClickedBlock().getLocation().getChunk().getZ();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (powerable.get(event.getClickedBlock().getWorld().getName()).getList(chunk).
        		contains(event.getClickedBlock().getLocation())) return;

        // We cancel; send smoke particles for button, and we just turn off the lever (if it was, for some reason, on).
        if (event.getClickedBlock().getType() == Material.STONE_BUTTON ||
                event.getClickedBlock().getType() == Material.WOOD_BUTTON){
            event.setCancelled(true);
            event.getClickedBlock().getWorld().spawnParticle(Particle.SMOKE_NORMAL, event.getClickedBlock().getLocation().add(0.5, 1, 0.5), 7,  0, 0.2, 0, 0.03);
            event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.RED + " There is no generator in range!");
        }else if (event.getClickedBlock().getType() == Material.LEVER){
        	BlockState state =  event.getClickedBlock().getState(); 
        	Lever lever = (Lever) state.getData();
        	
        	lever.setPowered(false);
        	state.setData(lever);
        	state.update();
            event.getClickedBlock().getWorld().spawnParticle(Particle.SMOKE_NORMAL, event.getClickedBlock().getLocation().add(0.5, 1, 0.5), 7,  0, 0.2, 0, 0.03);
            event.getPlayer().sendMessage(MortuusTerraCore.NOTI_PREFIX + ChatColor.RED + " There is no generator in range!");
        }

    }

	// Called when a redstone current moves thru a block
    @EventHandler
    public void onPower(BlockRedstoneEvent event){
    	String chunk = event.getBlock().getLocation().getChunk().getX() + ";" + event.getBlock().getLocation().getChunk().getZ();
        if (!powerable.get(event.getBlock().getWorld().getName()).getList(chunk).contains(event.getBlock().getLocation())) {
            event.getBlock().getWorld().spawnParticle(Particle.SMOKE_NORMAL, event.getBlock().getLocation().add(0.5, 1, 0.5), 7,  0, 0.2, 0, 0.03);
            event.setNewCurrent(0);
        }
    }


    /**
     * Util method to return all blocks in a cubic area around the block
     * @param generator the block in the center.
     * @param radius The size from a corner to the center
     * @return List of blocks around the gen
     */
    public static List<Block>  getNearbyBlocks(Block generator, int radius){

        List<Block> blocks = new ArrayList<>();

        for (int x = -(radius); x <= radius; x ++)
        {
            for (int y = -(radius); y <= radius; y ++) {
                for (int z = -(radius); z <= radius; z ++) {
                        blocks.add(generator.getRelative(x,y,z));
                }
            }
        }
        return blocks;
    }

    /**
     * Util method to divide a large list into several smaller ones
     * @param list The list in question
     * @param size the size of the returned list. The last one will be smaller if not exactly divisible
     * @return List of smaller lists.
     */
    public static <T> List<List<T>> subList(List<T> list, int size){
    	List<List<T>> lists = new ArrayList<>();
    	for (int i = size; i < list.size(); i += size) {
    		if (i > list.size()) {
    			break;
    		}
    		
    		int up = Math.min(list.size(), i += size);
    		i-= size;
    		
    		lists.add(list.subList(i, up));
    	}
    	
    	return lists;
    }
}
