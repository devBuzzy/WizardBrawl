package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.command.CommandSender;

public class Location extends CommandBase {
    
    public Location() {
        super("[set] <lobby, red, blue> <name>", "Get or set a location for a map", "loc");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if(args.length < 2) {
            sendUsage(sender);
            return;
        }
        
        if(args[0].equalsIgnoreCase("set")) {
            
        }
        else if(args[0].equalsIgnoreCase("lobby")) {
            
        }
        else if(args[0].equalsIgnoreCase("lobby")) {
            
        }
        else if(args[0].equalsIgnoreCase("lobby")) {
            
        }
        else {
            sendUsage(sender);
        }
    }
    
}
