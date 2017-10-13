package com.mortuusterra.listeners;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import com.mortuusterra.objects.FalloutShelter;

public class WorldGenerationListener implements Listener {
	
	@EventHandler
	public void onChunk(ChunkLoadEvent e) {
		if (!e.isNewChunk())
			return;

		Chunk chunk = e.getChunk();
		int x = chunk.getX() * 16;
		int z = chunk.getZ() * 16;
		int y = chunk.getWorld().getHighestBlockYAt(x + 8, z + 2);

		Location loc = new Location(e.getWorld(), x + 8, y, z + 2);
		Random r = new Random();
		
		if (r.nextInt(100) < 2) {
			FalloutShelter s = new FalloutShelter(loc.clone());
			s.generateFalloutShelter();
		}
			
	}

}
