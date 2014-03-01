package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExampleWand extends WandBase {
    
    public ExampleWand() {
        super("Name", 20, new ItemStack(Material.STICK), "Lore");
    }
    
    @Override
    public void spell(Player caster) {
        // TODO DO WORK!
    }
}
