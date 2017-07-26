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
	Main main = JavaPlugin.getPlugin(Main.class);

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Location senderLocation = e.getPlayer().getLocation();
		String preMessage = e.getMessage();
		Set<Player> recipients = e.getRecipients();
		ArrayList<Player> radioRecieve = new ArrayList<>();
		ArrayList<Player> scrambleRecieve = new ArrayList<>();
		// check if the sender has a radio, then check if the player is in range of a
		// cell tower or another player, then check if there are other players in range
		// if cell tower and if so then send the message raw only to the players that
		// have a radio, all other players recieve a mixed up messages. if not then dont
		// send message.
		if (isSenderHasRadio(e.getPlayer())) {
			if (isInRangeOfCellTower(e.getPlayer())) {
				for (Player p : recipients) {
					if ((isInRangeOfCellTower(p) && isSenderHasRadio(p)) || isInRangeSender(p, e.getPlayer())) {
						radioRecieve.add(p);
					}
					if (isSenderHasRadio(p) && !isInRangeOfCellTower(p)) {
						scrambleRecieve.add(p);
					}
				}
			}
		} else {
			for (Player p : recipients) {
				if (isInRangeSender(p, e.getPlayer())) {
					radioRecieve.add(p);
				}
			}
		}
		e.setCancelled(true);
		for (Player p : radioRecieve) {
			p.sendMessage(preMessage);
		}
		for (Player p : scrambleRecieve) {
			String scrambledMessage = preMessage;
			scrambledMessage = scrambledMessage.replaceAll("e", String.valueOf(p.getName().length()));
			scrambledMessage = ChatColor.MAGIC + scrambledMessage;
			p.sendMessage(scrambledMessage);
		}

		e.getPlayer().sendMessage(preMessage);
	}

	private boolean isInRangeSender(Player recieve, Player send) {
		return send.getLocation().distance(recieve.getLocation()) <= 50;
	}

	public boolean isSenderHasRadio(Player p) {
		if (p.getInventory().contains(Material.JUKEBOX)) {
			return true;
		} else
			return false;

	}

	public boolean isInRangeOfCellTower(Player p) {
		for (Location loc : main.getCellTowerManager().getCellTowerLocationsMap().keySet()) {
			if (p.getLocation().distance(loc) <= 50) {
				return true;
			}
		}
		return false;
	}
}