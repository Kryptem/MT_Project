package mortuusterra.listeners.spawn;

import mortuusterra.Main;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.world.ChunkLoadEvent;

public class MobListener implements Listener {

	private Main main = Main.getPlugin(Main.class);
    // Changed to CreatureSpawnEvent 9/2/17
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		if (e.getEntityType().isAlive() && e.getEntityType() != EntityType.PLAYER
				&& e.getEntityType() != EntityType.ZOMBIE && e.getEntityType() != EntityType.ENDERMAN
				&& e.getEntityType() != EntityType.PIG && e.getEntityType() != EntityType.COW
				&& e.getEntityType() != EntityType.SHEEP) {
			e.setCancelled(true);
			System.out.println("Called");
			e.getLocation().getWorld().spawn(e.getLocation(), Zombie.class);
		}

	}

	// Fixed, removed unnecessary method. 9/2/17
	@EventHandler
	public void onBurn(EntityCombustEvent e) {
		Location loc = e.getEntity().getLocation();
		int light = loc.getBlock().getLightFromSky();

		if (light >= 12 && e.getEntity().getType() == EntityType.ZOMBIE){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event){
		main.getMobManager().clearUnwantedMobs(event.getChunk());
	}
}