package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class List extends CommandBase {
    
    public List() {
        super(null, "Returns a list of all active arenas");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Permissions perms = new Permissions(sender);
        if (!perms.CanListArenas()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to list the arenas.");
            return;
        }
        StringBuilder sb = new StringBuilder(ChatColor.GOLD + "Arenas: ");
        if (ArenaManager.get().getAllArenas().length == 0) {
            sender.sendMessage(sb.toString() + " []");
            return;
        }
        for (Arena a : ArenaManager.get().getAllArenas()) {
            sb.append(a.isJoinable() ? ChatColor.GREEN + a.getName() + ", " + ChatColor.RESET : ChatColor.RED + a.getName() + ", " + ChatColor.RESET);
        }
        sender.sendMessage(sb.toString().substring(0, sb.toString().lastIndexOf(",")));
    }
}
