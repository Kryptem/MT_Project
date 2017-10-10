package com.mortuusterra;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.mortuusterra.events.block.CellTowerBlockEvent;
import com.mortuusterra.listeners.GeckPowerListener;
import com.mortuusterra.listeners.GeneratorListener;
import com.mortuusterra.listeners.MobListener;
import com.mortuusterra.listeners.PlayerListener;
import com.mortuusterra.managers.GeckManager;
import com.mortuusterra.managers.GeckObjectManager;
import com.mortuusterra.managers.MobManager;
import com.mortuusterra.managers.PlayerManager;
import com.mortuusterra.managers.RadiationManager;
import com.mortuusterra.managers.RecipeManager;
import com.mortuusterra.utils.files.FileManager;
import com.mortuusterra.utils.nmsentities.CustomEntityType;
import com.mortuusterra.utils.others.StringUtilities;

public class MortuusTerraCore extends JavaPlugin {

	public final MortuusTerraCore main = this;

	// private DisguiseAPI disguiseAPI;
	private FileManager fileManager;

	private PlayerManager playerMan;
	private RadiationManager radMan;
	// private CellTowerManager cellTowerManager;
	private GeckObjectManager geckObjectManager;
	private GeckManager geckManager;
	private MobManager mobManager;

	// Just a fancy prefix looks like "[!]"
	public static final String NOTI_PREFIX = StringUtilities.color("&7&l[&b&l!&7&l] ");

	// private SupplyDropManager supplyDropManager;

	private GeckPowerListener geckPowerListener;
	private MobListener mobListener;
	private PlayerListener playerListener;

	private GeneratorListener genListener;

	// private PlayerChat playerChatListener;

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

		// Load every file from disk.
		getFileManager().loadFiles();

		// Register the zombie.
		CustomEntityType.DAY_ZOMBIE.registerEntity();
		getRadiationManager().startPlayerRadiationDamage();
		
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra ready.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");

		// Save every file to disk.
		getFileManager().saveFiles();

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
	}

	private void registerListeners() {
		playerListener = new PlayerListener();
		mobListener = new MobListener();
		geckPowerListener = new GeckPowerListener();

		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.mobListener, this);
		getServer().getPluginManager().registerEvents(this.geckPowerListener, this);
		getServer().getPluginManager().registerEvents(new CellTowerBlockEvent(), this);
		getServer().getPluginManager().registerEvents(genListener = new GeneratorListener(), this);
	}

	private void initiateManagers() {
		fileManager = new FileManager();
		playerMan = new PlayerManager();
		geckObjectManager = new GeckObjectManager();
		// cellTowerManager = new CellTowerManager();
		geckManager = new GeckManager();
		radMan = new RadiationManager();
		mobManager = new MobManager();
		// supplyDropManager = new SupplyDropManager();
	}

	public MortuusTerraCore getCore() {
		return main;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public MobListener getMobListener() {
		return mobListener;
	}

	public GeneratorListener getGenListener() {
		return genListener;
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
	 * public CellTowerManager getCellTowerManager() { return cellTowerManager;
	 * }
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

	public GeckManager getGeckManager() {
		return geckManager;
	}

	/*
	 * public SupplyDropManager getSupplyDropManager() { return
	 * supplyDropManager; }
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