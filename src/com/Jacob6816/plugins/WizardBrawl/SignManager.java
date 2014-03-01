package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignManager implements Listener {
	
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
            if (e.getLine(0).equalsIgnoreCase("[Test]")) {
                    e.setLine(0, "§3[Cdd]");
            }
    }

}
