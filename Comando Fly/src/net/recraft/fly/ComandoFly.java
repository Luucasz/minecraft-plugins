package net.recraft.fly;

import org.bukkit.Bukkit;
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

            if (cmd.getName().equalsIgnoreCase("fly")) { // comando /fly
                if (args.length == 0) {
                    if (p.getAllowFlight()) {
                        p.setAllowFlight(false);
                        p.sendMessage(ChatColor.RED + "Seu fly foi desativado.");
                        p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10, 10);
                    } else {
                        p.setAllowFlight(true);
                        p.sendMessage(ChatColor.GREEN + "Seu fly foi ativado.");
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
                        return true;
                    }
                }

                if (args.length == 1) {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if (target == null) {
                        p.sendMessage(ChatColor.RED + "Este jogador não existe, ou ele está offline.");
                        return true;
                    } else {
                        if (target.getAllowFlight()) {
                            target.setAllowFlight(false);
                            p.sendMessage(ChatColor.RED + "O fly do player " + target.getName() + " foi desativado.");
                            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10, 10);
                        } else {
                            target.setAllowFlight(true);
                            p.sendMessage(ChatColor.GREEN + "O fly do player " + target.getName() + " foi ativado.");
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 5, 1);
                            return true;
                        }
                    }
                }

            }

        }
            return false;
    }
}

