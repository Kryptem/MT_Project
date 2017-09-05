package mortuusterra;

/**
 * Created by Kadeska23
 */

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import mortuusterra.events.block.CellTowerBlockEvent;
import mortuusterra.listeners.chat.PlayerChat;
import mortuusterra.listeners.player.PlayerListener;
import mortuusterra.listeners.radiation.GeckPowerListener;
import mortuusterra.listeners.spawn.MobListener;
import mortuusterra.managers.Geck.GeckRangeManager;
import mortuusterra.managers.crafting.RecipeManager;
import mortuusterra.managers.mob.MobManager;
import mortuusterra.managers.player.PlayerManager;
import mortuusterra.managers.radiation.GeckObjectManager;
import mortuusterra.managers.radiation.RadiationManager;

public class Main extends JavaPlugin {

	// private DisguiseAPI disguiseAPI;

	private PlayerManager playerMan;
	private RadiationManager radMan;
	// private CellTowerManager cellTowerManager;
	private GeckObjectManager geckObjectManager;
	private GeckRangeManager geckRangeManager;
	private MobManager mobManager;
	// private SupplyDropManager supplyDropManager;

	private PlayerChat playerChatListener;
	private GeckPowerListener geckPowerListener;
	private MobListener mobListener;
	private PlayerListener playerListener;

	private RecipeManager recipeManager;

	private BukkitTask radTimer;
	// private BukkitTask supplyDropTimer;

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		registerRecipes();
		registerListeners();
		initiateManagers();
		registerRadiationTimer();

		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "DONE");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		// getFileManager().saveFiles();
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	private void registerRadiationTimer() {
		// We aren't using this rn. this.supplyDropTimer = new
		// SupplyDropTimer().runTaskTimer(this, 1L, 500L);
	}

	private void registerRecipes() {
		recipeManager = new RecipeManager();
		recipeManager.setCellTowerRecipe();
		recipeManager.setGeneratorRecipe();
		recipeManager.setPowerOutletRecipe();
	}

	private void registerListeners() {
		playerListener = new PlayerListener();
		playerChatListener = new PlayerChat();
		mobListener = new MobListener();
		geckPowerListener = new GeckPowerListener();

		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.playerChatListener, this);
		getServer().getPluginManager().registerEvents(this.mobListener, this);
		getServer().getPluginManager().registerEvents(this.geckPowerListener, this);
		getServer().getPluginManager().registerEvents(new CellTowerBlockEvent(), this);
	}

	private void initiateManagers() {
		playerMan = new PlayerManager();
		geckObjectManager = new GeckObjectManager();
		// cellTowerManager = new CellTowerManager();
		geckRangeManager = new GeckRangeManager();
		radMan = new RadiationManager();
		mobManager = new MobManager();
		// supplyDropManager = new SupplyDropManager();
	}

	public MobListener getMobListener() {
		return mobListener;
	}

	public MobManager getMobManager() {
		return mobManager;
	}

	public PlayerManager getPlayerManager() {
		return playerMan;
	}

	public RadiationManager getRadiationManager() {
		return radMan;
	}

	public RecipeManager getCellTowerRecipe() {
		return recipeManager;
	}

	/**
	 * public CellTowerManager getCellTowerManager() { return cellTowerManager; }
	 **/

	/**
	 * public PlayerChatListener getPlayerChatListener() { return
	 * playerChatListener; }
	 **/

	public GeckObjectManager getGeckObjectManager() {
		return geckObjectManager;
	}

	public GeckPowerListener getGeckPowerListener() {
		return geckPowerListener;
	}

	public GeckRangeManager getGeckRangeManager() {
		return geckRangeManager;
	}

	/*
	 * public SupplyDropManager getSupplyDropManager() { return supplyDropManager; }
	 */
	public BukkitTask getRadTimer() {
		return radTimer;
	}

	/**
	 * public BukkitTask getSupplyDropTimer() { return supplyDropTimer; }
	 **/
	public PlayerListener getPlayerListener() {
		return playerListener;
	}
	/**
	 * public DisguiseAPI getDisguiseAPI() { return disguiseAPI; }
	 **/
}