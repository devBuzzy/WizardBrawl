package com.Jacob6816.plugins.WizardBrawl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Jacob6816.plugins.WizardBrawl.Commands.CommandBase;
import com.Jacob6816.plugins.WizardBrawl.Commands.Create;
import com.Jacob6816.plugins.WizardBrawl.Commands.Delete;
import com.Jacob6816.plugins.WizardBrawl.Commands.Join;
import com.Jacob6816.plugins.WizardBrawl.Commands.Leave;
import com.Jacob6816.plugins.WizardBrawl.Commands.Load;
import com.Jacob6816.plugins.WizardBrawl.Commands.Location;

public class CommandHandler implements CommandExecutor {
    HashSet<CommandBase> commands = new HashSet<CommandBase>();
    
    public CommandHandler() {
        commands.add(new Join());
        commands.add(new Create());
        commands.add(new Leave());
        commands.add(new Location());
        commands.add(new Delete());
        commands.add(new Load());
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            StringBuilder sb = new StringBuilder();
            for (CommandBase b : commands) {
                sb.append(b.getClass().getSimpleName() + ", ");
            }
            sender.sendMessage(ChatColor.YELLOW + "== WizardBrawl Commands ==");
            for (CommandBase b : commands) {
                sender.sendMessage(ChatColor.GRAY + b.getClass().getSimpleName() + " - " + b.getDescription());
            }
            return true;
        }
        CommandBase b = getCommand(args[0]);
        if (b != null) {
            b.onCommand(sender, reduce(args));
        }
        else {
            sender.sendMessage(ChatColor.RED + "Command not found.");
        }
        return true;
    }
    
    private CommandBase getCommand(String name) {
        for (CommandBase b : commands) {
            if (b.getClass().getSimpleName().equalsIgnoreCase(name)) {
                return b;
            }
            else {
                if (b.hasAliases()) {
                    for (String s : b.getAliases()) {
                        if (s.equalsIgnoreCase(name)) return b;
                    }
                }
            }
        }
        return null;
    }
    
    private String[] reduce(String... strings) {
        if (strings == null || strings.length == 0) return new String[] {};
        ArrayList<String> lines = new ArrayList<String>(Arrays.asList(strings));
        lines.remove(0);
        return lines.toArray(new String[lines.size()]);
    }
}
