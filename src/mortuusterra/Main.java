package mortuusterra;

/**
 * Created by Kadeska23
 */

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import mortuusterra.events.radiation.RadiationDamageEvent;
import mortuusterra.listeners.player.PlayerListener;
import mortuusterra.listeners.radiation.GeckPowerListener;
import mortuusterra.listeners.spawn.MobListener;
import mortuusterra.managers.Geck.GeckRangeManager;
import mortuusterra.managers.crafting.CellTowerRecipe;
import mortuusterra.managers.mob.MobManager;
import mortuusterra.managers.player.PlayerManager;
import mortuusterra.managers.radiation.GeckObjectManager;
import mortuusterra.managers.radiation.RadiationManager;
import mortuusterra.managers.supplydrops.SupplyDropManager;
import mortuusterra.managers.tower.CellTowerManager;
import mortuusterra.utils.timers.RadiationTimer;
import mortuusterra.utils.timers.SupplyDropTimer;

public class Main extends JavaPlugin {

	public Logger logger;

	private PlayerManager playerMan;
	private RadiationManager radMan;
	private CellTowerManager cellTowerManager;
	private GeckObjectManager geckObjectManager;
	private GeckRangeManager geckRangeManager;
	private MobManager mobManager;
	private SupplyDropManager supplyDropManager;

	// private PlayerChatListener playerChatListener;
	private GeckPowerListener geckPowerListener;
	private MobListener mobListener;
	private PlayerListener playerListener;

	private RadiationDamageEvent radDamageEvent;

	private CellTowerRecipe cellTowerRecipe;

	private BukkitTask radTimer;
	private BukkitTask supplyDropTimer;

	@Override
	public void onEnable() {
		logger = Logger.getLogger("Minecraft");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		registerResipes();
		registerListeners();
		initiateManagers();
		registerEvents();
		registerRadiationTimer();

		clearUnwantedMobs();
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "DONE");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		// getFileManager().saveFiles();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	public void clearUnwantedMobs() {
		mobManager.clearUnwantedMobs();
	}

	public void registerRadiationTimer() {
		this.radTimer = new RadiationTimer().runTaskTimerAsynchronously(this, 0L, 80L);
		this.supplyDropTimer = new SupplyDropTimer().runTaskTimer(this, 1L, 500L);
	}

	private void registerResipes() {
		cellTowerRecipe = new CellTowerRecipe();
		cellTowerRecipe.setRecipe();
	}

	private void registerEvents() {
		radDamageEvent = new RadiationDamageEvent();
	}

	private void registerListeners() {
		playerListener = new PlayerListener();
		mobListener = new MobListener();
		// playerChatListener = new PlayerChatListener();
		geckPowerListener = new GeckPowerListener();

		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.mobListener, this);
		// getServer().getPluginManager().registerEvents(this.playerChatListener, this);
		getServer().getPluginManager().registerEvents(this.geckPowerListener, this);
	}

	private void initiateManagers() {
		playerMan = new PlayerManager();
		geckObjectManager = new GeckObjectManager();
		cellTowerManager = new CellTowerManager();
		geckRangeManager = new GeckRangeManager();
		radMan = new RadiationManager();
		mobManager = new MobManager();
		supplyDropManager = new SupplyDropManager();
	}

	public MobListener getMobListener() {
		return mobListener;
	}

	public PlayerManager getPlayerManager() {
		return playerMan;
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
	 * public PlayerChatListener getPlayerChatListener() { return
	 * playerChatListener; }
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
	public SupplyDropManager getSupplyDropManager() {
		return supplyDropManager;
	}

	public BukkitTask getRadTimer() {
		return radTimer;
	}

	public BukkitTask getSupplyDropTimer() {
		return supplyDropTimer;
	}

	public PlayerListener getPlayerListener() {
		return playerListener;
	}
}