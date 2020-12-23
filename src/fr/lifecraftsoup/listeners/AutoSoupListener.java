package fr.lifecraftsoup.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.lifecraftsoup.main.Main;

public class AutoSoupListener implements Listener{

	
	@EventHandler
	public void onAutoSoup(EntityDamageEvent event) {
		if(event.getEntity().getType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if(Main.autosoup.containsKey(player)) {
				player.setHealth(player.getMaxHealth());
			}
		}
	}
}
