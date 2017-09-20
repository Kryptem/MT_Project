package mortuusterra.managers.misc;

import java.util.Random;

import org.bukkit.entity.Player;

public class MessageScrambler {

	final int minDistance = 0;
	final int maxDistance = 100;
	final int eighty_percent = maxDistance * 80 / 100;
	final int sixty_percent = maxDistance * 60 / 100;
	final int forty_percent = maxDistance * 40 / 100;
	final int twenty_percent = maxDistance * 20 / 100;
	
	final char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public String scramble(String message, Player from, Player to) {

		int length = message.length();
		int i = length;
		int distance = (int) from.getLocation().distance(to.getLocation());
		
		String newMessage;
		
		// Scrambling power = 100%
		if (distance < maxDistance) {
			while (i > 0) {
				newMessage = message.replace(selectAChar(message), generateCharacter());
				i--;
			}
		}

		// Scrambling power = 80%
		if (distance < eighty_percent) {
			while ((i*80/100) > 0) {
				newMessage = message.replace(selectAChar(message), generateCharacter());
				i--;
			}
		}

		// Scrambling power = 60%
		if (distance < sixty_percent) {
			while ((i*60/100) > 0) {
				newMessage = message.replace(selectAChar(message), generateCharacter());
				i--;
			}
		}

		// Scrambling power = 40%
		if (distance < forty_percent) {
			while ((i*40/100) > 0) {
				newMessage = message.replace(selectAChar(message), generateCharacter());
				i--;
			}
		}

		// Scrambling power = 20%
		if (distance < twenty_percent) {
			while ((i*20/100) > 0) {
				newMessage = message.replace(selectAChar(message), generateCharacter());
				i--;
			}
		}
		
		newMessage = message;

		return newMessage;

	}
	
	private char selectAChar(String input) {
		Random ran = new Random();
		int index = ran.nextInt(input.length());
		return input.charAt(index);
	}
	private char generateCharacter() {
		final String alphabet = "abcdefghijklmnopqerstuwxyz";
		final int N = alphabet.length();
		int i = new Random().nextInt(N);
		return alphabet.charAt(i);
	}

}
