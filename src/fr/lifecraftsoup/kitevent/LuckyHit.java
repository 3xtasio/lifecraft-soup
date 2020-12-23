package fr.lifecraftsoup.kitevent;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class LuckyHit implements Listener{

	public HashMap<String, Long> cooldownluckyhit = new HashMap<String, Long>();
	 
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
	   Player p = e.getPlayer();
	   cooldownluckyhit.put(p.getName(), (long) 0);
	}
	
	@EventHandler
	public void onLuckyHit(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player victim = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			if(event.getCause()== DamageCause.ENTITY_ATTACK) {
				ItemStack it = damager.getItemInHand();
			    if(it != null && it.hasItemMeta()) {
			    	if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§eRoulette") && damager.getWorld().getName().equalsIgnoreCase("delfino")) {
					    Random rdm = new Random();
			    		int cooldownTime =  6+rdm.nextInt(15-6);
			    		if(cooldownluckyhit.containsKey(damager.getName())) {
			    			long secondsLeft = ((cooldownluckyhit.get(damager.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
			    			if(secondsLeft > 0) {
				            } else { 
							    Random r = new Random();
							    int rand = r.nextInt(6);
							    Firework f = (Firework)victim.getWorld().spawn(new Location(victim.getWorld(), victim.getLocation().getX(), victim.getLocation().getY() - 1.0, victim.getLocation().getZ()), (Class)Firework.class);
				                FireworkMeta meta = f.getFireworkMeta();
							    switch (rand) {
			                    	case 1:
				                        victim.damage(4.0);
				                        meta.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.TEAL).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.GREEN).build());
				                        damager.sendMessage("§7» §aRoulette §7- §64 de dégâts§e infligés à l'adversaire");
				                        cooldownluckyhit.put(damager.getName(), System.currentTimeMillis());
				                        break;
				                    case 2:
				                        victim.damage(5.0);
				                        meta.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.LIME).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.GREEN).build());
				                        damager.sendMessage("§7» §aRoulette §7- §65 de dégâts§e infligés à l'adversaire");
				                        cooldownluckyhit.put(damager.getName(), System.currentTimeMillis());
				                        break;
				                    case 3: 
				                        victim.damage(6.0);
				                        meta.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.FUCHSIA).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.PURPLE).build());
				                        damager.sendMessage("§7» §aRoulette §7- §66 de dégâts§e infligés à l'adversaire");
				                        cooldownluckyhit.put(damager.getName(), System.currentTimeMillis());
				                        break;
				                    case 4:
				                        victim.damage(7.0);
				                        meta.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.AQUA).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.TEAL).build());
				                        damager.sendMessage("§7» §aRoulette §7- §67 de dégâts§e infligés à l'adversaire");
				                        cooldownluckyhit.put(damager.getName(), System.currentTimeMillis());
				                        break;
				                    case 5:
				                        victim.damage(8.0);
				                        meta.addEffect(FireworkEffect.builder().flicker(true).trail(true).withColor(Color.YELLOW).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.ORANGE).build());
				                        damager.sendMessage("§7» §aRoulette §7- §68 de dégâts§e infligés à l'adversaire");
				                        cooldownluckyhit.put(damager.getName(), System.currentTimeMillis());
				                        break;
				                    default:
				                    	f.remove();
				                    	return;
							    }
				                meta.setPower(2);
				                f.setFireworkMeta(meta);
				            }
			    		}
			    		
			    	}
			    } 
			}
		}
	}
}
