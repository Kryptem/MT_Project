package mortuusterra.managers.crafting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class RecipeManager {
	
<<<<<<< HEAD
	private Main main = JavaPlugin.getPlugin(Main.class);
=======
	private final Main main = JavaPlugin.getPlugin(Main.class);
>>>>>>> horsey
	
	// Added this to remove deprecated code.
	//private final NamespacedKey cellTowerKey = new NamespacedKey(main, "celltower");
	private final NamespacedKey genKey = new NamespacedKey(main, "generator");

<<<<<<< HEAD
	// cell tower
=======
	// To prevent repition :)
	public static final String GENERATOR_NAME = ChatColor.RED + "" + ChatColor.BOLD  + "Generator";
	
	private static ItemStack generator;
	
	// Returns an copy of what the generator looks like
	public static ItemStack getGenerator() {
		return generator.clone();
	}


>>>>>>> horsey
	public void setCellTowerRecipe() {
		/**
		ItemStack cellTower = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta cellTowerMeta = cellTower.getItemMeta();
		String cellTowerName = "CellTower";
		cellTowerMeta.setDisplayName(cellTowerName);
		cellTower.setItemMeta(cellTowerMeta);
<<<<<<< HEAD
		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTowerKey, cellTower);
=======

		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTowerKey, cellTower);

		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTowerKey, cellTower);
>>>>>>> horsey
		cellTowerRecipe.shape("ddd", "drd", "ddd");
		cellTowerRecipe.setIngredient('d', Material.DIAMOND);
		cellTowerRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(cellTowerRecipe);
		**/
	}

	// Generator
	public void setGeneratorRecipe() {
<<<<<<< HEAD
=======
		generator = new ItemStack(Material.IRON_BLOCK);

>>>>>>> horsey
		ItemStack generator = new ItemStack(Material.IRON_BLOCK);
		ItemMeta generatorMeta = generator.getItemMeta();
		generatorMeta.setDisplayName(GENERATOR_NAME);
		generator.setItemMeta(generatorMeta);
<<<<<<< HEAD
		ShapedRecipe generatorRecipe = new ShapedRecipe(genKey, generator);
=======

		ShapedRecipe generatorRecipe = new ShapedRecipe(genKey, generator);

>>>>>>> horsey
		generatorRecipe.shape("iii", "ici", "iii");
		generatorRecipe.setIngredient('i', Material.IRON_INGOT);
		generatorRecipe.setIngredient('c', Material.COAL);

		Bukkit.getServer().addRecipe(generatorRecipe);
	}
}
