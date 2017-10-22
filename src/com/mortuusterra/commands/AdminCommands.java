package com.mortuusterra.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mortuusterra.MortuusTerraCore;

public class AdminCommands implements CommandExecutor {

	private MortuusTerraCore main;
	
	public AdminCommands(MortuusTerraCore main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Admin commands are currently only available from ingame.");
			return true;
		}

		Player p = (Player) sender;

		if (!p.isOp())
			return true;
		
		if (cmd.getName().equalsIgnoreCase("supplydrop")) {
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("drop")) {
					main.getSupplyDropManager().deliverSupplyDrop(p.getWorld());
				}
			}
		}

		return true;
	}

}
