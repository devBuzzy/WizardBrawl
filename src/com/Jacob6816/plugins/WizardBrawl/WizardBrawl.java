package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class WizardBrawl extends JavaPlugin {
    @Override
    public void onEnable() {
        if (getWorldEdit() == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error: WorldEdit is not currently installed! Please install WorldEdit before attempting to use this plugin!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        getCommand("WizardBrawl").setExecutor(new CommandHandler());
        getCommand("WB").setExecutor(new CommandHandler());
        Bukkit.getServer().getPluginManager().registerEvents(new SignManager(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryMenu(), this);
        super.onEnable();
    }
    
    public static WorldEditPlugin getWorldEdit() {
        WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        return wep;
    }
    
    public static final WizardBrawl get() {
        return (WizardBrawl) Bukkit.getPluginManager().getPlugin("WizardBrawl");
    }
}
