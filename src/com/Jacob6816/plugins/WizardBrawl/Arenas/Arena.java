package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.util.HashSet;

import org.bukkit.OfflinePlayer;

public class Arena {
    private HashSet<OfflinePlayer> players;
    private String name;
    
    protected Arena(String name) {
        players = new HashSet<OfflinePlayer>();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public OfflinePlayer[] getPlayers() {
        return players.toArray(new OfflinePlayer[players.size()]);
    }
}
