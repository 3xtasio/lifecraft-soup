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

public class DrugDealer implements Listener{

	
	public HashMap<String, Long> cooldowndrugdealer = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldowndrugdealer.put(p.getName(), (long) 0);
	}
	
	  @EventHandler
	  public void onEagle(PlayerInteractEvent e) {
		  Player p = e.getPlayer();
		  ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName() == "§2Weed" && it.getType() == Material.SUGAR_CANE && p.getWorld().getName().equalsIgnoreCase("delfino")){
				      int cooldownTime = 15; 
				      if(cooldowndrugdealer.containsKey(p.getName())) {
				            long secondsLeft = ((cooldowndrugdealer.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
				            if(secondsLeft > 0) {
				                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
				            } else {
				            	cooldowndrugdealer.put(p.getName(), System.currentTimeMillis());
				            	p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 3));
				                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 160, 3));
				                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 160, 3));
				                p.playSound(p.getLocation(), Sound.CREEPER_HISS, 1.0f, 10.0f);
				            }
				      }
			      
		    		}	 
		    	}
		    }
	  }
}
