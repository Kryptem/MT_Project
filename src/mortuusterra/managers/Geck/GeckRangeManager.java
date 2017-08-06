package mortuusterra.managers.Geck;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;
import mortuusterra.objects.geck.GeckObject;

public class GeckRangeManager {
	Main main = JavaPlugin.getPlugin(Main.class);

	private Location playerLocation;
	private Location geckLocation;

	private double distance;

	public void checkAllPlayers() {
		for (Player p : main.getServer().getOnlinePlayers()) {
			p.sendMessage("adding you to RadPlayermap");
			main.getPlayerManager().addRadPlayer(p);
			checkRange(p);
		}
	}

	private void checkRange(Player p) {
		p.sendMessage("checkRange has ran");
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			this.geckLocation = geckObject.getGeckLocation();
			this.playerLocation = p.getLocation();
			setPlayerLocation(p, p.getLocation());

			this.distance = this.geckLocation.distance(playerLocation);

			// if the distance is less than x then you are in range of the geck
			if (this.distance < 3) {
				p.sendMessage("you are in range");
				if (main.getPlayerManager().containsGeckPlayer(p.getUniqueId().toString())) {
					main.getPlayerManager().getGeckPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(true);
				} else {
					main.getPlayerManager().addGeckPlayer(p);
					main.getPlayerManager().getGeckPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(true);
				}
			} else {
				p.sendMessage("you are not in range");
				if (main.getPlayerManager().containsGeckPlayer(p.getUniqueId().toString())) {
					p.sendMessage("removing you from GeckPlayerMap");
					main.getPlayerManager().removeGeckPlayer(p);
				}
			}
		}
	}

	private void setPlayerLocation(Player p, Location l) {
		p.sendMessage("setting you location");
		main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerLocation(l);
	}
}
