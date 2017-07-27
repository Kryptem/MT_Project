package mortuusterra.utils.timers;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.Main;

public class RadiationTimer extends BukkitRunnable {

	private long timeout = 35L;

	Main main = JavaPlugin.getPlugin(Main.class);

	public void run() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			if (main.GetRadiationDamageEvent().isCancelled()) {
				try {
					Thread.sleep(timeout);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				main.getServer().getPluginManager().callEvent(main.GetRadiationDamageEvent());
				main.getRadiationManager().CheckEachPlayerLocation();
			}
		}

	}
}
