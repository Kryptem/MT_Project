package mortuusterra.managers.Geck;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;
import mortuusterra.objects.geck.GeckObject;

public class GeckRangeManager {
	private Main main = JavaPlugin.getPlugin(Main.class);

	public void checkPlayers(Player p) {
		setPlayerLocation(p, p.getLocation());
		checkRange(p);
	}

	private void checkRange(Player p) {
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			Location geckLocation = geckObject.getGeckLocation();
			Location playerLocation = p.getLocation();

			double distance = geckLocation.distanceSquared(playerLocation);

			System.out.println(distance + " - DISTANCE");

			// if the distance is less than or = to x and the GECK is correct and powered then you are in range of the geck
			int x = 25;
			if (distance <= x) {
					main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(
							main.getGeckObjectManager().getGeckObject(geckLocation).isCorrect()
							&& main.getGeckObjectManager().getGeckObject(geckLocation).getIspowered());
					
					Bukkit.broadcastMessage(main.getGeckObjectManager().getGeckObject(geckLocation).isCorrect() + "");
			} else {
				main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(false);
			}
		}
	}

	private void setPlayerLocation(Player p, Location l) {
		main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerLocation(l);
	}
}
