package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.Jacob6816.plugins.WizardBrawl.Misc.ConfigHelper;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Arena {
    public enum State {
        DISABLED, LOADING, LOBBY, GRACE, INGAME, ENDING;
    }
    
    private Selection region;
    private List<Integer> ticks = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30, 45);
    private int timeRemaining = 0;
    private State state;
    private HashSet<Player> players, redTeam, blueTeam;
    private HashMap<Player, DataBackup> backups;
    private String name;
    private ConfigHelper config;
    private Location lobby, blue, red;
    
    protected Arena(String name, Location lobby, Location redTeam, Location blueTeam) {
        players = new HashSet<Player>();
        backups = new HashMap<Player, DataBackup>();
        this.name = name;
        this.lobby = lobby;
        blue = blueTeam;
        red = redTeam;
        state = State.LOADING;
        config = new ConfigHelper(name, true);
        region = new CuboidSelection(config.getHighestPoint().getWorld(), config.getHighestPoint(), config.getLowestPoint());
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
    
    public Player[] getPlayers() {
        return players.toArray(new Player[players.size()]);
    }
    
    public int getPlayerCount() {
        return players.size();
    }
    
    public void addPlayer(Player player) {
        players.add(player);
        player.getPlayer().teleport(getLobby());
        backups.put(player, new DataBackup(player));
    }
    
    public void removePlayer(Player player) {
        if (players.contains(player)) {
            backups.get(player).restorePlayer();
            backups.remove(player);
            players.remove(player);
        }
    }
    
    public String[] getIndepthDescription() {
        ArrayList<String> l = new ArrayList<String>();
        l.add(ChatColor.YELLOW + "Ingame: " + getPlayerCount() + " / 24");
        l.add(ChatColor.YELLOW + "Map name: " + getName());
        l.add(ChatColor.YELLOW + "Lobby world: " + getLobby().getWorld().getName());
        return l.toArray(new String[l.size()]);
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void stateGame() {
        redTeam = new HashSet<Player>();
        blueTeam = new HashSet<Player>();
        int side = 0;
        for (Player p : getPlayers()) {
            if (side == 1) {
                side = 0;
                p.teleport(getBlueSpawn());
                blueTeam.add(p);
                continue;
            }
            else {
                side = 1;
                p.teleport(getRedSpawn());
                redTeam.add(p);
                continue;
            }
        }
        state = State.GRACE;
    }
    
    public void endGame() {
        state = State.ENDING;
        for (Player p : getPlayers()) {
            backups.get(p).restorePlayer();
            backups.clear();
            redTeam.clear();
            players.clear();
            blueTeam.clear();
        }
        timeRemaining = 0;
        state = State.LOBBY;
    }
    
    public void doTick() {
        if (state == State.GRACE) {
            timeRemaining--;
            for (Player p : getPlayers()) {
                p.setLevel(timeRemaining);
            }
            if (timeRemaining <= 0) {
                state = State.INGAME;
                timeRemaining = 5 * 60;
                return;
            }
        }
        else if (state == state.INGAME) {
            timeRemaining--;
            for (Player p : getPlayers()) {
                p.setLevel(timeRemaining);
            }
            if (timeRemaining <= 0) {
                endGame();
                return;
            }
            else {
                if (timeRemaining % 60 == 0) {
                    // TODO breadcast remaining minutes!
                }
                return;
            }
        }
    }
    
    public boolean isInsideArena(Player player) {
        final Location c = player.getLocation();
        final Location l = config.getLowestPoint();
        if (!c.getWorld().equals(l.getWorld())) return false;
        if (Double.doubleToLongBits(c.getX()) < Double.doubleToLongBits(l.getX())) return false;
        if (Double.doubleToLongBits(c.getY()) < Double.doubleToLongBits(l.getY())) return false;
        if (Double.doubleToLongBits(c.getZ()) < Double.doubleToLongBits(l.getZ())) return false;
        final Location h = config.getHighestPoint();
        if (!c.getWorld().equals(h.getWorld())) return false;
        if (Double.doubleToLongBits(c.getX()) > Double.doubleToLongBits(h.getX())) return false;
        if (Double.doubleToLongBits(c.getY()) > Double.doubleToLongBits(h.getY())) return false;
        if (Double.doubleToLongBits(c.getZ()) > Double.doubleToLongBits(h.getZ())) return false;
        return true;
    }
    
    public boolean locationIsInsideArena(Location location) {
        final Location c = location;
        final Location l = config.getLowestPoint();
        if (!c.getWorld().equals(l.getWorld())) return false;
        if (Double.doubleToLongBits(c.getX()) < Double.doubleToLongBits(l.getX())) return false;
        if (Double.doubleToLongBits(c.getY()) < Double.doubleToLongBits(l.getY())) return false;
        if (Double.doubleToLongBits(c.getZ()) < Double.doubleToLongBits(l.getZ())) return false;
        final Location h = config.getHighestPoint();
        if (!c.getWorld().equals(h.getWorld())) return false;
        if (Double.doubleToLongBits(c.getX()) > Double.doubleToLongBits(h.getX())) return false;
        if (Double.doubleToLongBits(c.getY()) > Double.doubleToLongBits(h.getY())) return false;
        if (Double.doubleToLongBits(c.getZ()) > Double.doubleToLongBits(h.getZ())) return false;
        return true;
    }
    
    public Location getTeamSpawn(Player player) {
        if (redTeam.contains(player)) return getRedSpawn();
        if (blueTeam.contains(player)) return getBlueSpawn();
        return null;
    }
    
    public boolean isJoinable() {
        return state == State.LOBBY && getPlayerCount() < 24;
    }
    
    public Selection getSelection() {
        return region;
    }
    
    public Integer[] setTicks() {
        return ticks.toArray(new Integer[ticks.size()]);
    }
    
    /**
     * @author JRL1004
     */
    
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
