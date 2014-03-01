package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class Delete extends CommandBase {
    
    public Delete() {
        super("<Name>", "Delete a WizardBrawl Map", "d");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Permissions perms = new Permissions(sender);
        if (!perms.CanDeleteMap()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
            return;
        }
        if (args.length == 0) {
            sendUsage(sender);
            return;
        }
        Arena a = ArenaManager.get().getByName(args[0]);
        if (a == null) {
            sender.sendMessage(ChatColor.RED + "That arena does not exist");
            return;
        }
        a.endGame();
        ArenaManager.get().removeArena(a);
        a.getHelper().getFile().delete();
    }
}
