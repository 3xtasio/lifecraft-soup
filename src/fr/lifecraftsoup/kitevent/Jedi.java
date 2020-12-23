package fr.lifecraftsoup.kitevent;


import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Jedi implements Listener{
	public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		cooldowns.put(p.getName(), (long) 0);
	}
		
	@EventHandler
	public void jedi(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
	    ItemStack it = p.getItemInHand();
	    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
	    	if(it != null && it.hasItemMeta() && it.getType()==Material.TRIPWIRE_HOOK) {
	    		if(it.getItemMeta().getDisplayName() == "§6Jedi" && p.getWorld().getName().equalsIgnoreCase("delfino")){
			      int cooldownTime = 20; 
			      if(cooldowns.containsKey(p.getName())) {
			            long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft > 0) {
			                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
			            } else {
			        		for (final Entity entity : p.getNearbyEntities(10.0, 10.0, 10.0)) {
			        			if (entity instanceof Player && p.canSee((Player) entity)) {
			        				final Player nrb = (Player) entity;
			        				p.playSound(p.getLocation(),  Sound.EXPLODE, 1.0f, 0.0f);
			        				nrb.playSound(p.getLocation(),  Sound.EXPLODE, 1.0f, 0.0f);
			        				nrb.sendMessage("§7»§6 " + p.getDisplayName() +" §eutilise la force !");
			        				Vector v = new Vector(0.0, 1.25, 0.0);
			        				final Vector direction = nrb.getLocation().toVector().subtract(p.getLocation().toVector()).normalize();
			        				direction.setX(direction.getX() * 1.0);
			        				direction.setY(direction.getY() * 1.0);
			        				direction.setZ(direction.getZ() * 2.5);
			        				nrb.setVelocity(direction.add(v));
			        				nrb.playSound(nrb.getLocation(), Sound.EXPLODE, 1.0f, 0.0f);
			        			}
			        		}
			            	cooldowns.put(p.getName(), System.currentTimeMillis());
			        		p.sendMessage("§7» §eVous utilisez la force !");
			        		p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 0.0f);
			            }
			      }
		      
	    		}	 
	    	}
	    }

		
	}
}
