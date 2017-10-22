package com.mortuusterra.managers;

import java.util.Arrays;

import org.bukkit.Chunk;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class MobManager {

	/**
	 * Loop over all entities and kill them if they are not a zombie, pig, enderman, cow or sheep.
	 * @param loaded the chunk which was loaded
	 */
	public void clearUnwantedMobs(Chunk loaded){
        Arrays.stream(loaded.getEntities()).filter(e -> e instanceof LivingEntity).filter(entity ->
                (entity.getType().isAlive() && entity.getType() != EntityType.PLAYER
                && entity.getType() != EntityType.ZOMBIE && entity.getType() != EntityType.ENDERMAN
                && entity.getType() != EntityType.PIG && entity.getType() != EntityType.COW
                && entity.getType() != EntityType.SHEEP)).forEach(entity -> ((LivingEntity)entity).damage(((LivingEntity) entity).getHealth() + 1));
	}

}
