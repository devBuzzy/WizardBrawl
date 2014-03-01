package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class IceWand extends WandBase {
    
    public IceWand() {
        super(ChatColor.translateAlternateColorCodes('&', "&3Ice WAnd&r"), 5, ChatColor.AQUA + "Freeze your enemies in place.");
    }
    
    @Override
    public void spell(Player caster) {
        
    }
    
}
