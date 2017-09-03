package mortuusterra.managers.mob;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

import java.util.Arrays;

public class MobManager {

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

	public void clearUnwantedMobs(Chunk loaded){
		long b4 = System.nanoTime();
        Arrays.stream(loaded.getEntities()).filter(e -> e instanceof LivingEntity).filter(entity ->
                (entity.getType().isAlive() && entity.getType() != EntityType.PLAYER
                && entity.getType() != EntityType.ZOMBIE && entity.getType() != EntityType.ENDERMAN
                && entity.getType() != EntityType.PIG && entity.getType() != EntityType.COW
                && entity.getType() != EntityType.SHEEP)).forEach(entity -> ((LivingEntity)entity).damage(((LivingEntity) entity).getHealth() + 1));
    	System.out.println(System.nanoTime() - b4);
	}
}
