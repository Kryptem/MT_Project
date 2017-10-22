package com.mortuusterra.objects;

import org.bukkit.Material;

public class SupplyDropContent {
	
	private Material itemMaterial;
	private int itemChance;
	private int itemAmount;
	
	public SupplyDropContent(Material itemMaterial, int itemChance, int itemAmount) {
		this.itemAmount = itemAmount;
		this.itemChance = itemChance;
		this.itemMaterial = itemMaterial;
	}
	
	public Material getItemMaterial() {
		return itemMaterial;
	}
	
	public int getItemAmount() {
		return itemAmount;
	}
	
	public int getItemChance() {
		return itemChance;
	}
	

}
