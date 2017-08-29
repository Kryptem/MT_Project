package mortuusterra.managers.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class RecipeManager {
	private String CellTowerName = "CellTower";
	private String GeneratorName = "Generator";
	private String PowerOutletName = "PowerOutlet";

	private ItemStack cellTower;
	private ItemStack generator;
	private ItemStack powerOutlet;

	private ItemMeta cellTowerMeta;
	private ItemMeta generatorMeta;
	private ItemMeta powerOutletMeta;

	private ShapedRecipe cellTowerRecipe;
	private ShapedRecipe generatorRecipe;
	private ShapedRecipe powerOutletRecipe;

	// cell tower
	public void setCellTowerRecipe() {
		cellTower = new ItemStack(Material.DIAMOND_BLOCK);

		cellTowerMeta = cellTower.getItemMeta();
		cellTowerMeta.setDisplayName(CellTowerName);
		cellTower.setItemMeta(cellTowerMeta);

		cellTowerRecipe = new ShapedRecipe(cellTower);

		cellTowerRecipe.shape("ddd", "drd", "ddd");
		cellTowerRecipe.setIngredient('d', Material.DIAMOND);
		cellTowerRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(cellTowerRecipe);

	}

	// Generator
	public void setGeneratorRecipe() {
		generator = new ItemStack(Material.IRON_BLOCK);

		generatorMeta = generator.getItemMeta();
		generatorMeta.setDisplayName(GeneratorName);
		generator.setItemMeta(generatorMeta);

		generatorRecipe = new ShapedRecipe(cellTower);
		generatorRecipe.shape("iii", "ici", "iii");
		generatorRecipe.setIngredient('i', Material.IRON_INGOT);
		generatorRecipe.setIngredient('c', Material.COAL);

		Bukkit.getServer().addRecipe(generatorRecipe);
	}

	// Power Outlet
	public void setPowerOutletRecipe() {
		powerOutlet = new ItemStack(Material.WOOL);

		powerOutletMeta = powerOutlet.getItemMeta();
		powerOutletMeta.setDisplayName(PowerOutletName);
		powerOutlet.setItemMeta(powerOutletMeta);

		powerOutletRecipe = new ShapedRecipe(cellTower);
		powerOutletRecipe.shape("www", "wrw", "www");
		powerOutletRecipe.setIngredient('w', Material.WOOL);
		powerOutletRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		Bukkit.getServer().addRecipe(powerOutletRecipe);
	}
}
