package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class IceWand extends WandBase {
    
    public IceWand() {
        super(ChatColor.translateAlternateColorCodes('&', "&3Ice WAnd&r"), 5, new ItemStack(Material.DIAMOND_HOE), ChatColor.AQUA + "Freeze your enemies in place.");
        WandBase.addWand(this);
    }
    
    @Override
    public void spell(Player caster) {
        
    }
}
