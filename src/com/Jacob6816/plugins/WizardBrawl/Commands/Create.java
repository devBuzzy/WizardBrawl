package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jacob6816.plugins.WizardBrawl.WizardBrawl;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Create extends CommandBase {
    
    public Create() {
        super("<Name>", "Create a new arena", "c");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This is a player-only command.");
            return;
        }
        final Player player = (Player) sender;
        if (args.length == 0) {
            sendUsage(player);
            return;
        }
        final Selection selection = WizardBrawl.getWorldEdit().getSelection(player);
        if (selection == null) {
            player.sendMessage(ChatColor.RED + "You must have a current WorldEdit selection for the arena.");
            return;
        }
        player.sendMessage(ChatColor.YELLOW + "Arena created: " + ArenaManager.get().createNewArena(args[0], selection));
    }
}
