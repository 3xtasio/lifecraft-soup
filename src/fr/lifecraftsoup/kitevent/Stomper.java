package fr.lifecraftsoup.kitevent;

import java.util.Iterator;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class Stomper implements Listener{
	@EventHandler
	public void onStomper(EntityDamageEvent e) {
		if (e.getEntity().getType()== EntityType.PLAYER && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
		      Player p = (Player)e.getEntity();
		      ItemStack it = p.getEquipment().getChestplate();
		      if(it != null && it.hasItemMeta() && p.getInventory().contains(Material.STONE_SWORD) && p.getWorld().getName().equalsIgnoreCase("delfino")) {
		    	  if(it.getItemMeta().getDisplayName() == "§eStomper") {
		    		  System.out.println("e");
		    		  	int a = 0;
			    	      for (Entity ent : p.getNearbyEntities(5.0D, 5.0D, 5.0D)) {
			    	        if (ent instanceof LivingEntity) {
			    	          a++;
			    	          ((LivingEntity)ent).damage(e.getDamage() / 2.25D);
			    	        } 
			    	    } 
						if (e.getDamage() >= 4.0) {
							e.setDamage(4.0);
						}
		    	  }
		      }
		    } 
	}
}
