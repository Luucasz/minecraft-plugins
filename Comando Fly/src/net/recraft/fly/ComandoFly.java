package net.recraft.fly;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ComandoFly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Este comando só pode ser utilizado por jogadores.");
			return true;
		}

		Player p = (Player) sender;

		if (!p.hasPermission("fly.usar")) {
			p.sendMessage(ChatColor.RED + "Você não tem permissão para utilizar este comando.");
		} else {

			if (p.getAllowFlight()) {
				p.setAllowFlight(false);
				p.sendMessage(ChatColor.RED + "O seu fly foi desativado!");
				p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 5, 10);
			} else {
				p.setAllowFlight(true);
				p.sendMessage(ChatColor.GREEN + "Yay! O seu fly foi ativado!");
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
			}
		}

		return false;
	}

}

