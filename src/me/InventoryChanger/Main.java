package me.InventoryChanger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.InventoryChanger.commands.ChangeCommand;
import me.InventoryChanger.commands.EndCommand;
 

public class Main extends JavaPlugin{
	
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[InvChanger] Plugin is enabled..." );
		new ChangeCommand(this);
		new EndCommand(this);
	}
	
}
