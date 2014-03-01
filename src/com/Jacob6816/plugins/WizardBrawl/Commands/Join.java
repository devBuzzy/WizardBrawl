package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Join extends CommandBase {
    
    public Join() {
        super("<Arena>", "Allows for you to join an existing arena", "j");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is a player-only command.");
         return;   
        }
    }
}
