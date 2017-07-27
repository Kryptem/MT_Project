package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import mortuusterra.events.radiation.RadiationDamageEvent;
import mortuusterra.listeners.chat.PlayerChatListener;
import mortuusterra.listeners.radiation.GeckPowerListener;
import mortuusterra.listeners.spawn.SpawnListener;
import mortuusterra.managers.crafting.CellTowerRecipe;
import mortuusterra.managers.player.PlayerManager;
import mortuusterra.managers.radiation.GeckObjectManager;
import mortuusterra.managers.radiation.RadiationManager;
import mortuusterra.managers.tower.CellTowerManager;
import mortuusterra.utils.ElapsedTime;
import mortuusterra.utils.timers.RadiationTimer;

public class Main extends JavaPlugin {

	public Logger logger;

	private SpawnListener spawnListener;

	private ElapsedTime elapsedTime;

	private PlayerManager playerMan;
	private RadiationManager radMan;
	private RadiationTimer radiationTimer;
	private CellTowerRecipe cellTowerRecipe;
	private CellTowerManager cellTowerManager;
	private PlayerChatListener playerChatListener;
	private RadiationDamageEvent radDamageEvent;
	private GeckObjectManager geckObjectManager;
	private GeckPowerListener geckPowerListener;

	private BukkitTask radTimer;

	@Override
	public void onEnable() {
		logger = Logger.getLogger("Minecraft");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		registerResipes();
		initiateOther();
		elapsedTime.setupStartTime();
		registerRadiationTimer();
		registerListeners();
		initiateManagers();
		registerEvents();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "DONE");
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
	private void registerResipes() {
		cellTowerRecipe = new CellTowerRecipe();
		cellTowerRecipe.setRecipe();
	}
	private void registerEvents() {
		radDamageEvent = new RadiationDamageEvent();
	}

	private void registerListeners() {
		spawnListener = new SpawnListener();
		playerChatListener = new PlayerChatListener();

		getServer().getPluginManager().registerEvents(this.spawnListener, this);
		getServer().getPluginManager().registerEvents(this.playerChatListener, this);
		
	}

	private void initiateManagers() {
		playerMan = new PlayerManager();
		cellTowerManager = new CellTowerManager();
		radMan = new RadiationManager();
	}

	private void initiateOther() {
		elapsedTime = new ElapsedTime();
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
	public RadiationManager getRadiationManager() {
		return radMan;
	}
	public CellTowerRecipe getCellTowerRecipe() {
		return cellTowerRecipe;
	}
	public CellTowerManager getCellTowerManager() {
		return cellTowerManager;
	}
	public PlayerChatListener getPlayerChatListener() {
		return playerChatListener;
	}
	public RadiationDamageEvent GetRadiationDamageEvent() {
		return radDamageEvent;
	}
	public GeckObjectManager getGeckObjectManager() {
		return geckObjectManager;
	}
	public GeckPowerListener getGeckPowerListener() {
		return geckPowerListener;
	}
}