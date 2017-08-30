package mortuusterra.utils.timers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.Main;

public class SupplyDropTimer  extends BukkitRunnable {
	private long timeout = 40L;

	Main main = JavaPlugin.getPlugin(Main.class);
	
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
