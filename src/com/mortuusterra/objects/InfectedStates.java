package com.mortuusterra.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

public enum InfectedStates {

	UNEASY(0, PotionEffectType.HUNGER), 
	SICK(1, PotionEffectType.HUNGER, PotionEffectType.SLOW),
	TRANSFORMED(2, PotionEffectType.HUNGER, PotionEffectType.SLOW, PotionEffectType.CONFUSION, PotionEffectType.POISON);

	private List<PotionEffectType> possibleEffects;
	private final int id;

	InfectedStates(int id, PotionEffectType... effects) {
		this.id = id;
		possibleEffects = new ArrayList<>();
		possibleEffects.addAll(Arrays.asList(effects));
	}

	public InfectedStates getStateById(int id) {
		for (InfectedStates state : values()) {
			if (state.getId() == id)
				return state;
		}
		return null;
	}

	public InfectedStates getStateByString(String s) {
		for (InfectedStates state : values()) {
			if (state.toString().equalsIgnoreCase(s))
				return state;
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public List<PotionEffectType> getPossibleEffects() {
		return possibleEffects;
	}

}
