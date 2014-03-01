package com.Jacob6816.plugins.WizardBrawl.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public abstract class CommandBase {
    String[] aliases;
    String usage, description;
    
    public CommandBase(String usage, String description, String... aliases) {
        this.usage = usage;
        this.description = description;
        this.aliases = aliases;
    }
    
    public String getUsage() {
        return usage;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String[] getAliases() {
        return aliases;
    }
    
    public boolean hasAliases() {
        return aliases != null && aliases.length >= 1;
    }
    
    public abstract void onCommand(CommandSender sender, String[] args);
    
    public void sendUsage(CommandSender sender) {
        sender.sendMessage(ChatColor.YELLOW + "Usage: /WizardBrawl " + getClass().getSimpleName() + " " + getUsage());
    }
}
