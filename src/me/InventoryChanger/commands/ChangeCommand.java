package me.InventoryChanger.commands;

import me.InventoryChanger.Main;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

public class ChangeCommand implements CommandExecutor{

	private Main plugin;
	private int task1, task2;
	
	public ChangeCommand(Main _plugin) {
		// TODO Auto-generated constructor stub
		plugin = _plugin;
		plugin.getCommand("ichange").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
		
		Bukkit.broadcastMessage(ChatColor.GREEN + "Plugin is now enabled");
//		send.getServer().broadcastMessage(ChatColor.GREEN + "Plugin is started now...");
		// command would be: /ichange Skipboi PickledEgg0 xattas
		
		Player[] players = new Player[args.length];
		
		for (int i = 0; i < args.length; i++) {
			players[i] = send.getServer().getPlayer(args[i]); // assign each name in args to playerArray
		}
		
		if (cmd.getName().equalsIgnoreCase("ichange")) {
			send.getServer().broadcastMessage("InventoryChange Plugin made by ehurturk");
		}
		else {
			return false;
		}
		
		BukkitScheduler sched = send.getServer().getScheduler();
		
		send.getServer().broadcastMessage("Game is starting...");
		
		task1 = sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			int num = 10;
			
			@Override
			public void run() {
				if (num!=0) {
					send.getServer().broadcastMessage("Game starting in: "+ num--);
				}
				else {
					send.getServer().broadcastMessage("Go!");
					Bukkit.getScheduler().cancelTask(task1);				
				}
			}}, 20L, 20L);
		
		sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			@Override
			public void run() {

				task2 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
					int num = 10;

					@Override
					public void run() {
						if (num!=0) {
							send.getServer().broadcastMessage("Changing inventory in: "+ num--);
						}
						else {
							int[] rNums = generateUniqueNumbers(args.length);

							for (int i = 0; i < rNums.length; i++) {
								// get random players armor and extra contents

								ItemStack[] armor = players[rNums[i]].getInventory().getArmorContents();
								ItemStack[] extraContents = players[rNums[i]].getInventory().getExtraContents();

								// swap each player's inventory with random player's inventory.
								players[i].getInventory().setArmorContents(armor);
								players[i].getInventory().setExtraContents(extraContents);
							}

							Bukkit.getScheduler().cancelTask(task2); //close the task
						}
					}}, 20L, 20L);
			}
		}, 6020L, 6000L);
		
		

		return true;
	}
	
	public int[] generateUniqueNumbers(int size) {
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int[] shuffledList = new int[size];
		for (int i = 0; i < size; i++) {
			indices.add(i);
		}
		Collections.shuffle(indices);
		
		for (int j = 0; j<size; j++) {
			shuffledList[j] = indices.get(j);
		}
		return shuffledList;
	}

}
