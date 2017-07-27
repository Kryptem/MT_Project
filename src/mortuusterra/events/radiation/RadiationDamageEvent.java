package mortuusterra.events.radiation;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.Main;

public final class RadiationDamageEvent extends Event implements Cancellable {
	Main main = JavaPlugin.getPlugin(Main.class);

	private static final HandlerList handlers = new HandlerList();
	private boolean isCancelled = false;

	public RadiationDamageEvent(Player p) {
		if(main.getPlayerManager().containsRadPlayer(p.getUniqueId().toString())) {
			
		}
		if (this.isCancelled) {
			return;
		} else {
			if (main.getGeckPowerListener().isIncorrect() == false && main.getGeckPowerListener().isPowered()) {
				this.setCancelled(true);
			} else {
				main.getRadiationManager().CheckEachPlayerLocation();
			}
		}
	}

	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		isCancelled = cancelled;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
