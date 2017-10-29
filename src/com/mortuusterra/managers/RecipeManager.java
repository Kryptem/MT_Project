/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.managers;

import com.mortuusterra.MortuusTerraCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeManager {
	
	private final MortuusTerraCore main = JavaPlugin.getPlugin(MortuusTerraCore.class);
	
	// Added this to remove deprecated code.
	//private final NamespacedKey cellTowerKey = new NamespacedKey(main, "celltower");
	private final NamespacedKey genKey = new NamespacedKey(main, "generator");

	// To prevent repition :)
	public static final String GENERATOR_NAME = ChatColor.RED + "" + ChatColor.BOLD  + "Generator";
	
	private static ItemStack generator;
	
	// Returns the generator item DO NOT MODIFY THIS
	public static ItemStack getGenerator() {
		return generator;
	}

	// Generator
	public void setGeneratorRecipe() {
		generator = new ItemStack(Material.FURNACE);

		ItemMeta generatorMeta = generator.getItemMeta();
		generatorMeta.setDisplayName(GENERATOR_NAME);
		generator.setItemMeta(generatorMeta);

		ShapedRecipe generatorRecipe = new ShapedRecipe(genKey, generator);


		generatorRecipe.shape("iii", "ici", "iii");
		generatorRecipe.setIngredient('i', Material.IRON_INGOT);
		generatorRecipe.setIngredient('c', Material.COAL);

		Bukkit.getServer().addRecipe(generatorRecipe);
	}
}
