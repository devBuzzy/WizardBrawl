package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.Jacob6816.plugins.WizardBrawl.WizardBrawl;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;
import com.Jacob6816.plugins.WizardBrawl.Wands.WandBase;

public class Wand extends CommandBase implements Listener {
    private static final Inventory wands = Bukkit.createInventory(null, getSize(), ChatColor.GOLD + "Wands");
    
    public Wand() {
        super(null, "Allows for you to choose your wand", "w");
        if (WandBase.getAllWands().length >= 1) {
            for (WandBase w : WandBase.getAllWands()) {
                wands.addItem(w.getWand().clone());
            }
            Bukkit.getPluginManager().registerEvents(this, WizardBrawl.get());
        }
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Permissions perms = new Permissions(sender);
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is a player-only command");
            return;
        }
        if (!perms.CanChooseWand()) {
            sender.sendMessage(ChatColor.RED + "You do not have permissions to select a wand.");
            return;
        }
        ((Player) sender).openInventory(wands);
    }
    
    private static int getSize() {
        int base = WandBase.getAllWands().length;
        while (base % 9 != 0) {
            base++;
        }
        return base;
    }
    
    @EventHandler(ignoreCancelled = false, priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent evt) {
        if (evt.getInventory().equals(wands) || evt.getInventory().getName().equals(wands.getName())) {
            evt.setCancelled(true);
            ItemStack c = evt.getCurrentItem();
            if (c == null) return;
            WizardBrawl.get().saveDefaultConfig();
            setWand((Player) evt.getWhoClicked(), ChatColor.stripColor(c.getItemMeta().getDisplayName()));
        }
    }
    
    public static void setWand(Player p, String wand) {
        WizardBrawl.get().getConfig().set(p.getName(), ChatColor.stripColor(wand.toUpperCase()));
        WizardBrawl.get().saveConfig();
    }
}
