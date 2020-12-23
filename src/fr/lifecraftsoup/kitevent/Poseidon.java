package fr.lifecraftsoup.kitevent;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Poseidon implements Listener{
	@EventHandler
    public void onPoseidon(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		ItemStack it = p.getItemInHand();
		if(it != null && it.hasItemMeta()) {
    		if(it.getItemMeta().getDisplayName() == "§eTrident"){
		        if (p.getLocation().getBlock().getRelative(BlockFace.UP).getType() == Material.WATER || p.getLocation().getBlock().getRelative(BlockFace.UP).getType() == Material.STATIONARY_WATER || p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
		        	 p.removePotionEffect(PotionEffectType.SPEED);
		             p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
		        	 p.removePotionEffect(PotionEffectType.WATER_BREATHING);
		             p.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 30, 0));
		             p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1));
		             p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 0));
		        }
		        
        	}
        }
    }

}
