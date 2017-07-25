package mortuusterra.utils.timers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.Main;

public class RadiationTimer extends BukkitRunnable{
	
	private long timeout = 30L;
	
	Main main = JavaPlugin.getPlugin(Main.class);

	public void run() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "there are no players online");
			try {
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			for (Player p : main.getServer().getOnlinePlayers()) {
				main.getRadiationManager().givePlayerRads(p);
			}
		}

	}
}
