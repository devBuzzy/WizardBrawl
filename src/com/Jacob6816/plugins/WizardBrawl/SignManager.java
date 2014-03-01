package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignManager implements Listener {
	
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            if (e.getLine(0).equalsIgnoreCase("[wb]")) {
                    e.setLine(0, "§5[WizardBrawl]");
                    e.setLine(2, arena.getPlayers + "/24");
            }
    }

}
