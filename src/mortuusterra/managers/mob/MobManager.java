package mortuusterra.managers.mob;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class MobManager {

	Main main = JavaPlugin.getPlugin(Main.class);

	public void clearUnwantedMobs() {

		for (LivingEntity entity : Bukkit.getServer().getWorld("world").getLivingEntities()) {
			if (entity.getType().isAlive() && entity.getType() != EntityType.PLAYER
					&& entity.getType() != EntityType.ZOMBIE && entity.getType() != EntityType.ENDERMAN
					&& entity.getType() != EntityType.PIG && entity.getType() != EntityType.COW
					&& entity.getType() != EntityType.SHEEP) {
				entity.damage(entity.getHealth() + 1);

			}
		}
	}
}
