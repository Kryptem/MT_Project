package mortuusterra.listeners.chat;

import java.util.ArrayList;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class PlayerChatListener implements Listener {
	private Main main = JavaPlugin.getPlugin(Main.class);

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		String preMessage = e.getMessage();
		Set<Player> recipients = e.getRecipients();
		ArrayList<Player> radioReceive = new ArrayList<>();
		ArrayList<Player> scrambleReceive = new ArrayList<>();
		// check if the sender has a radio, then check if the player is in range of a
		// cell tower or another player, then check if there are other players in range
		// if cell tower and if so then send the message raw only to the players that
		// have a radio, all other players recieve a mixed up messages. if not then dont
		// send message.
		if (isSenderHasRadio(e.getPlayer())) {
			if (isInRangeOfCellTower(e.getPlayer())) {
				for (Player p : recipients) {
					if ((isInRangeOfCellTower(p) && isSenderHasRadio(p)) || isInRangeSender(p, e.getPlayer())) {
						radioReceive.add(p);
					}
					if (isSenderHasRadio(p) && !isInRangeOfCellTower(p)) {
						scrambleReceive.add(p);
					}
				}
			}
		} else {
			for (Player p : recipients) {
				if (isInRangeSender(p, e.getPlayer())) {
					radioReceive.add(p);
				}
			}
		}
		e.setCancelled(true);
		for (Player p : radioReceive) {
			p.sendMessage(preMessage);
		}
		for (Player p : scrambleReceive) {
			String scrambledMessage = preMessage;
			scrambledMessage = scrambledMessage.replace("e", String.valueOf(p.getName().length()));
			scrambledMessage = ChatColor.MAGIC + scrambledMessage;
			p.sendMessage(scrambledMessage);
		}

		e.getPlayer().sendMessage(preMessage);
	}

	private boolean isInRangeSender(Player recieve, Player send) {
		return send.getLocation().distance(recieve.getLocation()) <= 50;
	}

	private boolean isSenderHasRadio(Player p) {
		return p.getInventory().contains(Material.JUKEBOX);

	}

	private boolean isInRangeOfCellTower(Player p) {
		for (Location loc : main.getCellTowerManager().getCellTowerLocationsMap().keySet()) {
			if (p.getLocation().distanceSquared(loc) <= 2500) {
				return true;
			}
		}
		return false;
	}
}