package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import mortuusterra.listeners.spawn.SpawnListener;
import mortuusterra.managers.player.PlayerManager;
import mortuusterra.managers.radiation.RadiationManager;
import mortuusterra.utils.ElapsedTime;
import mortuusterra.utils.timers.RadiationTimer;

public class Main extends JavaPlugin {

	public Logger logger;

	protected SpawnListener spawnListener;

	protected ElapsedTime elapsedTime;

	protected PlayerManager playerMan;
	protected RadiationManager radMan;
	protected RadiationTimer radiationTimer;

	protected BukkitTask radTimer;

	@Override
	public void onEnable() {
		logger = Logger.getLogger("Minecraft");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		initiateManagers();
		registerListeners();
		initiateOther();
		registerRadiationTimer();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "DONE");
		elapsedTime.setupStartTime();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		elapsedTime.setupStopTime();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		// getFileManager().saveFiles();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	public void registerRadiationTimer() {
		radTimer = new RadiationTimer().runTaskTimer(this, 0L, 80L);
	}

	private void registerListeners() {
		spawnListener = new SpawnListener();

		getServer().getPluginManager().registerEvents(this.spawnListener, this);
	}

	private void initiateManagers() {
		playerMan = new PlayerManager();
	}

	private void initiateOther() {
		elapsedTime = new ElapsedTime();
		radMan = new RadiationManager();
	}

	public SpawnListener getSpawnListener() {
		return spawnListener;
	}

	public ElapsedTime getElapsedTime() {
		return elapsedTime;
	}
	public BukkitTask radiationTimer() {
		return radTimer;
	}
	public PlayerManager getPlayerManager() {
		return playerMan;
	}
	public RadiationTimer getRadaionTimer() {
		return radiationTimer;
	}
}
