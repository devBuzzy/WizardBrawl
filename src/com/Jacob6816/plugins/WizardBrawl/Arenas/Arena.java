package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Arena {
    public enum State {
        DISABLED, LOADING, LOBBY, STARTING, INGAME, ENDING;
    }
    
    private State state;
    private HashSet<OfflinePlayer> players;
    private HashMap<OfflinePlayer, DataBackup> backups;
    private String name;
    private Location lobby, blue, red;
    
    protected Arena(String name, Location lobby, Location redTeam, Location blueTeam) {
        players = new HashSet<OfflinePlayer>();
        backups = new HashMap<OfflinePlayer, DataBackup>();
        this.name = name;
        this.lobby = lobby;
        blue = blueTeam;
        red = redTeam;
        state = State.LOADING;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLobby() {
        return lobby;
    }
    
    public void setLobby(Location location) {
        lobby = location;
    }
    
    public Location getRedSpawn() {
        return red;
    }
    
    public void setRedSpawn(Location location) {
        red = location;
    }
    
    public Location getBlueSpawn() {
        return blue;
    }
    
    public void setBlueSpawn(Location location) {
        blue = location;
    }
    
    public OfflinePlayer[] getPlayers() {
        return players.toArray(new OfflinePlayer[players.size()]);
    }
    
    public int getPlayerCount() {
        return players.size();
    }
    
    public void addPlayer(Player player) {
        if (state == State.LOBBY) {
            players.add(player);
            player.getPlayer().teleport(getLobby());
            backups.put(player, new DataBackup(player));
        }
    }
    
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            backups.get(player).restorePlayer();
            backups.remove(player);
            players.remove(player);
        }
    }
    
    private class DataBackup {
        private ItemStack[] inventory, armor;
        private float exp;
        
        private DataBackup(Player player) {
            createBackup();
        }
        
        public void restorePlayer() {
            
        }
        
        public void createBackup() {
            
        }
    }
}
