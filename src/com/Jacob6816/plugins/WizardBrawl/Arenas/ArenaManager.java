package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class ArenaManager {
    private static ArrayList<Arena> arenas = new ArrayList<Arena>();
    
    private static ArenaManager instance;
    
    private ArenaManager() {
        if (instance == null) instance = this;
    }
    
    public static ArenaManager get() {
        if (instance == null) instance = new ArenaManager();
        return instance;
    }
    
    public Arena getPlayerArena(Player player) {
        if (arenas.isEmpty()) return null;
        for (Arena a : arenas) {
            for (OfflinePlayer p : a.getPlayers()) {
                if (p.getName().equals(player.getName())) return a;
            }
        }
        return null;
    }
    
    public Arena getByName(String name) {
        if (arenas.isEmpty()) return null;
        for (Arena a : arenas) {
            if (a.getName().equalsIgnoreCase(name)) return a;
        }
        return null;
    }
    
    public boolean isInGame(Player player) {
        return getPlayerArena(player) != null;
    }
    
    public Arena[] getAllArenas() {
        return arenas.toArray(new Arena[arenas.size()]);
    }
    
    public boolean createNewArena(String string, Selection selection) {
        Arena a = getByName(string);
        if (a != null) {
            return false;
        }
        else {
            a = new Arena(string, selection);
            arenas.add(a);
            return true;
        }
    }
}
