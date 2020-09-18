package com.highstepmc.scratch;

import com.highstepmc.scratch.CustomItems.Buster;
import com.highstepmc.scratch.CustomItems.Reaper;
import com.highstepmc.scratch.Util.CustomItemManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.highstepmc.scratch.Listener.EventListener;

public class main extends JavaPlugin {

	private CustomItemManager customItemManager;

	@Override
	public void onLoad() {
		init();
	}

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new EventListener(this),
				this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public void init(){
		this.customItemManager = new CustomItemManager();
		this.customItemManager.register(new Buster());
		this.customItemManager.register(new Reaper());
	}

	public static main getLib(){
		return get("HighStepMC-Scratch");
	}

	public static main get(String name){
		return (main) Bukkit.getPluginManager().getPlugin(name);
	}

	public CustomItemManager getCustomItemManager() {
		return customItemManager;
	}
}
