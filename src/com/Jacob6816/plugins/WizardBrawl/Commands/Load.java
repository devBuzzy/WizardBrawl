package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class Load extends CommandBase {
    
    public Load() {
        super("<Name>", "Attempt to load a previously unloaded arena");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Permissions perms = new Permissions(sender);
        if (!perms.CanLoadArenas()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to force-load an arena.");
            return;
        }
        if(args.length == 0) {
            sendUsage(sender);
            return;
        }
        sender.sendMessage(ChatColor.YELLOW + "Map loaded: " + ArenaManager.get().loadArena(args[0]));
    }
}
