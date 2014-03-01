package com.Jacob6816.plugins.WizardBrawl.Commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Jacob6816.plugins.WizardBrawl.Arenas.Arena;
import com.Jacob6816.plugins.WizardBrawl.Arenas.ArenaManager;
import com.Jacob6816.plugins.WizardBrawl.Misc.Permissions;

public class Location extends CommandBase {
    
    public Location() {
        super("[set] <lobby, red, blue> <name>", "Get or set a location for a map", "loc");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        Permissions perms = new Permissions(sender);
        if (!(perms.CanSetLocations() || perms.CanViewLocations())) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
        }
        if (args.length < 2) {
            sendUsage(sender);
            return;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (!perms.CanSetLocations()) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to set locations.");
                return;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only a player may set a location");
                return;
            }
            final Player player = (Player) sender;
            args = reduce(args);
            if (args[0].equalsIgnoreCase("lobby")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a != null) {
                    a.getHelper().setLobbySpawn(player.getLocation());
                    player.sendMessage(ChatColor.YELLOW + "Location set at your current position.");
                    return;
                }
            }
            else if (args[0].equalsIgnoreCase("red")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a != null) {
                    a.getHelper().setLobbySpawn(player.getLocation());
                    player.sendMessage(ChatColor.YELLOW + "Location set at your current position.");
                    return;
                }
            }
            else if (args[0].equalsIgnoreCase("blue")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a != null) {
                    a.getHelper().setLobbySpawn(player.getLocation());
                    player.sendMessage(ChatColor.YELLOW + "Location set at your current position.");
                    return;
                }
            }
            else {
                sendUsage(sender);
                return;
            }
        }
        else {
            if (!perms.CanViewLocations()) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to view locations.");
                return;
            }
            if (args[0].equalsIgnoreCase("lobby")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a == null) {
                    sender.sendMessage(ChatColor.RED + "That arena does not exist.");
                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "Location: " + a.getHelper().locationToString(a.getHelper().getLobbySpawn()));
                    return;
                }
            }
            else if (args[0].equalsIgnoreCase("red")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a == null) {
                    sender.sendMessage(ChatColor.RED + "That arena does not exist.");
                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "Location: " + a.getHelper().locationToString(a.getHelper().getRedSpawn()));
                    return;
                }
            }
            else if (args[0].equalsIgnoreCase("blue")) {
                Arena a = ArenaManager.get().getByName(args[1]);
                if (a == null) {
                    sender.sendMessage(ChatColor.RED + "That arena does not exist.");
                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "Location: " + a.getHelper().locationToString(a.getHelper().getBlueSpawn()));
                    return;
                }
            }
            else {
                sendUsage(sender);
                return;
            }
        }
    }
    
    private String[] reduce(String... strings) {
        if (strings == null || strings.length == 0) return new String[] {};
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(strings));
        lines.remove(0);
        return lines.toArray(new String[lines.size()]);
    }
}
