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

public class Eagle implements Listener {
	public HashMap<String, Long> cooldowneagle = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldowneagle.put(p.getName(), (long) 0);
	}
	
	  @EventHandler
	  public void onEagle(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§eAiles" && it.getType() == Material.QUARTZ){
				      int cooldownTime = 20; 
				      if(cooldowneagle.containsKey(p.getName())) {
				            long secondsLeft = ((cooldowneagle.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	cooldowneagle.put(p.getName(), System.currentTimeMillis());
				            	p.setVelocity(p.getEyeLocation().getDirection().multiply(3.5));
				            }
				      }
			      
		    		}	 
		    	}
		    }
	  }
	  
	  
	  @EventHandler
	  public void onEagleFall(EntityDamageEvent e) {
		  if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
			 Player p = (Player)e.getEntity();
			 ItemStack it = p.getEquipment().getChestplate();
			 if(it != null && it.hasItemMeta() && p.getInventory().contains(Material.QUARTZ)) {
			    if(it.getItemMeta().getDisplayName() == "§eAiles") {
					 e.setCancelled(true); 
			    }
			 }
		  } 
	}
}
