package com.Jacob6816.plugins.WizardBrawl.Misc;

import org.bukkit.entity.Player;

public class Permissions {
    Player p;
    
    public Permissions(Player player) {
        p = player;
    }
    
    boolean IsAdmin() {
        return p.hasPermission("WizardBrawl.Admin") || p.isOp();
    }
    
    boolean IsMod() {
        return p.hasPermission("WizardBrawl.Mod") || IsAdmin();
    }
    
    boolean CanCreateSigns() {
        return p.hasPermission("WizardBrawl.Signs.Create") || IsMod();
    }
    
    boolean CanDestroySigns() {
        return p.hasPermission("WizardBrawl.Signs.Destroy") || IsMod();
    }
    
    boolean CanUseArena() {
        return p.hasPermission("WizardBrawl.Arena") || IsMod();
    }
    
    boolean CanCreateMap() {
        return p.hasPermission("WizardBrawl.Maps.Create") || IsAdmin();
    }
    
    boolean CanDeleteMap() {
        return p.hasPermission("WizardBrawl.Maps.Delete") || IsAdmin();
    }
}
