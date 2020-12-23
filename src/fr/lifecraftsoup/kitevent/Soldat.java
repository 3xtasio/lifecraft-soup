package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class Soldat implements Listener{
	public HashMap<String, Long> cooldownsoldier = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownsoldier.put(p.getName(), (long) 0);
	}
	
	  @EventHandler
	  public void onSoldier(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§eEpée du Soldat"){
				      int cooldownTime = 10; 
				      if(cooldownsoldier.containsKey(p.getName())) {
				            long secondsLeft = ((cooldownsoldier.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	cooldownsoldier.put(p.getName(), System.currentTimeMillis());
				            	p.setVelocity(p.getLocation().getDirection().multiply(1.25).setY(1));
				            }
				      }
			      
		    		}	 
		    	}
		    }
	  }
	  
	  @EventHandler
	  public void onSoldierFall(EntityDamageEvent e) {
	    if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
	      Player p = (Player)e.getEntity();
	      ItemStack it = p.getEquipment().getChestplate();
	      if(it != null && it.hasItemMeta() && p.getInventory().contains(Material.IRON_SWORD)) {
	    	  if(it.getItemMeta().getDisplayName() == "§eSoldat") {
			      e.setCancelled(true); 
	    	  }
	      }
	    } 
	  }
}
