package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import mortuusterra.events.radiation.RadiationDamageEvent;
import mortuusterra.listeners.radiation.GeckPowerListener;
import mortuusterra.listeners.spawn.MobListener;
import mortuusterra.managers.Geck.GeckRangeManager;
import mortuusterra.managers.crafting.CellTowerRecipe;
import mortuusterra.managers.player.PlayerManager;
import mortuusterra.managers.radiation.GeckObjectManager;
import mortuusterra.managers.radiation.RadiationManager;
import mortuusterra.managers.tower.CellTowerManager;
import mortuusterra.utils.ElapsedTime;
import mortuusterra.utils.timers.RadiationTimer;

public class Main extends JavaPlugin {

	public Logger logger;
	
	private PlayerManager playerMan;
	private RadiationManager radMan;
	private CellTowerManager cellTowerManager;
	private GeckObjectManager geckObjectManager;
	private GeckRangeManager geckRangeManager;

	//private PlayerChatListener playerChatListener;
	private GeckPowerListener geckPowerListener;
	private MobListener mobListener;
	
	private RadiationDamageEvent radDamageEvent;
	
	private RadiationTimer radiationTimer;
	private CellTowerRecipe cellTowerRecipe;
	private ElapsedTime elapsedTime;

	private BukkitTask radTimer;

	@Override
	public void onEnable() {
		logger = Logger.getLogger("Minecraft");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		registerResipes();
		initiateOther();
		registerListeners();
		initiateManagers();
		registerEvents();
		registerRadiationTimer();
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
		radTimer = new RadiationTimer().runTaskTimerAsynchronously(this, 0L, 80L);
		
	}
	private void registerResipes() {
		cellTowerRecipe = new CellTowerRecipe();
		cellTowerRecipe.setRecipe();
	}
	private void registerEvents() {
		radDamageEvent = new RadiationDamageEvent();
	}
	private void registerListeners() {
		mobListener = new MobListener();
		//playerChatListener = new PlayerChatListener();
		geckPowerListener = new GeckPowerListener();

		getServer().getPluginManager().registerEvents(this.mobListener, this);
		//getServer().getPluginManager().registerEvents(this.playerChatListener, this);
		getServer().getPluginManager().registerEvents(this.geckPowerListener, this);
	}
	private void initiateManagers() {
		playerMan = new PlayerManager();
		geckObjectManager = new GeckObjectManager();
		playerMan = new PlayerManager();
		cellTowerManager = new CellTowerManager();
		geckRangeManager = new GeckRangeManager();
		radMan = new RadiationManager();
	}
	private void initiateOther() {
		elapsedTime = new ElapsedTime();
		elapsedTime.setupStartTime();
		
	}
	public MobListener getMobListener() {
		return mobListener;
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
	/**
	public PlayerChatListener getPlayerChatListener() {
		return playerChatListener;
	}
	**/
	public RadiationDamageEvent GetRadiationDamageEvent() {
		return radDamageEvent;
	}
	public GeckObjectManager getGeckObjectManager() {
		return geckObjectManager;
	}
	public GeckPowerListener getGeckPowerListener() {
		return geckPowerListener;
	}
	public GeckRangeManager getGeckRangeManager() {
		return geckRangeManager;
	}
}