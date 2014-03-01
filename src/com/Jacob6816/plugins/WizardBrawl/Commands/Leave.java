package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;

public class Leave extends CommandBase {
    
    public Leave() {
        super(null, "Leave your current game", "l");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is a player-only command.");
            return;
        }
        Arena a = ArenaManager.get().getPlayerArena((Player) sender);
        if (a == null) {
            sender.sendMessage(ChatColor.RED + "You are not currently in a game");
        }
        else {
            a.removePlayer((Player) sender);
        }
    }
}
