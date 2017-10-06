package com.mortuusterra.objects.mobs;

import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.EnumDifficulty;
import net.minecraft.server.v1_12_R1.World;

/**
 * A zombie clone that has the ability to spawn during the day.
 * @author Horsey
 *
 */
public class DayZombie extends EntityZombie {

    public DayZombie(World world) {
        super(world);

    }
    
    /**
     * Don't run light checks.
     */
    @Override
    public boolean P() {
    	return world.getDifficulty() != EnumDifficulty.PEACEFUL;
    }
}