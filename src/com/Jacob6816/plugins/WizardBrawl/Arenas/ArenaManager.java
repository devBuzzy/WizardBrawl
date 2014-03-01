package com.Jacob6816.plugins.WizardBrawl.Arenas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.Jacob6816.plugins.WizardBrawl.WizardBrawl;
import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena.State;
import com.Jacob6816.plugins.WizardBrawl.Misc.ConfigHelper;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
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
    
    public boolean removeArena(Arena a) {
        boolean val = arenas.contains(a);
        if (val) arenas.remove(a);
        return val;
    }
    
    public boolean loadArena(String name) {
        Arena a = getByName(name);
        if (a != null) {
            injectData(a);
            return true;
        }
        File f = new File(WizardBrawl.get().getDataFolder(), "Maps" + File.separator + name + ".yml");
        if (!f.exists()) return false;
        FileConfiguration conf = YamlConfiguration.loadConfiguration(f);
        List<String> data = Arrays.asList("MaxPlayers", "Spawns", "Spawns.Red", "Spawns.Blue", "Spawns.Lobby", "Natives", "Natives.Lowest", "Natives.Highest");
        for (String s : data) {
            if (conf.get(s) == null) { return false; }
        }
        Location h = ConfigHelper.locationFromString(conf.getString("Natives.Highest"));
        Location l = ConfigHelper.locationFromString(conf.getString("Natives.Lowest"));
        a = new Arena(f.getName().substring(0, f.getName().lastIndexOf(".")), new CuboidSelection(h.getWorld(), h, l));
        injectData(a);
        a.setState(State.LOBBY);
        arenas.add(a);
        return true;
    }
    
    public void injectData(Arena a) {
        a.setBlueSpawn(ConfigHelper.locationFromString((String) a.getHelper().get("Spawns.Blue")));
        a.setRedSpawn(ConfigHelper.locationFromString((String) a.getHelper().get("Spawns.Red")));
        a.setLobby(ConfigHelper.locationFromString((String) a.getHelper().get("Spawns.Lobby")));
        a.setMaxPlayers((int) (a.getHelper().get("MaxPlayers") != null ? a.getHelper().get("MaxPlayers") : 24));
    }
}
