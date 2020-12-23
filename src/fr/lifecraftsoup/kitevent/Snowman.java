package fr.lifecraftsoup.kitevent;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.lifecraftsoup.utils.ItemBuilder;

public class Snowman implements Listener {
	
	public HashMap<String, Long> cooldownsnowman = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownsnowman.put(p.getName(), (long) 0);
	}
	
	@EventHandler
	  public void entityDamaged(EntityDamageByEntityEvent e) {
	    if (e.getEntity() instanceof Player && e.getDamager() instanceof Snowball) {
	      Player player = (Player)e.getEntity();
	      Snowball s = (Snowball)e.getDamager();
	      Player p = (Player)s.getShooter(); 
	      ItemStack it = p.getItemInHand();
	      if(it != null && it.hasItemMeta()) {
	    		if(it.getItemMeta().getDisplayName() == "§cSnowman" && it.getType() == Material.SNOW_BALL && p.getWorld().getName().equalsIgnoreCase("delfino")){
			      int cooldownTime = 8; 
			      if(cooldownsnowman.containsKey(p.getName())) {
			            long secondsLeft = ((cooldownsnowman.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			            if(secondsLeft > 0) {
			            	e.setCancelled(true);
			            	p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL).setDisplayName("§cSnowman").setAmount(1)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());;
			                p.sendMessage("§cVous devez patienter encore "+ secondsLeft +" secondes avant d'utiliser votre compétence.");
			            } else {
			            	cooldownsnowman.put(p.getName(), System.currentTimeMillis());
			            	player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 50));
			            	player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 50));
			            	p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL).setDisplayName("§cSnowman").setAmount(1)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());;
			            	p.sendMessage("§e» §7Vous avez gelé §6"+player.getName());
			            	p.playSound(p.getLocation(), Sound.GLASS, 1.0f, 1.0f);
			            }
			      }
		      
	    		}	 
	    	}
	    } 
	  }
}
