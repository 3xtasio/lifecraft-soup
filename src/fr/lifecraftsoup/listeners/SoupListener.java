package fr.lifecraftsoup.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SoupListener implements Listener {
	
	@EventHandler
	public void onSoup(PlayerInteractEvent event) {
		Player player = event.getPlayer(); //Récupère le joueur
		ItemStack item = player.getItemInHand(); //Récupère l'item dans la main du joueur
		
		if(item != null && item.getType().equals(Material.MUSHROOM_SOUP)) { 
			if(player.getHealth()!= 20) {
	            if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
	            	if(player.getHealth() + 6.5F > 20) { //Si le joueur a + de 20HP avec la soup, on lui donne son max de vie
	            		player.setHealth(player.getMaxHealth());
	            	} else { //Sinon on le heal de 6.5C
	            		player.setHealth(player.getHealth() + 6.5F);
	            	}
	                player.getItemInHand().setType(Material.BOWL);;
	    		
	            }
			}
		}
		
	}
	
	

}
