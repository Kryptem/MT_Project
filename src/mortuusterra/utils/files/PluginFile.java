package mortuusterra.utils.files;

import mortuusterra.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PluginFile {

    private Main main = JavaPlugin.getPlugin(Main.class);

    private FileManager fileManager;

    private String name;
    private PluginFile file;

    public PluginFile(String name, FileType type) {
        fileManager = new FileManager();
        file = fileManager.getPluginFiles().get(name);
        this.name = name;

    }

    public PluginFile getFile() {
        return file;
    }

    public void setFile(PluginFile file) {
        this.file = file;
    }

    /**
     * Save Method (Only for YAML)
     *
     * @param file              The file to save
     * @param yamlConfiguration The YamlConfiguration to save
     */

    public void save(File file, YamlConfiguration yamlConfiguration) {
        try {
            yamlConfiguration.save(file);
            main.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully saved file!");
        } catch (IOException e) {
            e.printStackTrace();
            main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to save file!");
        }
    }

    /**
     * Write Method for JSON files
     *
     * @param file       The file to write to
     * @param jsonObject The JSONObject to write
     */

    public void write(File file, JSONObject jsonObject) {
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
     * @param file The file to write to
     * @param text The text to write
     */
    public void write(File file, String text) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(text);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "File could not be written to!");
        }
    }
}
