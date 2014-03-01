package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class Join extends CommandBase {
    
    public Join() {
        super("<Arena>", "Allows for you to join an existing arena", "j");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is a player-only command.");
            return;
        }
        final Player player = (Player) sender;
        Permissions perms = new Permissions(sender);
        if(!perms.CanUseArena()) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to join an arena.");
            return;
        }
        if (args.length == 0) {
            sendUsage(player);
            return;
        }
        Arena a = ArenaManager.get().getByName(args[0]);
        if (a == null) {
            player.sendMessage(ChatColor.RED + "Sorry, that arena does not exist.");
            return;
        }
        else {
            if (a.isJoinable()) {
                a.addPlayer(player);
            }
            else {
                player.sendMessage(ChatColor.RED + "That arena is not currently joinable.");
            }
        }
    }
}
