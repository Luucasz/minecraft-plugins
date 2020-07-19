package net.recraft.luz;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getCommand("luz").setExecutor(new ComandoLuz());
		saveDefaultConfig();

	}

	@Override
	public void onDisable() {
	}
}
