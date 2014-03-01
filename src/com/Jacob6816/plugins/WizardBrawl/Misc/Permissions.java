package com.Jacob6816.plugins.WizardBrawl.Misc;

import org.bukkit.entity.Player;

public class Permissions {
    Player p;
    
    public Permissions(Player player) {
        p = player;
    }
    
    private boolean IsAdmin() {
        return p.hasPermission("WizardBrawl.Admin") || p.isOp();
    }
    
    private boolean IsMod() {
        return p.hasPermission("WizardBrawl.Mod") || IsAdmin();
    }
    
    public boolean CanCreateSigns() {
        return p.hasPermission("WizardBrawl.Signs.Create") || IsMod();
    }
    
    public boolean CanDestroySigns() {
        return p.hasPermission("WizardBrawl.Signs.Destroy") || IsMod();
    }
    
    public boolean CanUseArena() {
        return p.hasPermission("WizardBrawl.Arena") || IsMod();
    }
    
    public boolean CanCreateMap() {
        return p.hasPermission("WizardBrawl.Maps.Create") || IsAdmin();
    }
    
    public boolean CanDeleteMap() {
        return p.hasPermission("WizardBrawl.Maps.Delete") || IsAdmin();
    }
}
