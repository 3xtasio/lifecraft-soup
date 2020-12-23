package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Material;
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


public class Hermès implements Listener{
	public HashMap<String, Long> cooldownfly= new HashMap<String, Long>();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownfly.put(p.getName(), (long) 0);
       p.setAllowFlight(false);
       p.setFlying(false);
	}
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
	   Player p = e.getEntity().getPlayer();
	   cooldownfly.put(p.getName(), (long) 0);
       p.setAllowFlight(false);
       p.setFlying(false);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {

		Player player = e.getPlayer();
		ItemStack it = player.getItemInHand();
		if(it != null && it.hasItemMeta()) {
			if(it.getItemMeta().getDisplayName().equals("§cHermès") && it.getType().equals(Material.FEATHER) && player.getWorld().getName().equalsIgnoreCase("delfino")) {
	            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	    			int cooldownTime = 30; 
	    		    if(cooldownfly.containsKey(player.getName())) {
	    		          long secondsLeft = ((cooldownfly.get(player.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
	    		          if(secondsLeft > 0) {
	    		              player.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
	    		          } else {
	    		           cooldownfly.put(player.getName(), System.currentTimeMillis());
	    		           player.setAllowFlight(true);
	    		           player.sendMessage("§e» §7Vous utilisez votre compétence §bVol");
		    		       new BukkitRunnable(){
			    		             @Override
			    		             public void run(){
			    		               player.setAllowFlight(false);
			    		               player.setFlying(false);
			    		             }
		    		          }.runTaskLater(Main.getInstance(), 250L);
	    		          }
	    		    }
	    		}
	            
			}
		}
	}
}
