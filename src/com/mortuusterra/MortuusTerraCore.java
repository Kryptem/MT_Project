package com.mortuusterra;

/**
 * Created by Kadeska23
 */

import com.mortuusterra.listeners.generator.GenListener;
import com.mortuusterra.utils.nmsentities.CustomEntityType;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.mortuusterra.events.block.CellTowerBlockEvent;
import com.mortuusterra.listeners.player.PlayerListener;
import com.mortuusterra.listeners.radiation.GeckPowerListener;
import com.mortuusterra.listeners.spawn.MobListener;
import com.mortuusterra.managers.Geck.GeckRangeManager;
import com.mortuusterra.managers.crafting.RecipeManager;
import com.mortuusterra.managers.mob.MobManager;
import com.mortuusterra.managers.player.PlayerManager;
import com.mortuusterra.managers.radiation.GeckObjectManager;
import com.mortuusterra.managers.radiation.RadiationManager;

public class MortuusTerraCore extends JavaPlugin {
	
	public final MortuusTerraCore main = this;

	// private DisguiseAPI disguiseAPI;
	

	private PlayerManager playerMan;
	private RadiationManager radMan;
	// private CellTowerManager cellTowerManager;
	private GeckObjectManager geckObjectManager;
	private GeckRangeManager geckRangeManager;
	private MobManager mobManager;

	// Just a fancy prefix looks like "[!]"
	public static final String NOTI_PREFIX = ChatColor.translateAlternateColorCodes('&', "&7&l[&b&l!&7&l]");

	//private SupplyDropManager supplyDropManager;

	private GeckPowerListener geckPowerListener;
	private MobListener mobListener;
	private PlayerListener playerListener;

    private GenListener genListener;
	
	//private PlayerChat playerChatListener;


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

		// Register the zombie.
        CustomEntityType.DAY_ZOMBIE.registerEntity();

		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra ready.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		// getFileManager().saveFiles();
        genListener.saveFile();
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
		getServer().getPluginManager().registerEvents(genListener = new GenListener(), this);
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
	
	public MortuusTerraCore getCore() {
		return main;
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