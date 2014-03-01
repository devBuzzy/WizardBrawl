package com.Jacob6816.plugins.WizardBrawl;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class SignManager implements Listener {
    
    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("[wb]")) {
            Permissions perms = new Permissions(e.getPlayer());
            if (perms.CanCreateSigns()) {
                e.setLine(0, "§5[WizardBrawl]");
                e.setLine(2, ArenaManager.get().getByName(e.getLine(1)) + "/24");
                e.setLine(3, ChatColor.AQUA + "Click Here to join!");
            }
        }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void clickHandler(PlayerInteractEvent e){
        if(!(e.getAction()==Action.RIGHT_CLICK_BLOCK || e.getAction()==Action.LEFT_CLICK_BLOCK)) return;
        Block clickedBlock = e.getClickedBlock(); 
        if(!(clickedBlock.getType()==Material.SIGN || clickedBlock.getType()==Material.SIGN_POST || clickedBlock.getType()==Material.WALL_SIGN)) return;
        Sign thisSign = (Sign) clickedBlock.getState();
        String[] lines = thisSign.getLines();       
        if(lines[0].equalsIgnoreCase("§5[WizardBrawl]")){
        	Permissions perms = new Permissions(e.getPlayer());
        	Arena a = ArenaManager.get().getByName(lines[1]);
        	if(a != null && perms.CanUseArena()) {
        		a.addPlayer(e.getPlayer());
        	}
        		}	
        	
    	}
    
	}
