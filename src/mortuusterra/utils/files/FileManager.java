package mortuusterra.utils.files;

import mortuusterra.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileManager {

    FileManager fileManager = new FileManager();
    private Main main = JavaPlugin.getPlugin(Main.class);
    private YamlConfiguration yamlFile;
    private HashMap<String, PluginFile> pluginFiles = new HashMap<>();
    private HashMap<PluginFile, File> files = new HashMap<>();

    public HashMap<PluginFile, File> getFiles() {
        return files;
    }

    HashMap<String, PluginFile> getPluginFiles() {
        return pluginFiles;
    }

    /**
     * Create PluginFile Method
     *
     * @param name - The name of the file that you are creating.
     * @param type - The extension of the file you are creating.
     */

    // Obviously creates a PluginFile. ex: createPluginFile("config", FileType.YAML);
    public void createPluginFile(String name, FileType type) {
        switch (type) {
            case YAML:
                if (!main.getDataFolder().exists()) {
                    main.getDataFolder().mkdir();
                }

                File file = new File(main.getDataFolder(), name + ".yml");

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

                yamlFile = YamlConfiguration.loadConfiguration(file);
                break;
            case JSON:
                try {
                    File jsonFile = new File(name + ".json)");

                    if (!jsonFile.exists()) {
                        jsonFile.createNewFile();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Could not create file!");
                }
                break;
            case TEXT:
                try {
                    File textFile = new File(name + ".txt");

                    if (!textFile.exists()) {
                        textFile.createNewFile();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    main.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to create file!");
                }
                break;
        }
    }

}
