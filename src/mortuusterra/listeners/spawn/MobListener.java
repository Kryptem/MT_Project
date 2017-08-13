package mortuusterra.listeners.spawn;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobListener implements Listener {
	private EntityType type;
	private DamageCause cause;

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		if (e.getEntityType().isAlive() && e.getEntityType() != EntityType.PLAYER
				&& e.getEntityType() != EntityType.ZOMBIE && e.getEntityType() != EntityType.ENDERMAN
				&& e.getEntityType() != EntityType.PIG && e.getEntityType() != EntityType.COW
				&& e.getEntityType() != EntityType.SHEEP) {
			this.type = e.getEntityType();
			e.setCancelled(true);
			e.getLocation().getWorld().spawn(e.getLocation(), Zombie.class);
		}

	}

	@EventHandler
	public void onBurn(EntityCombustEvent e) {
		if (e.getEntity().getLastDamageCause() != null) {
			this.cause = e.getEntity().getLastDamageCause().getCause();
			if (e.getEntityType() == EntityType.ZOMBIE && isSunDamage()) {
				e.setCancelled(true);

			}
		} else {
			return;
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

	public EntityType getType() {
		return type;
	}
}