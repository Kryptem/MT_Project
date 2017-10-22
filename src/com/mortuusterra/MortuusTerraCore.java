package com.mortuusterra;

<<<<<<< HEAD
/**
 * Created by Kadeska23
 */

import com.mortuusterra.listeners.generator.GenListener;
import com.mortuusterra.listeners.mob.MobListener;
import com.mortuusterra.utils.nmsentities.CustomEntityType;
=======
>>>>>>> a3b0c6bc9e48566cf827d6422684ac6b888df60f
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
<<<<<<< HEAD
import org.bukkit.scheduler.BukkitTask;

import com.mortuusterra.events.block.CellTowerBlockEvent;
import com.mortuusterra.listeners.player.PlayerListener;
import com.mortuusterra.listeners.radiation.GeckPowerListener;
import com.mortuusterra.managers.Geck.GeckRangeManager;
import com.mortuusterra.managers.crafting.RecipeManager;
import com.mortuusterra.managers.mob.MobManager;
import com.mortuusterra.managers.player.PlayerManager;
import com.mortuusterra.managers.radiation.GeckObjectManager;
import com.mortuusterra.managers.radiation.RadiationManager;
=======

import com.mortuusterra.commands.AdminCommands;
import com.mortuusterra.listeners.GeckPowerListener;
import com.mortuusterra.listeners.GeneratorListener;
import com.mortuusterra.listeners.MobListener;
import com.mortuusterra.listeners.PlayerListener;
import com.mortuusterra.listeners.WorldListener;
import com.mortuusterra.managers.GeckManager;
import com.mortuusterra.managers.GeckObjectManager;
import com.mortuusterra.managers.MobManager;
import com.mortuusterra.managers.PlayerManager;
import com.mortuusterra.managers.RadiationManager;
import com.mortuusterra.managers.RecipeManager;
import com.mortuusterra.managers.SupplyDropManager;
import com.mortuusterra.objects.CustomScoreboards;
import com.mortuusterra.utils.files.FileManager;
import com.mortuusterra.utils.nmsentities.CustomEntityType;
import com.mortuusterra.utils.others.StringUtilities;
>>>>>>> a3b0c6bc9e48566cf827d6422684ac6b888df60f

public class MortuusTerraCore extends JavaPlugin {

	/*
	 * Use getCore() to get this main class and all of its public methods. DO
	 * NOT ACCESS BEFORE ONENABLE
	 **/
	public static MortuusTerraCore core;

	/*
	 * Hook into the Disguise API. This is used for making infected players look
	 * like a zombie.
	 **/
	// private DisguiseAPI disguiseAPI;

	/*
	 * These are all of the managers
	 **/
	private CustomScoreboards scoreboards;
	private FileManager fileManager;
	private PlayerManager playerMan;
	private RadiationManager radMan;
	private GeckObjectManager geckObjectManager;
	private GeckManager geckManager;
	private MobManager mobManager;
	private RecipeManager recipeManager;
	private SupplyDropManager supplyDropManager;

	/*
	 * These are all of the listeners
	 **/
	private GeneratorListener genListener;

	/*
	 * These are all of the prefixes
	 **/
	// looks like "[MTCore]"
	public static final String MTC_PREFIX = StringUtilities.color("&7&l[&b&lMTCore&7&l]");
	// looks like "[!]"
	public static final String NOTI_PREFIX = StringUtilities.color("&7&l[&b&l!&7&l] ");
	// looks like "[Alert!]"
	public static final String ALERT_PREFIX = StringUtilities.color("&7&l[&c&lAlert!&7&l] ");

	@Override
	public void onEnable() {

		core = this;
		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Starting Mortuus Terra.");

		// register/initiate listeners
		registerListeners();

		// register/initiate managers
		initiateManagers();

		// register/initiate recipes
		registerRecipes();

		// register custom Zombie
		CustomEntityType.DAY_ZOMBIE.registerEntity();
		getCommand("supplydrop").setExecutor(new AdminCommands(this));
		// register/initiate timers

		// start radiation
		getRadiationManager().startPlayerRadiationDamage();

		// Load files
		fileManager = new FileManager();
		getFileManager().loadFiles();

		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra ready.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	@Override
	public void onDisable() {
		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Stoping Mortuus Terra.");

		// Save every file to disk.
		getFileManager().saveFiles();

		// Console sender
		getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Mortuus Terra stopped.");
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "|----------|");
	}

	private void registerRecipes() {
		recipeManager = new RecipeManager();
		recipeManager.setCellTowerRecipe();
		recipeManager.setGeneratorRecipe();
	}

	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();

		manager.registerEvents(new PlayerListener(), this);
		manager.registerEvents(new MobListener(), this);
		manager.registerEvents(new GeckPowerListener(), this);
		manager.registerEvents(new WorldListener(this), this);
		// getServer().getPluginManager().registerEvents(new
		// CellTowerBlockEvent(),
		// this);
		manager.registerEvents(genListener = new GeneratorListener(), this);
	}

	private void initiateManagers() {
		playerMan = new PlayerManager();
		geckObjectManager = new GeckObjectManager();
		geckManager = new GeckManager();
		radMan = new RadiationManager();
		mobManager = new MobManager();
		supplyDropManager = new SupplyDropManager(this);
		scoreboards = new CustomScoreboards();
	}

	public static MortuusTerraCore getCore() {
		return core;
	}

	public FileManager getFileManager() {
		return fileManager;
	}
	
	public CustomScoreboards getScoreboards() {
		return scoreboards;
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

	public GeckObjectManager getGeckObjectManager() {
		return geckObjectManager;
	}

	public GeckManager getGeckManager() {
		return geckManager;
	}

	public SupplyDropManager getSupplyDropManager() {
		return supplyDropManager;
	}

	/**
	 * public DisguiseAPI getDisguiseAPI() { return disguiseAPI; }
	 **/
}