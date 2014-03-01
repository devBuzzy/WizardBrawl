package com.Jacob6816.plugins.WizardBrawl.Arenas;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.Jacob6816.plugins.WizardBrawl.Wands.WandBase;

public class ArenaEvents implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onGraceMove(PlayerMoveEvent event) {
        final Player p = event.getPlayer();
        final ArenaManager m = ArenaManager.get();
        final Arena a = m.getPlayerArena(p);
        event.setCancelled(a != null && a.getState() == Arena.State.GRACE);
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event) {
        final Arena a = ArenaManager.get().getPlayerArena(event.getPlayer());
        if (a == null) return;
        if (!a.locationIsInsideArena(event.getTo())) {
            event.setCancelled(true);
            event.getPlayer().teleport(a.getTeamSpawn(event.getPlayer()));
        }
    }
    
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent event) {
        if (!event.getAction().toString().contains("LEFT")) return;
        final Player player = event.getPlayer();
        if (player.getItemInHand() != null) {
            ItemStack i = player.getItemInHand();
            if (!WandBase.isWand(i)) return;
            WandBase b = WandBase.getByName(i.getItemMeta().getDisplayName());
            if (b != null) {
                b.spell(player);
            }
        }
    }
}
