package mortuusterra.listeners.chat;

import java.util.ArrayList;
import java.util.List;
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
				String scrambled = main.getMessageScrambler().scramble(message, sender, recipient);

				String scrambledMessage = ChatColor.DARK_AQUA + "{" + e.getPlayer().getDisplayName() + "} : "
						+ ChatColor.GRAY + scrambled;
				recipient.sendMessage(scrambledMessage);
			} else {
				String RawMessage = ChatColor.DARK_AQUA + "{" + e.getPlayer().getDisplayName() + "} : " + ChatColor.GRAY
						+ e.getMessage();

				recipient.sendMessage(RawMessage);
			}
		}
	}

	private boolean playerInRad(Player p) {
		return main.getRadiationManager().isPlayerInRad(p);
	}
	
	private String shuffle(String input) {
		List<Character> characters = new ArrayList<Character>();
		for(char c:input.toCharArray()) {
			characters.add(c);
		}
		StringBuilder output = new StringBuilder(input.length());
		while(characters.size()!=0) {
			int randPicker = (int)(Math.random()*characters.size());
			output.append(characters.remove(randPicker));
		}
		return output.toString();
	}

}
