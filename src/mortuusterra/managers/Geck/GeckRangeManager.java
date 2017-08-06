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

	public void checkPlayers(Player p) {
			main.getPlayerManager().addRadPlayer(p);
			setPlayerLocation(p, p.getLocation());
			checkRange(p);
	}

	private void checkRange(Player p) {
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			this.geckLocation = geckObject.getGeckLocation();
			this.playerLocation = p.getLocation();

			this.distance = this.geckLocation.distance(playerLocation);

			// if the distance is less than x then you are in range of the geck
			if (this.distance < 3) {
				if (main.getPlayerManager().containsGeckPlayer(p.getUniqueId().toString())) {
					main.getPlayerManager().getGeckPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(true);
				} else {
					main.getPlayerManager().addGeckPlayer(p);
					main.getPlayerManager().getGeckPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(true);
				}
			} else {
				if (main.getPlayerManager().containsGeckPlayer(p.getUniqueId().toString())) {
					main.getPlayerManager().removeGeckPlayer(p);
				}
			}
		}
	}

	private void setPlayerLocation(Player p, Location l) {
		main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerLocation(l);
	}
}
