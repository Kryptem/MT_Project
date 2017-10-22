/*
 * Copyright (C) 2017 Mortuss Terra Team
 * You should have received a copy of the GNU General Public License along with this program. 
 * If not, see https://github.com/kadeska/MT_Core/blob/master/LICENSE.
 */
package com.mortuusterra.events.block;

import org.bukkit.event.Listener;

public class CellTowerBlockEvent implements Listener {
	
	/**
	 * CellTowers are disabled for now. 
	 * 
	 * 
	Main main = JavaPlugin.getPlugin(Main.class);

	private Location blockLocation;

	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent e) {
		if (e.getItemInHand().getItemMeta().getDisplayName() == "CellTower") {
			this.blockLocation = e.getBlock().getLocation();
			main.getCellTowerManager().addCellTowerLocations(blockLocation);
		}
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {
		if(main.getCellTowerManager().containsCellTowerLocations(e.getBlock().getLocation())) {
			main.getCellTowerManager().removeCellTowerLocations(e.getBlock().getLocation());
		}
	}
	**/
}