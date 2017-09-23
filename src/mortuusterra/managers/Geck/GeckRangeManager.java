package mortuusterra.managers.Geck;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.MortuusTerraCore;
import mortuusterra.objects.geck.GeckObject;

public class GeckRangeManager {
	private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

	public void checkPlayers(Player p) {
		setPlayerLocation(p, p.getLocation());
		checkRange(p);
	}

	private void checkRange(Player p) {
		for (GeckObject geckObject : main.getGeckObjectManager().getGecklocationMap().values()) {
			Location geckLocation = geckObject.getGeckLocation();
			Location playerLocation = p.getLocation();
            if (!geckLocation.getWorld().equals(playerLocation.getWorld())) continue;

			double distance = geckLocation.distanceSquared(playerLocation);

			// if the distance is less than or = to x and the GECK is correct and powered then you are in range of the geck
			int x = 225; // this is the squared range of blocks. the block rang is 15 blocks.
			if (distance <= x) {
					main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(
							main.getGeckObjectManager().getGeckObject(geckLocation).isCorrect()
							&& main.getGeckObjectManager().getGeckObject(geckLocation).getIspowered());

			} else {
				main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerInRangeOfGeck(false);
			}
		}
	}

	private void setPlayerLocation(Player p, Location l) {
		main.getPlayerManager().getRadPlayer(p.getUniqueId().toString()).setPlayerLocation(l);
	}
}
