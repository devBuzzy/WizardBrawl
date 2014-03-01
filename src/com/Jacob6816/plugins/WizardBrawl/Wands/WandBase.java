package com.Jacob6816.plugins.WizardBrawl.Wands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class WandBase {
    private static ArrayList<WandBase> wands = new ArrayList<WandBase>();
    private int mana;
    private String name;
    private String[] lore;
    private ItemStack root;
    
    public WandBase(String name, int mana, ItemStack root, String... lore) {
        this.mana = mana;
        this.name = name;
        if (lore != null) {
            this.lore = lore;
        }
        else {
            this.lore = new String[] {};
        }
        this.root = root.clone();
        wands.add(new FireWand());
        wands.add(new IceWand());
        wands.add(new ExampleWand());
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
    
    public static WandBase getByName(String name) {
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
    
    public static boolean isWand(ItemStack itemstack) {
        if (!itemstack.hasItemMeta()) return false;
        if (!itemstack.getItemMeta().hasDisplayName()) return false;
        return getByName(itemstack.getItemMeta().getDisplayName()) != null;
    }
    
    public ItemStack getWand() {
        ItemStack wand = root.clone();
        ItemMeta meta = wand.getItemMeta();
        meta.setDisplayName(getName());
        meta.setLore(Arrays.asList(getLore()));
        wand.setItemMeta(meta);
        return wand;
    }
    
    public static void addWand(WandBase base) {
        wands.add(base);
    }
    
    public static WandBase[] getAllWands() {
        return wands.toArray(new WandBase[wands.size()]);
    }
}
