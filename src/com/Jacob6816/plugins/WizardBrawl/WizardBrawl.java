package com.Jacob6816.plugins.WizardBrawl;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena.State;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
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
        File f = new File(getDataFolder(), "Maps");
        f.mkdirs();
        if (f.listFiles().length >= 1) {
            for (File file : f.listFiles()) {
                ArenaManager.get().loadArena(file.getName().substring(0, file.getName().lastIndexOf(".")));
            }
        }
        super.onEnable();
    }
    
    public static WorldEditPlugin getWorldEdit() {
        WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        return wep;
    }
    
    public static final WizardBrawl get() {
        return (WizardBrawl) Bukkit.getPluginManager().getPlugin("WizardBrawl");
    }
    
    @Override
    public void onDisable() {
        for (Arena a : ArenaManager.get().getAllArenas()) {
            a.endGame();
            a.setState(State.DISABLED);
            a.getHelper().save();
        }
        HandlerList.unregisterAll(this);
        super.onDisable();
    }
}
