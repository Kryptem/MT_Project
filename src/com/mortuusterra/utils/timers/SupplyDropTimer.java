package com.mortuusterra.utils.timers;

import com.mortuusterra.MortuusTerraCore;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SupplyDropTimer  extends BukkitRunnable {
	private long timeout = 40L;

	MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);
	
	@Override
	public void run() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			//if the server is empty then wait
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			//main.getSupplyDropManager().deliverSupplyDrop();
		}
		
	}

}
