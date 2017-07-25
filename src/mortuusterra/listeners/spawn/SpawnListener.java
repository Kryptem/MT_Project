package mortuusterra.listeners.spawn;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnListener implements Listener {
	private EntityType type;
	private Location playerLocation;

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if (e.getEntityType().isAlive()) {
			type = e.getEntityType();
			if (type == EntityType.PLAYER) {
				playerLocation = e.getLocation();

			} else if (!(type == EntityType.ZOMBIE || type == EntityType.ENDERMAN)) {
				e.setCancelled(true);
			}
		}
	}

	public Location getPlayerLocation() {
		return playerLocation;
	}

	public EntityType getType() {
		return type;
	}
}