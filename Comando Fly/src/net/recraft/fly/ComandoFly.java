package net.recraft.fly;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ComandoFly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		Player p = (Player) sender;
		
		if (p.getAllowFlight()) {
			p.setAllowFlight(false);
			p.sendMessage(ChatColor.RED + "O seu fly foi desativado!");
		} else {
			p.setAllowFlight(true);
			p.sendMessage(ChatColor.GREEN + "Yay! O seu fly foi ativado!"); 
		}
		
		return false;
	}
	
	

}
