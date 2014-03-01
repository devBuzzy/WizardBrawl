package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FireWand extends WandBase {
    
    public FireWand() {
        super(ChatColor.translateAlternateColorCodes('&', "&6Fire Wand&r"), 5, new ItemStack(Material.BLAZE_ROD), ChatColor.AQUA + "Shoot fireballs at your enemies!");
    }
    
    @Override
    public void spell(Player caster) {
        Fireball f = caster.getPlayer().launchProjectile(Fireball.class);
        f.setVelocity(caster.getLocation().getDirection());
    }
}
