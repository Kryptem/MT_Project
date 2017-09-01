package mortuusterra.listeners.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class PlayerChat implements Listener {
	private Main main = JavaPlugin.getPlugin(Main.class);

	@EventHandler
	private void onChatEvent(AsyncPlayerChatEvent e) {
		e.setCancelled(true);

		String message = e.getMessage();

		Player sender = e.getPlayer();

		for (Player recipient : e.getRecipients()) {
			if (playerInRad(recipient) || playerInRad(sender)) {
				String scrambled = message.replaceAll("a", "#").replaceAll("e", " ").replaceAll("i", "#")
						.replaceAll("o", " ").replaceAll("u", "#").replaceAll("y", " ").replaceAll("A", "#").replaceAll("E", " ").replaceAll("I", "#")
						.replaceAll("O", " ").replaceAll("U", "#").replaceAll("Y", " ");

				//e.setMessage(ChatColor.DARK_AQUA + "{" + e.getPlayer().getDisplayName() + "} : SCRAMBLED " + ChatColor.GRAY + scrambled);
				
				String scrambledMessage = ChatColor.DARK_AQUA + "{" + e.getPlayer().getDisplayName() + "} : SCRAMBLED "+ ChatColor.GRAY + scrambled;
				recipient.sendMessage(scrambledMessage);
			} else {
				String RawMessage = ChatColor.DARK_AQUA + "{" + e.getPlayer().getDisplayName() + "} : RAW " + ChatColor.GRAY
						+ e.getMessage();

				recipient.sendMessage(RawMessage);
			}
		}
	}

	private boolean playerInRad(Player p) {
		return main.getRadiationManager().isPlayerInRad(p);
	}

}
