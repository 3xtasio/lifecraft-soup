package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.lifecraftsoup.utils.ItemBuilder;

public class Switcher implements Listener {
	
	public HashMap<String, Long> cooldownswitcher = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownswitcher.put(p.getName(), (long) 0);
	}
	
	@EventHandler
	  public void entityDamaged(EntityDamageByEntityEvent e) {
	    if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
	      Player player = (Player)e.getEntity();
	      Snowball s = (Snowball)e.getDamager();
	      Player p = (Player)s.getShooter(); 
	      ItemStack it = p.getItemInHand();
	      if(it != null && it.hasItemMeta()) {
	    		if(it.getItemMeta().getDisplayName() == "§cSwitcher" && it.getType() == Material.SNOW_BALL && p.getWorld().getName().equalsIgnoreCase("delfino")){
			      int cooldownTime = 15; 
			      if(cooldownswitcher.containsKey(p.getName())) {
			            long secondsLeft = ((cooldownswitcher.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft > 0) {
			            	e.setCancelled(true);
			            	p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL).setDisplayName("§cSwitcher").setAmount(1)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());;
			                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
			            } else {
			            	e.setCancelled(true);
			            	cooldownswitcher.put(p.getName(), System.currentTimeMillis());
			            	p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL).setDisplayName("§cSwitcher").setAmount(1)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());;
			            	p.sendMessage("§e» §7Vous avez échangé de position avec "+player.getName());
			            	p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
			            	Location l1 = p.getLocation();
			            	p.teleport(player.getLocation());
			            	player.teleport(l1);
			            }
			      }
		      
	    		}	 
	    	}
	    } 
	  }
}
