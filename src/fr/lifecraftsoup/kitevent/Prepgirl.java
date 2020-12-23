package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Prepgirl implements Listener{
	public HashMap<String, Long> cooldownprep = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownprep.put(p.getName(), (long) 0);
	}
	
	  @EventHandler
	  public void onPrep(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§ciPhone 7s" && p.getWorld().getName().equalsIgnoreCase("delfino")){
				      int cooldownTime = 15; 
				      if(cooldownprep.containsKey(p.getName())) {
				            long secondsLeft = ((cooldownprep.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	for (Entity et : p.getNearbyEntities(8.0, 8.0, 8.0)) {
				                    if (et instanceof Player) {
				                        final Player pls = (Player) et;
				                        pls.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
				                        pls.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
				                        pls.sendMessage("§7»§6 " + p.getDisplayName() +" §ea prit un selfie !");
				                        pls.playSound(pls.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 0.0f);
				                    }
				                }
				            	p.sendMessage("§7» §eTu as pris un selfie !");
				            	cooldownprep.put(p.getName(), System.currentTimeMillis());
				            	p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 10.0f, 10.0f);
				            }
				      }
			      
		    		}	 
		    	}
		    }
	  }
}
