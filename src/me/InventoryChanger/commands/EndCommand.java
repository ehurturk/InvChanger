package me.InventoryChanger.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.InventoryChanger.Main;

public class EndCommand implements CommandExecutor {

	private Main plugin;
	
	public EndCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		p.getServer().broadcastMessage("Stopping InventoryChanger...");
		p.getServer().getScheduler().cancelTasks(this.plugin);
		return false;
	}
}
