package com.Jacob6816.plugins.WizardBrawl.Arenas;

public class ArenaManager {
    private static ArenaManager instance;
    
    private ArenaManager() {
        if (instance == null) instance = new ArenaManager();
    }
    
    public static ArenaManager get() {
        if (instance == null) instance = new ArenaManager();
        return instance;
    }
}
