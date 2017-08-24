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

	private int x = 5;

	public void checkPlayers(Player p) {
		setPlayerLocation(p, p.getLocation());
		checkRange(p);
	}

	private void checkRange(Player p) {
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			this.geckLocation = geckObject.getGeckLocation();
			this.playerLocation = p.getLocation();

			this.distance = this.geckLocation.distance(playerLocation);

			// if the distance is less than or = to x and the GECK is correct and powered then you are in range of the geck
			if (this.distance <= x) {
				if (main.getGeckObjectManager().getGeckObject(geckLocation).isCorrect()
						&& main.getGeckObjectManager().getGeckObject(geckLocation).getIspowered()) {
					main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(true);
				} else {
					main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(false);
				}
			} else {
				main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(false);
			}
		}
	}

	private void setPlayerLocation(Player p, Location l) {
		main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerLocation(l);
	}
}
