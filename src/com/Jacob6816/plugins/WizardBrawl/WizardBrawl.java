package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WizardBrawl extends JavaPlugin {
    @Override
    public void onEnable() {
    	Bukkit.getServer().getPluginManager().registerEvents(new SignManager(), this);
    	Bukkit.getServer().getPluginManager().registerEvents(new InventoryMenu(), this);
        super.onEnable();
    }
}
