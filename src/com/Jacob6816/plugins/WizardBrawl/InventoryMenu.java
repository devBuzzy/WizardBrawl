package com.Jacob6816.plugins.WizardBrawl;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;

public class InventoryMenu implements Listener {
	public static Inventory myInventory = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Choose an arena to join!");
	ItemStack arenas = new ItemStack(Material.DIAMOND_BLOCK);

	private static ItemStack[] arenaItemStacks() {
		ArrayList<ItemStack> l = new ArrayList<ItemStack>();
		for (Arena a : ArenaManager.get().getAllArenas()) {
			ItemStack i = new ItemStack(Material.DIAMOND_BLOCK);
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(ChatColor.LIGHT_PURPLE + a.getName());
			im.setLore(Arrays.asList(ChatColor.DARK_RED	+ "Click to join an arena!", a.getIndepthDescription()));
			i.setItemMeta(im);
			l.add(i);
		}
		return l.toArray(new ItemStack[l.size()]);
	}

	public static void UpdateInventory() {
		myInventory.setContents(arenaItemStacks());
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player){
		Player player = (Player) e.getWhoClicked();
		ItemStack clicked = e.getCurrentItem();
		Inventory inventory = e.getInventory(); 
		if (inventory.getName().equals(myInventory.getName())) {
			e.setCancelled(true);
			e.getCurrentItem();
			
		}
	}
}
}
