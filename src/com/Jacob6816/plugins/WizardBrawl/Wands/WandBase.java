package com.Jacob6816.plugins.WizardBrawl.Wands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class WandBase {
    private ArrayList<WandBase> wands = new ArrayList<WandBase>();
    private int mana;
    private String name;
    private String[] lore;
    
    public WandBase(String name, int mana, String... lore) {
        this.mana = mana;
        this.name = name;
        if (lore != null) {
            this.lore = lore;
        }
        else {
            this.lore = new String[] {};
        }
    }
    
    public String getName() {
        return name;
    }
    
    public int getMana() {
        return mana;
    }
    
    public String[] getLore() {
        return lore;
    }
    
    public abstract void spell(Player caster);
    
    public WandBase getByName(String name) {
        if (wands.isEmpty()) return null;
        for (WandBase base : wands) {
            if (name.equals(base.getName())) return base;
        }
        return null;
    }
    
    @Deprecated
    public WandBase getByLore(String... lore) {
        if (wands.isEmpty()) return null;
        for (WandBase base : wands) {
            if (base.getLore().length != lore.length) continue;
            for (int i = 0; i < lore.length; i++) {
                if (lore[i].equals(base.getLore()[i])) continue;
            }
            return base;
        }
        return null;
    }
    
    public boolean isWand(ItemStack itemstack) {
        if (!itemstack.hasItemMeta()) return false;
        if (!itemstack.getItemMeta().hasDisplayName()) return false;
        return getByName(itemstack.getItemMeta().getDisplayName()) != null;
    }
}
