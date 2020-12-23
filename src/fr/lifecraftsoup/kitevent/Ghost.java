package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Ghost implements Listener {
	public HashMap<String, Long> cooldownghost = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownghost.put(p.getName(), (long) 0);
	}
	
	@EventHandler
	public void onGhost(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta() && it.getType() == Material.PAPER && p.getWorld().getName().equalsIgnoreCase("delfino")) {
		    		if(it.getItemMeta().getDisplayName() == "§aInvisibilité"){
				      int cooldownTime = 2; 
				      if(cooldownghost.containsKey(p.getName())) {
				            long secondsLeft = ((cooldownghost.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
				                    for (final PotionEffect pe : p.getActivePotionEffects()) {
				                        p.removePotionEffect(pe.getType());
				                    }
				            	} else {
					            	p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
				            	}
				            	cooldownghost.put(p.getName(), System.currentTimeMillis());
				            	p.playSound(p.getLocation(), Sound.GHAST_SCREAM, 10.0f, 10.0f);
				            }
				      }
			      
		    		}	 
		    	}
		    }
	}
}
