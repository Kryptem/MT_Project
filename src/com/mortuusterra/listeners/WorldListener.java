/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.listeners;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.mortuusterra.MortuusTerraCore;
import com.mortuusterra.objects.FalloutShelter;

public class WorldListener implements Listener {

	private MortuusTerraCore core;
	private int timePassed;

	public WorldListener(MortuusTerraCore core) {
		this.core = core;
		this.timePassed = 0;
		startSupplyDropTimer();
	}

	private void startSupplyDropTimer() {
		Random r = new Random();

		new BukkitRunnable() {
			int randomFactor = 0;
			
			@Override
			public void run() {
				if (timePassed == 0) 
					randomFactor = r.nextInt(48001) - 24000;
				if (timePassed % 200 == 0)
					core.getLogger().info("Next supplydrop in " + (((216000 + randomFactor) - timePassed) / 20) + " seconds.");
				// If 3 hours (216.000 ticks) ± 20 minutes (24000 ticks) have passed deliver.
				// Getting the world should maybe be changed later.
				if (timePassed >= 216000 + randomFactor) {
					core.getSupplyDropManager().deliverSupplyDrop(core.getServer().getWorld("world"));
					timePassed = 0;
				}
				timePassed++;
			}

		}.runTaskTimer(core, 0L, 1L);
	}

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e) {
		if (!e.isNewChunk())
			return;

		Chunk chunk = e.getChunk();
		int x = chunk.getX() * 16;
		int z = chunk.getZ() * 16;
		int y = chunk.getWorld().getHighestBlockYAt(x + 8, z + 2);

		Location loc = new Location(e.getWorld(), x + 8, y, z + 2);
		Random r = new Random();

		// 2% chance of spawning shelter on generating new chunks
		if (r.nextInt(100) < 2) {
			FalloutShelter s = new FalloutShelter(loc.clone());
			s.generateFalloutShelter();
		}
	}

}
