package com.mortuusterra.utils.others;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

public class WorldUtils {

	/**
	 * Util method to return all blocks in a cubic area around the block
	 * 
	 * @param generator
	 *            the block in the center.
	 * @param radius
	 *            The size from a corner to the center
	 * @return List of blocks around the gen
	 */
	public static List<Block> getNearbyBlocks(Block generator, int radius) {

		List<Block> blocks = new ArrayList<>();

		for (int x = -(radius); x <= radius; x++) {
			for (int y = -(radius); y <= radius; y++) {
				for (int z = -(radius); z <= radius; z++) {
					blocks.add(generator.getRelative(x, y, z));
				}
			}
		}
		return blocks;
	}
}
