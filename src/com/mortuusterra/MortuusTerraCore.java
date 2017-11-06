/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */

package com.mortuusterra;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.mortuusterra.commands.AdminCommands;
import com.mortuusterra.listeners.GeckPowerListener;
import com.mortuusterra.listeners.GeneratorListener;
import com.mortuusterra.listeners.PlayerListener;
import com.mortuusterra.listeners.SupplyDropListener;
import com.mortuusterra.listeners.WorldListener;
import com.mortuusterra.listeners.mob.MobListener;
import com.mortuusterra.managers.DataManager;
import com.mortuusterra.managers.GeckManager;
import com.mortuusterra.managers.GeckObjectManager;
import com.mortuusterra.managers.GeneratorManager;
import com.mortuusterra.managers.MobManager;
import com.mortuusterra.managers.PlayerManager;
import com.mortuusterra.managers.RadiationManager;
import com.mortuusterra.managers.RecipeManager;
import com.mortuusterra.managers.SupplyDropManager;
import com.mortuusterra.objects.CustomScoreboards;
import com.mortuusterra.utils.files.FileManager;
import com.mortuusterra.utils.nmsentities.CustomEntityType;
import com.mortuusterra.utils.others.StringUtilities;
import com.mortuusterra.utils.others.SupplyDropTimer;

public class MortuusTerraCore extends JavaPlugin {
	/*
	 * List of contributors 
	 * Kadeska23 
	 * Shyos 
	 * Horsey 
	 * Andrewbow159
	 */

	/*
	 * Use getCore() to get this main class and all of its public methods. 
	 * 
	 * DO NOT ACCESS BEFORE ONENABLE
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
	private DataManager dataManager;
	private SupplyDropTimer supplyDropTimer;
	private GeneratorManager generatorManager;

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

		// register/initiate Commands
		getCommand("supplydrop").setExecutor(new AdminCommands(this));

		// Load files
		fileManager = new FileManager(this);
		getFileManager().loadFiles();
		
		// start radiation
		getRadiationManager().startPlayerRadiationDamage();
	
		// Start supplydrops for enabled worlds
		for (World world : getDataManager().getSupplyDropWorlds()) {
			getSupplyDropTimer().startSupplyDropTimer(world);
		}


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
		recipeManager = new RecipeManager(this);
	}

	private void registerListeners() {
		PluginManager manager = getServer().getPluginManager();

		manager.registerEvents(new SupplyDropListener(this), this);
		manager.registerEvents(new PlayerListener(this), this);
		manager.registerEvents(new MobListener(this), this);
		manager.registerEvents(new GeckPowerListener(this), this);
		manager.registerEvents(new WorldListener(this), this);
		manager.registerEvents(genListener = new GeneratorListener(this), this);
	}

	private void initiateManagers() {
		playerMan = new PlayerManager(this);
		geckObjectManager = new GeckObjectManager(this);
		geckManager = new GeckManager(this);
		radMan = new RadiationManager(this);
		mobManager = new MobManager(this);
		supplyDropManager = new SupplyDropManager(this);
		scoreboards = new CustomScoreboards(this);
		dataManager = new DataManager(this);
		supplyDropTimer = new SupplyDropTimer(this);
		generatorManager = new GeneratorManager(this);
	}

	public static MortuusTerraCore getCore() {
		return core;
	}

	public FileManager getFileManager() {
		return fileManager;
	}
	
	public GeneratorManager getGeneratorManager() {
		return generatorManager;
	}

	public SupplyDropTimer getSupplyDropTimer() {
		return supplyDropTimer;
	}
	
	public DataManager getDataManager() {
		return dataManager;
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