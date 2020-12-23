package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

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

public class Frog implements Listener{
	public HashMap<String, Long> cooldownfrog = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownfrog.put(p.getName(), (long) 0);
	}
	
	  @EventHandler
	  public void onCheetah(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§cNénuphar" && p.getWorld().getName().equalsIgnoreCase("delfino")){
				      int cooldownTime = 4; 
				      if(cooldownfrog.containsKey(p.getName())) {
				            long secondsLeft = ((cooldownfrog.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	cooldownfrog.put(p.getName(), System.currentTimeMillis());
				            	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
				            	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 4));
				            	p.playSound(p.getLocation(), Sound.EAT, 10.0f, 10.0f);
				            }
				      }
			      
		    		}	 
		    	}
		    }
	  }
}
