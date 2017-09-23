package mortuusterra.listeners.spawn;

import mortuusterra.MortuusTerraCore;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.world.ChunkLoadEvent;

public class MobListener implements Listener {

	private MortuusTerraCore main = MortuusTerraCore.getPlugin(MortuusTerraCore.class);
	private DamageCause cause;

	// Changed to CreatureSpawnEvent 9/2/17
	@EventHandler
	public void onEntitySpawn(CreatureSpawnEvent e) {
		if (e.getEntityType().isAlive() && e.getEntityType() != EntityType.PLAYER
				&& e.getEntityType() != EntityType.ZOMBIE && e.getEntityType() != EntityType.ENDERMAN
				&& e.getEntityType() != EntityType.PIG && e.getEntityType() != EntityType.COW
				&& e.getEntityType() != EntityType.SHEEP && e.getEntityType() != EntityType.HORSE) {
			e.setCancelled(true);
			e.getLocation().getWorld().spawn(e.getLocation(), Zombie.class);
		}

	}

	@EventHandler
	public void onBurn(EntityCombustEvent e) {
		if (e.getEntityType() == EntityType.ZOMBIE) {
			if (isSunDamage()) {
				e.getEntity().setFireTicks(0);
				e.setDuration(0);
				e.setCancelled(true);
			}
		}
	}

	private boolean isSunDamage() {
		if (cause != DamageCause.LAVA || cause != DamageCause.ENTITY_ATTACK || cause != DamageCause.ENTITY_SWEEP_ATTACK
				|| cause != DamageCause.PROJECTILE || cause != DamageCause.FIRE) {
			return true;
		} else {
			return false;
		}
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		main.getMobManager().clearUnwantedMobs(event.getChunk());
	}
}