package fr.lifecraftsoup.kitevent;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class Fisherman implements Listener{

	
	
	/*
	 * FISHERMAN
	 */
	@EventHandler
    public void onFishPlayer(final PlayerFishEvent e) {
        final Player p = e.getPlayer();
        if (!(e.getCaught() instanceof Player)) {
            return;
        }
        final Player v = (Player)e.getCaught();
        if (p.getWorld() != v.getWorld()) {
            e.setCancelled(true);
            return;
        }
        v.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), v.getLocation().getYaw(), v.getLocation().getPitch()));
    }
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onFish(final PlayerFishEvent e) {
        final Player p = e.getPlayer();
        if (e.getCaught() instanceof Item) {
            final Item i = (Item)e.getCaught();
            if (i.getItemStack().getType() != Material.RAW_FISH) {
            	e.setCancelled(true);
                return;
            }
            if (i.getItemStack().getData().getData() == 3) {
            	e.setCancelled(true);
                return;
            }
            p.getInventory().addItem(new ItemStack[] { new ItemStack(i.getItemStack().getType(), 1, (short)i.getItemStack().getData().getData()) });
            p.setFoodLevel(p.getFoodLevel() - 1);
        }
    }
    
    @EventHandler
    public void onEat(final PlayerItemConsumeEvent e) {
        if (e.getItem().getType() != Material.RAW_FISH) {
            return;
        }
        e.getPlayer().setFoodLevel(20);
    }
    
    
    
    
    
}
