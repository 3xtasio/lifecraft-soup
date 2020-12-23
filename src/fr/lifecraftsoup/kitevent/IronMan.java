package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.lifecraftsoup.main.Main;

public class IronMan implements Listener{
	public HashMap<String, Long> cooldownironman= new HashMap<String, Long>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownironman.put(p.getName(), (long) 0);
       p.setAllowFlight(false);
       p.setFlying(false);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
	   Player p = e.getEntity().getPlayer();
	   cooldownironman.put(p.getName(), (long) 0);
       p.setAllowFlight(false);
       p.setFlying(false);
	}
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		Player player = e.getPlayer();
		ItemStack it = player.getItemInHand();
		if(it != null && it.hasItemMeta()) {
			if(it.getItemMeta().getDisplayName().equals("§cIronMan") && it.getType().equals(Material.IRON_BLOCK) && player.getWorld().getName().equalsIgnoreCase("delfino")) {
	            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	    			int cooldownTime = 30; 
	    		    if(cooldownironman.containsKey(player.getName())) {
	    		          long secondsLeft = ((cooldownironman.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
	    		          if(secondsLeft > 0) {
	    		              player.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
	    		          } else {
	    		           cooldownironman.put(player.getName(), System.currentTimeMillis());
	    		           player.setAllowFlight(true);
	    		           player.sendMessage("§e» §7Vous utilisez votre compétence §bRéacteurs dorsaux");
	    		           player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 0.0f);
		    		       new BukkitRunnable(){
			    		             @Override
			    		             public void run(){
			    		               player.setAllowFlight(false);
			    		               player.setFlying(false);
			    		             }
		    		          }.runTaskLater(Main.getInstance(), 200L);
	    		          }
	    		    }
	    		}
	            
			}
		}
	}
}
