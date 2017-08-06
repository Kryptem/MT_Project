package mortuusterra.utils.timers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.Main;

public class RadiationTimer extends BukkitRunnable {

	private long timeout = 35L;

	Main main = JavaPlugin.getPlugin(Main.class);

	public void run() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			//if the server is empty then wait
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			//if the server is not empty then run the check for all players
				main.getRadiationManager().CheckEachPlayerLocation();
		}

	}
}
