package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryMenu implements Listener {
	public static Inventory myInventory = Bukkit.createInventory(null, 9, "Choose an arena to jofin!");
	static {
		myInventory.setItem(0, new ItemStack(Material.DIRT, 1));
		myInventory.setItem(8, new ItemStack(Material.GOLD_BLOCK, 1));
		}

}
