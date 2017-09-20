package mortuusterra.managers.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class RecipeManager {
	
	private Main main = JavaPlugin.getPlugin(Main.class);
	
	// Added this to remove deprecated code.
	//private final NamespacedKey cellTowerKey = new NamespacedKey(main, "celltower");
	private final NamespacedKey genKey = new NamespacedKey(main, "generator");

	// cell tower
	public void setCellTowerRecipe() {
		/**
		ItemStack cellTower = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta cellTowerMeta = cellTower.getItemMeta();
		String cellTowerName = "CellTower";
		cellTowerMeta.setDisplayName(cellTowerName);
		cellTower.setItemMeta(cellTowerMeta);
		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTowerKey, cellTower);
		cellTowerRecipe.shape("ddd", "drd", "ddd");
		cellTowerRecipe.setIngredient('d', Material.DIAMOND);
		cellTowerRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(cellTowerRecipe);
		**/
	}

	// Generator
	public void setGeneratorRecipe() {
		ItemStack generator = new ItemStack(Material.IRON_BLOCK);
		ItemMeta generatorMeta = generator.getItemMeta();
		String generatorName = "Generator";
		generatorMeta.setDisplayName(generatorName);
		generator.setItemMeta(generatorMeta);
		ShapedRecipe generatorRecipe = new ShapedRecipe(genKey, generator);
		generatorRecipe.shape("iii", "ici", "iii");
		generatorRecipe.setIngredient('i', Material.IRON_INGOT);
		generatorRecipe.setIngredient('c', Material.COAL);

		Bukkit.getServer().addRecipe(generatorRecipe);
	}
}
