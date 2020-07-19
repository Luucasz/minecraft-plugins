package net.recraft.fly;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		getCommand("fly").setExecutor(new ComandoFly());
		
	}
	
	@Override
	public void onDisable() {
	}
	
}
