package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class SignManager implements Listener {
	
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            if (e.getLine(0).equalsIgnoreCase("[wb]")) {
            	Permissions perms = new Permissions(e.getPlayer());
            	if(perms.)
                    e.setLine(0, "§5[WizardBrawl]");
                    e.setLine(2, ArenaManager.get().getByName(e.getLine(1)) + "/24");
                    e.setLine(3, ChatColor.AQUA + "Click Here to join!");
            }
    }

}
