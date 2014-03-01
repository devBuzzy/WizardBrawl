package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.entity.Player;

public abstract class WandBase {
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
}
