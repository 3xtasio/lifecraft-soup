package fr.lifecraftsoup.kitevent;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Snail implements Listener{
	@EventHandler
	  public void entityDamaged(EntityDamageByEntityEvent e) {
	    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
	      Player player = (Player)e.getEntity();
	      Player p = (Player)e.getDamager();
	      ItemStack it = p.getItemInHand();
	      if(it != null && it.hasItemMeta()) {
	    		if(it.getItemMeta().getDisplayName() == "§eEpée du Snail") {
			        Random r = new Random();
			        int a = r.nextInt(9);
			        if (a <=2) {
			          player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 150, 1)); 
		    		}
	    		}
	      } 
	    } 
	  }
}
