package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import com.Jacob6816.plugins.WizardBrawl.Misc.ConfigHelper;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Arena {
    public enum State {
        DISABLED, LOADING, LOBBY, GRACE, INGAME, ENDING;
    }
    
    private Selection region;
    private List<Integer> ticks = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 30, 45);
    private int timeRemaining = 0, maxplayers = 24;
    private State state;
    private HashSet<Player> players, redTeam, blueTeam;
    private HashMap<Player, DataBackup> backups;
    private String name;
    private ConfigHelper config;
    private Location lobby, blue, red;
    
    protected Arena(String name, Selection region) {
        state = State.LOADING;
        players = new HashSet<Player>();
        backups = new HashMap<Player, DataBackup>();
        this.name = name;
        config = new ConfigHelper(name, true);
        this.region = region;
        config.setMaxPlayers(24);
        config.setHighestPoint(region.getMaximumPoint());
        config.setLowestPoint(region.getMinimumPoint());
        config.save();
    }
    
    public ConfigHelper getHelper() {
        return config;
    }
    
    public String getName() {
        return name;
    }
    
    public Location getLobby() {
        return lobby;
    }
    
    public void setLobby(Location location) {
        lobby = location;
        config.setLobbySpawn(location);
        config.save();
    }
    
    public Location getRedSpawn() {
        return red;
    }
    
    public void setRedSpawn(Location location) {
        red = location;
        config.setRedSpawn(location);
        config.save();
    }
    
    public Location getBlueSpawn() {
        return blue;
    }
    
    public void setBlueSpawn(Location location) {
        blue = location;
        config.setBlueSpawn(location);
        config.save();
    }
    
    public Player[] getPlayers() {
        return players.toArray(new Player[players.size()]);
    }
    
    public int getPlayerCount() {
        return players.size();
    }
    
    public void addPlayer(Player player) {
        players.add(player);
        backups.put(player, new DataBackup(player));
        player.getPlayer().teleport(getLobby());
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
        else if (state == State.INGAME) {
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
    
    public int getMaxPlayers() {
        return maxplayers;
    }
    
    public void setMaxPlayers(int max) {
        this.maxplayers = max;
        config.setMaxPlayers(max);
        config.save();
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
    
    public Integer[] getTicks() {
        return ticks.toArray(new Integer[ticks.size()]);
    }
    
    /**
     * @author JRL1004
     */
    
    private class DataBackup {
        private Player player;
        private ItemStack[] inventory, armor;
        private int totalExp, foodLevel;
        private Collection<PotionEffect> activeEffects;
        private Location bedSpawn, lastLocation, compass;
        private Vector velocity;
        private String customName, displayName;
        private float fallDistance;
        
        private DataBackup(Player player) {
            this.player = player;
            createBackup();
        }
        
        public void restorePlayer() {
            player.teleport(lastLocation);
            player.setCompassTarget(compass);
            player.setBedSpawnLocation(bedSpawn);
            player.getInventory().setContents(inventory);
            player.getInventory().setArmorContents(armor);
            player.addPotionEffects(activeEffects);
            player.setTotalExperience(totalExp);
            player.setFoodLevel(foodLevel);
            player.setVelocity(velocity);
            player.setCustomName(customName);
            player.setDisplayName(displayName);
            player.setFallDistance(fallDistance);
        }
        
        public void createBackup() {
            inventory = player.getInventory().getContents();
            player.getInventory().setContents(new ItemStack[] {});
            armor = player.getInventory().getArmorContents();
            player.getInventory().setArmorContents(new ItemStack[] {});
            activeEffects = player.getActivePotionEffects();
            bedSpawn = player.getBedSpawnLocation();
            lastLocation = player.getLocation();
            velocity = player.getVelocity();
            compass = player.getCompassTarget();
            customName = player.getCustomName();
            displayName = player.getDisplayName();
            fallDistance = player.getFallDistance();
            foodLevel = player.getFoodLevel();
            totalExp = player.getTotalExperience();
            clearEffects();
        }
        
        private void clearEffects() {
            if (player.getActivePotionEffects().isEmpty()) return;
            for (PotionEffect p : player.getActivePotionEffects()) {
                player.removePotionEffect(p.getType());
            }
        }
    }
}
