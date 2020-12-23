package fr.lifecraftsoup.kitevent;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class Bumblebee implements Listener {
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent evt) {
		if(evt.getEntity().getType()==EntityType.PLAYER && evt.getDamager() instanceof Arrow) {
			 ProjectileSource src = ((Arrow)evt.getDamager()).getShooter();
			 Player damager = (Player)src;
			 ItemStack it = damager.getItemInHand();
			 if(it != null && it.hasItemMeta() && it.getType()==Material.BOW &&damager.getWorld().getName().equalsIgnoreCase("delfino")) {
				 if(it.getItemMeta().getDisplayName() == "§eArc du Bumblebee"){
					Player victim = (Player)evt.getEntity();
					Random r = new Random();
					int a = r.nextInt(13);
					if (a == 1) {
						victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
					} else if (a == 2) {
						victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 1));
					} else if (a == 3) {
						victim.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 120, 1));
					} else if (a == 4) {
						victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 120, 1));
					} 
		    	}
			 }
		}
	}
}
