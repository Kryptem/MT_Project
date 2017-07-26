package mortuusterra.managers.crafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public class CellTowerRecipe {
	Main main = JavaPlugin.getPlugin(Main.class);
	private String name = "CellTower";

	@SuppressWarnings("deprecation")
	public void setRecipe() {
		ItemStack cellTower = new ItemStack(Material.DIAMOND_BLOCK);
		
		ItemMeta meta = cellTower.getItemMeta();
		meta.setDisplayName(name);
		cellTower.setItemMeta(meta);
		
		ShapedRecipe cellTowerRecipe = new ShapedRecipe(cellTower);

		cellTowerRecipe.shape("ddd", "drd", "ddd");
		cellTowerRecipe.setIngredient('d', Material.DIAMOND);
		cellTowerRecipe.setIngredient('r', Material.REDSTONE_BLOCK);

		main.getServer().addRecipe(cellTowerRecipe);

	}
}
