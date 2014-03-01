package com.Jacob6816.plugins.WizardBrawl.Arenas;

import org.bukkit.Bukkit;

import com.Jacob6816.plugins.WizardBrawl.WizardBrawl;
import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena.State;

public class TickTimer {
    private WizardBrawl plugin;
    
    public TickTimer(WizardBrawl main) {
        plugin = main;
        doGameTick();
    }
    
    private void doGameTick() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                if (ArenaManager.get().getAllArenas().length <= 0) return;
                for (Arena a : ArenaManager.get().getAllArenas()) {
                    if (a.getState() == Arena.State.INGAME || a.getState() == State.GRACE) {
                        a.doTick();
                    }
                }
            }
        }, 0, 20);
    }
}
