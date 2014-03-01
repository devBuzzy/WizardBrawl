package com.Jacob6816.plugins.WizardBrawl.Misc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Jacob6816.plugins.WizardBrawl.WizardBrawl;

public class ConfigHelper {
    private FileConfiguration config;
    
    public ConfigHelper(String map, boolean createIfAbsent) {
        File file = new File(WizardBrawl.get().getDataFolder(), "Maps" + File.separator + map + ".yml");
        if (!file.exists() && createIfAbsent) try {
            file.createNewFile();
        }
        catch (IOException e) {
            System.out.println("Failed to generate config file: " + map + ".yml");
            e.printStackTrace();
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    
    public void setMaxPlayers(int players) {
        config.set("MaxPlayers", players);
    }
    
    public int getMaxPlayers() {
        return get("MaxPlayers");
    }
    
    public void setRedSpawn(Location location) {
        config.set("Spawns.Red", locationToString(location));
    }
    
    public void setBlueSpawn(Location location) {
        config.set("Spawns.Blue", locationToString(location));
    }
    
    public void setLobbySpawn(Location location) {
        config.set("Spawns.Lobby", locationToString(location));
    }
    
    public Location getRedSpawn() {
        String text = get("Spawns.Red");
        return locationFromString(text);
    }
    
    public Location getBlueSpawn() {
        String text = get("Spawns.Blue");
        return locationFromString(text);
    }
    
    public Location getLobbySpawn() {
        String text = get("Spawns.Lobby");
        return locationFromString(text);
    }
    
    public void setLowestPoint(Location lowest) {
        config.set("Natives.Lowest", locationToString(lowest));
    }
    
    public void setHighestPoint(Location highest) {
        config.set("Natives.Highest", locationToString(highest));
    }
    
    public Location getLowestPoint() {
        String text = get("Natives.Lowest");
        return locationFromString(text);
    }
    
    public Location getHighestPoint() {
        String text = get("Natives.Highest");
        return locationFromString(text);
    }
    
    private String locationToString(Location location) {
        String w = location.getWorld().getName();
        double x = Math.ceil(location.getX());
        double y = Math.ceil(location.getY());
        double z = Math.ceil(location.getZ());
        float v = (float) Math.ceil(location.getYaw());
        float p = (float) Math.ceil(location.getPitch());
        String s = w + ":" + x + ":" + y + ":" + z + ":" + v + ":" + p;
        return s.trim();
    }
    
    private Location locationFromString(String location) {
        String[] l = location.split(":");
        World w = Bukkit.getWorld(l[0]);
        double x = Double.parseDouble(l[1]);
        double y = Double.parseDouble(l[2]);
        double z = Double.parseDouble(l[3]);
        float v = Float.parseFloat(l[4]);
        float p = Float.parseFloat(l[5]);
        Location target = new Location(w, x, y, z, v, p);
        return target;
    }
    
    /**
     * Get adaptive object T; cast-able to all
     * 
     * @param path
     * @return
     */
    
    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) config.get(path);
    }
}
