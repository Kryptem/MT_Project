package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.listeners.spawn.SpawnListener;
import mortuusterra.utils.ElapsedTime;

public class Main extends JavaPlugin {

	public static Logger logger;
	public static Plugin plugin;
	public static Main main;

	private SpawnListener spawnListener;
	private ElapsedTime elapsedTime;

	@Override
	public void onEnable() {
		elapsedTime.setupStartTime();
		logger = Logger.getLogger("Minecraft");
		logger.info(ChatColor.DARK_GREEN + "|---------|");
		initiateManagers();
		registerListeners();
		logger.info(ChatColor.DARK_BLUE + "DONE");
		logger.info(ChatColor.DARK_GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		elapsedTime.setupStopTime();
		logger.info(ChatColor.DARK_GREEN + "|----------|");
		// getFileManager().saveFiles();
		logger.info(ChatColor.DARK_GREEN + "|----------|");
	}

	private void registerListeners() {
		spawnListener = new SpawnListener();
		
		getServer().getPluginManager().registerEvents(this.spawnListener, this);
	}

	private void initiateManagers() {
		// MobManager();

	}

	public SpawnListener getSpawnListener() {
		return spawnListener;
	}
	public ElapsedTime getElapsedTime() {
		return elapsedTime;
	}
}
