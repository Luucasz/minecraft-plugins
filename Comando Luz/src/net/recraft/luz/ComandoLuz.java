package net.recraft.luz;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ComandoLuz implements CommandExecutor {
	
	
	private HashMap<Player, Long> luz = new HashMap<>();
	public static Main m = (Main)Bukkit.getPluginManager().getPlugin("IR_Luz");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cEsse comando só pode ser utilizado por jogadores.");
			return false;
		}
		
		Player p = (Player) sender;
		if (!(p.hasPermission("luz.usar"))) {
			p.sendMessage(m.getConfig().getString("mensagem_sem_permissao").replace('&', '§'));
			p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5, 5);
		} else {
			if (cmd.getName().equalsIgnoreCase("luz")) {
				if (luz.containsKey(p) && !(System.currentTimeMillis() >= luz.get(p))) {
					p.sendMessage("§cAguarde " + converter(p) + " para poder utilizar o comando novamente.");
					return false;
				} else luz.remove(p);
				
				luz.put(p, System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(m.getConfig().getInt("Delay")));
				
				if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
					p.removePotionEffect(PotionEffectType.NIGHT_VISION);
					p.sendMessage(m.getConfig().getString("mensagem_luz_desativada").replace('&', '§'));
					p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 10, 10);
				} else {
					p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 9999, 0, false, false));
					p.sendMessage(m.getConfig().getString("mensagem_luz_ativada").replace('&', '§'));
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
				}

			}
		}
		
		return false;
	}
	private Long converter(Player p) {
		long tempo = System.currentTimeMillis() - luz.get (p);
		return TimeUnit.MILLISECONDS.toSeconds(tempo) * -1;
	}
}
