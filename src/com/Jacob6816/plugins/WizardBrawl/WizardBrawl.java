package com.Jacob6816.plugins.WizardBrawl;

import me.mike1665.gamemode.easygmSign;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class WizardBrawl extends JavaPlugin {
    @Override
    public void onEnable() {
    	Bukkit.getServer().getPluginManager().registerEvents(new SignManager(), this);
        super.onEnable();
    }
}
