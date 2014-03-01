package com.Jacob6816.plugins.WizardBrawl.Wands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class FireWand extends WandBase {

	public FireWand() {
		super(ChatColor.translateAlternateColorCodes('&', "&6Fire Wand&r"), 5,
				ChatColor.AQUA + "Shoot fireballs at your enemies!");
	}

	@Override
	public void spell(Player caster) {
		Fireball f = caster.getWorld().createExplosion(x, y, z, 1);
		f.setVelocity(caster.getLocation().getDirection());
		caster.getPlayer().launchProjectile(FireWand.class);
		
	}

}
