package mortuusterra.utils.timers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import mortuusterra.Main;

public class RadiationTimer extends BukkitRunnable{
	
	Main main;

	public void run() {
		if (main.getServer().getOnlinePlayers().isEmpty()) {
			main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "there are no players online");
			this.cancel();
		} else {
			for (Player p : main.getServer().getOnlinePlayers()) {

				main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "give PlayerRad");
				//radMan.givePlayerRad(p);
			}
		}

	}
}
