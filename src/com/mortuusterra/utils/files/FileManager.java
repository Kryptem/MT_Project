/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.utils.files;

import com.mortuusterra.MortuusTerraCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class FileManager {

	// Not exactly sure what this class was for; removed weird variables that
	// would've thrown StackOverflows
	private static MortuusTerraCore core = JavaPlugin.getPlugin(MortuusTerraCore.class);

	/**
	 * Create PluginFile Method
	 *
	 * @param name
	 *            - The name of the file that you are creating.
	 * @param type
	 *            - The extension of the file you are creating.
	 */

	// Obviously creates a PluginFile. ex: createPluginFile("config",
	// FileType.YAML);
	public static File createPluginFile(String name, FileType type) {
		switch (type) {
		case YAML:
			if (!core.getDataFolder().exists()) {
				core.getDataFolder().mkdir();
			}

			File file = new File(core.getDataFolder(), name + ".yml");

			if (!file.exists()) {
				try {
					file.createNewFile();
					Bukkit.getServer().getConsoleSender()
							.sendMessage(ChatColor.GREEN + "The " + name + ".yml file has been created");
				} catch (IOException e) {
					e.printStackTrace();
					Bukkit.getServer().getConsoleSender()
							.sendMessage(ChatColor.RED + "Could not create the " + name + ".yml file");
				}
			}

			return file;
		case JSON:
			File jsonFile = new File(name + ".json");
			try {

				if (!jsonFile.exists()) {
					jsonFile.createNewFile();
				}

			} catch (IOException e) {
				e.printStackTrace();
				core.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create file!");
			}
			return jsonFile;
		case TEXT:
			File textFile = new File(name + ".txt");
			try {

				if (!textFile.exists()) {
					textFile.createNewFile();
				}

			} catch (IOException e) {
				e.printStackTrace();
				core.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to create file!");
			}
			return textFile;
		default:
			throw new IllegalArgumentException("Unknown FileType");
		}
	}

	public void saveFiles() {
		core.getPlayerManager().savePlayersToDisk();
		core.getGeckObjectManager().saveGecksToDisk();
		core.getGenListener().saveFile();
		core.getSupplyDropManager().saveSupplyData();
	}

	public void loadFiles() {
		core.getPlayerManager().loadPlayersFromDisk();
		core.getGeckObjectManager().loadGecksFromDisk();
		core.getGenListener().loadFile();
		core.getSupplyDropManager().loadSupplyData();
		core.getDataManager().loadData();
	}

}
