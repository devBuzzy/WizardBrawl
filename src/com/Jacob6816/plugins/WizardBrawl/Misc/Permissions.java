package com.Jacob6816.plugins.WizardBrawl.Misc;

import org.bukkit.command.CommandSender;

public class Permissions {
    CommandSender p;
    
    public Permissions(CommandSender sender) {
        p = sender;
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
    
    public boolean CanSetLocations() {
        return p.hasPermission("WizardBrawl.Maps.Locations.Set") || IsAdmin();
    }
    
    public boolean CanViewLocations() {
        return p.hasPermission("WizardBrawl.Maps.Locations.View") || CanSetLocations();
    }
    
    public boolean CanLoadArenas() {
        return p.hasPermission("WizardBrawl.Maps.Load") || IsMod();
    }
    
    public boolean CanListArenas() {
        return p.hasPermission("WizardBrawl.Maps.List") || IsMod();
    }
}
