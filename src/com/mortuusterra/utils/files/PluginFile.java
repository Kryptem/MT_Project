package com.mortuusterra.utils.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import com.mortuusterra.MortuusTerraCore;

public class PluginFile {

	// Unsure how this was meant to be, changed so that it doesn't throw StackOverflows
    private MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);

    private String name;
    private File file;

    /**
     * Constructor
     * @param name name of the file
     * @param type type of the file
     */
    public PluginFile(String name, FileType type) {
        file = FileManager.createPluginFile(name, type);
        this.name = name;

    }

    /**
     * returns the file bound to this object
     * @return file
     */
    public File getFile() {
        return file;
    }

    /** 
     * Sets the file bound to this object
     * @param file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * Save Method (Only for YAML)
     *
     */

    public void save(YamlConfiguration yamlConfiguration) {
        try {
            yamlConfiguration.save(file);
            main.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully saved file " + name + "!");
        } catch (IOException e) {
            e.printStackTrace();
            main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to save file " + name + "!");
        }
    }

    /**
     * Write Method for JSON files
     *
     * @param jsonObject The JSONObject to write
     */

    public void write(JSONObject jsonObject) {
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "File could not be written to!");
        }
    }

    /**
     * Write Method for Text files
     *
     * @param text The text to write
     */
    public void write(String text) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "File could not be written to!");
        }
    }

    /**
     * Returns the file as a YAML
     *
     * @return the file as yaml
     */
    public YamlConfiguration returnYaml(){
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Gets the name of the file
     * @return name of the file
     */
	public String getName() {
		return name;
	}

	/**
	 * Deletes the file bound to this object; clears all variables. 
	 */
	public void dispose() {
		file.delete();
		file = null;
		main = null;
		name = null;
	}
}