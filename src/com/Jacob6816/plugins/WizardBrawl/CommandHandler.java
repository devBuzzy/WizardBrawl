package com.Jacob6816.plugins.WizardBrawl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Jacob6816.plugins.WizardBrawl.Commands.CommandBase;

public class CommandHandler implements CommandExecutor {
    HashSet<CommandBase> commands = new HashSet<CommandBase>();
    
    public CommandHandler() {
        // Register commands
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            // Send list of commands
            return true;
        }
        CommandBase b = getCommand(args[0]);
        b.onCommand(sender, reduce(args));
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