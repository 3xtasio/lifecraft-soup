package fr.lifecraftsoup.kitevent;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Tank implements Listener{

	@EventHandler
	public void onTank(PlayerDeathEvent e) {
		 Player p = e.getEntity().getPlayer();
		  ItemStack it = p.getInventory().getChestplate();
		  if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§eTank" && p.getWorld().getName().equalsIgnoreCase("delfino")){
						Player v = e.getEntity();
						if (v.getKiller() instanceof Player) {
							Player k = v.getKiller();
							k.getLocation().getWorld().createExplosion(v.getLocation(), 2.0f);
							k.setNoDamageTicks(10);
							for (final Entity et : v.getNearbyEntities(7.0, 5.0, 7.0)) {
								if (et instanceof Player) {
									final Player nrb = (Player) et;
									if (nrb == k) {
										continue;
									}
									nrb.damage(12.0, (Entity) k);
									nrb.playSound(nrb.getLocation(), Sound.EXPLODE, 1.0f, 0.0f);
									k.playSound(nrb.getLocation(), Sound.EXPLODE, 1.0f, 0.0f);
									nrb.setVelocity(new Vector(0, 1.5, 0));
								}
							}
						}
		    		}
		    }
	}
}
