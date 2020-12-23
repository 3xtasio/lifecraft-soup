package fr.lifecraftsoup.kitevent;


import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Kangaroo implements Listener{

	@EventHandler
	public void onKangaroo(final PlayerInteractEvent e) {
		Player p = e.getPlayer();
	    ItemStack it = p.getItemInHand();
		    if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
		    	if(it != null && it.hasItemMeta() && it.getType()==Material.FIREWORK) {
		    		e.setCancelled(true);
		    		if(it.getItemMeta().getDisplayName() == "§cKangaroo"){
				          if(p.isOnGround()== true || p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WATER || p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.STATIONARY_WATER || p.getLocation().getBlock().getType() == Material.WATER || p.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
					           if (!p.isSneaking()) {
					                 Vector unitVector = new Vector(p.getLocation().getDirection().getX(), 1.22D, p.getLocation().getDirection().getZ());
					                  unitVector = unitVector.normalize();
					                  p.setVelocity(unitVector.multiply(1.285D));
					             	} else {
					                  Vector unitVector = new Vector(p.getLocation().getDirection().getX(), 1.23D, p.getLocation().getDirection().getZ());
					                  unitVector = unitVector.normalize();
					                  p.setVelocity(unitVector.multiply(1.3D));
					              }
					        	}
				            }
				      } 
		    	}
	}
	
	
	  @EventHandler
	  public void onKangarooFall(EntityDamageEvent e) {
	    if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
	      Player p = (Player)e.getEntity();
	      ItemStack it = p.getEquipment().getChestplate();
	      if(it != null && it.hasItemMeta() && it.getType()==Material.LEATHER_CHESTPLATE) {
	    	  if(it.getItemMeta().getDisplayName() == "§cKangaroo") {
			      e.setCancelled(true); 
	    	  }
	      }
	    } 
	  }
}
