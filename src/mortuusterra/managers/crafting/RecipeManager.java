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
	
	/*
	 * Why are we adding new recipes for the same item (celltower)??
	 */
	
	// Added this to remove deprecated code.
<<<<<<< HEAD
	private Main main = JavaPlugin.getPlugin(Main.class);
	private final NamespacedKey cellTowerKey = new NamespacedKey(main, "celltower");
	private final NamespacedKey genKey = new NamespacedKey(main, "generator");
	private final NamespacedKey outletKey = new NamespacedKey(main, "poweroutlet");
=======
	NamespacedKey CellTower = new NamespacedKey(JavaPlugin.getPlugin(Main.class), "CellTower");
	NamespacedKey Generator = new NamespacedKey(JavaPlugin.getPlugin(Main.class), "Generator");
	NamespacedKey PowerOutlet = new NamespacedKey(JavaPlugin.getPlugin(Main.class), "PowerOutlet");

>>>>>>> master
	// cell tower
	public void setCellTowerRecipe() {
		ItemStack cellTower = new ItemStack(Material.DIAMOND_BLOCK);

		ItemMeta cellTowerMeta = cellTower.getItemMeta();
		String cellTowerName = "CellTower";
		cellTowerMeta.setDisplayName(cellTowerName);
		cellTower.setItemMeta(cellTowerMeta);

<<<<<<< HEAD
		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTowerKey, cellTower);
=======
		ShapedRecipe cellTowerRecipe = new ShapedRecipe(CellTower, cellTower); 
>>>>>>> master

		cellTowerRecipe.shape("ddd", "drd", "ddd");
		cellTowerRecipe.setIngredient('d', Material.DIAMOND);
		cellTowerRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(cellTowerRecipe);

	}

	// Generator
	public void setGeneratorRecipe() {
		ItemStack generator = new ItemStack(Material.IRON_BLOCK);

		ItemMeta generatorMeta = generator.getItemMeta();
		String generatorName = "Generator";
		generatorMeta.setDisplayName(generatorName);
		generator.setItemMeta(generatorMeta);

<<<<<<< HEAD
		ShapedRecipe generatorRecipe = new ShapedRecipe(genKey, generator);
=======
		ShapedRecipe generatorRecipe = new ShapedRecipe(Generator, generator);
>>>>>>> master
		generatorRecipe.shape("iii", "ici", "iii");
		generatorRecipe.setIngredient('i', Material.IRON_INGOT);
		generatorRecipe.setIngredient('c', Material.COAL);

		Bukkit.getServer().addRecipe(generatorRecipe);
	}

	// Power Outlet
	public void setPowerOutletRecipe() {
		ItemStack powerOutlet = new ItemStack(Material.WOOL);
		ItemMeta powerOutletMeta = powerOutlet.getItemMeta();
		String powerOutletName = "PowerOutlet";
		powerOutletMeta.setDisplayName(powerOutletName);
		powerOutlet.setItemMeta(powerOutletMeta);

<<<<<<< HEAD
		ShapedRecipe powerOutletRecipe = new ShapedRecipe(outletKey, powerOutlet);
=======
		ShapedRecipe powerOutletRecipe = new ShapedRecipe(PowerOutlet, powerOutlet);
>>>>>>> master
		powerOutletRecipe.shape("www", "wrw", "www");
		powerOutletRecipe.setIngredient('w', Material.WOOL);
		powerOutletRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(powerOutletRecipe);
	}
}
