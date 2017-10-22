/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.potion.PotionEffectType;

public enum InfectedStates {
	
	/*
	 * Represents the states a player will go through after a while when being infected by a zombie.
	 * Feel free to add/modify the possible effects the player will get in each state.
	 */

	UNEASY(0, PotionEffectType.HUNGER), 
	SICK(1, PotionEffectType.HUNGER, PotionEffectType.SLOW),
	TRANSFORMED(2, PotionEffectType.HUNGER, PotionEffectType.SLOW, PotionEffectType.WITHER, PotionEffectType.POISON);

	private List<PotionEffectType> possibleEffects;
	private final int id;

	InfectedStates(int id, PotionEffectType... effects) {
		this.id = id;
		possibleEffects = new ArrayList<>();
		possibleEffects.addAll(Arrays.asList(effects));
	}

	public static InfectedStates getStateById(int id) {
		for (InfectedStates state : values()) {
			if (state.getId() == id)
				return state;
		}
		return null;
	}

	public static InfectedStates getStateByString(String s) {
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
