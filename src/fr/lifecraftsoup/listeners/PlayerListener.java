package fr.lifecraftsoup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.lifecraftsoup.main.Main;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class PlayerListener implements Listener{
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getType() != Material.BOWL) {
		      event.setCancelled(true); 
		} else {
			event.getItemDrop().remove();
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		
		if(player.isOp() == false) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}
	
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if(player.isOp() == false) {
			event.setCancelled(true);
		} else {
			event.setCancelled(false);
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        boolean rain = e.toWeatherState();
        if(rain) {
        		e.setCancelled(true);
        	
        }
    	
    }
  
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {
        boolean storm = event.toThunderState();
        if(storm) {
        		event.setCancelled(true);
        	
        }
    }
	
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setFoodLevel(20);
	}
	
	@EventHandler
	  public void tpportal(EntityPortalEnterEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
		    if (event.getLocation().getBlock().getType() == Material.PORTAL) {
		    	if(player.getInventory().contains(Material.ANVIL)) {
		    		player.getInventory().clear();
		    	}
		    	RandomTeleport.teleportdelfino(player);
	    		player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 80, 7));
		    }
		}
	  }
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getSlotType().equals(SlotType.ARMOR) && !event.getCurrentItem().getType().equals(Material.AIR))
            event.setCancelled(true);
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		switch (player.getLastDamageCause().getCause()) {
			case ENTITY_ATTACK:
			case FIRE:
			case FIRE_TICK:
			case PROJECTILE:
	                if (player.getKiller() == null || (!(player.getKiller() instanceof Player))) return;
					Player attacker = player.getKiller();
					event.setDeathMessage(null);
					player.sendMessage("§7[§cKitPvP§7] Vous avez été tué par §c" + attacker.getName());
					attacker.sendMessage("§7[§cKitPvP§7] Vous avez remporté §c5 crédits §7pour avoir tué §c" + player.getName());
			        event.getDrops().clear();
			        event.setDroppedExp(0);
			        if (Main.killstreaks.containsKey(player)) {
			            if (Main.infos.get(player).get(5) < Main.killstreaks.get(player)) {
			                Main.infos.get(player).set(5, Main.killstreaks.get(player));
			                Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));
			            }
			            Main.killstreaks.put(player, 0);
			        }
			        if (!Main.killstreaks.containsKey(attacker)) {
			            Main.killstreaks.put(attacker, 0);
			        }
			        int killstreak = Main.killstreaks.get(attacker) + 1;
			        Main.killstreaks.put(attacker, killstreak);
			        if (Main.infos.get(attacker).get(5) < killstreak) {
			            Main.infos.get(attacker).set(5, killstreak);
			        }
			        if (killstreak == 5 ) {
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 20);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c20 crédits§7 pour avoir réalisé une série de §65§7 kills");
			        } else if (killstreak == 10){
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 50);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c50 crédits§7 pour avoir réalisé une série de §610§7 kills");
			        } else if(killstreak == 20) {
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 100);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c100 crédits§7 pour avoir réalisé une série de §620§7 kills");
			        } else if(killstreak == 50){
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 250);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c250 crédits§7 pour avoir réalisé une série de §650§7 kills");
			        } else if(killstreak == 100){
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 500);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c500 crédits§7 pour avoir réalisé une série de §6100§7 kills");
			        } else if(killstreak == 150){
			            Bukkit.broadcastMessage("§e" + attacker.getPlayerListName() + " §ca un killstreak de §6" + killstreak);
			            Main.infos.get(attacker).set(0,Main.infos.get(attacker).get(0) + 1000);
			            attacker.sendMessage("§7[§cKitPvP§7] Vous remportez §c1000 crédits§7 pour avoir réalisé une série de §6150§7 kills");
			        }
			        
			        //AutoRespawn
			        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
							((CraftPlayer)player).getHandle().playerConnection.a(packet);
						}
					}, 40L);
			        //Actualisation des kills/morts/crédits
			        int kills = Main.infos.get(attacker).get(3) + 1;
			        int death = Main.infos.get(player).get(4) + 1;
			        int credit = Main.infos.get(attacker).get(0) + 5;
			        Main.infos.get(attacker).set(0, credit);
			        Main.infos.get(attacker).set(3, kills);
			        Main.infos.get(player).set(4, death);
			        Main.database.setInfos(attacker.getUniqueId(), Main.infos.get(attacker));  
			        Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));
			        break;
			case FALL:
			        event.getDrops().clear();
			        event.setDroppedExp(0);
			        event.setDeathMessage(null);
			        //AutoRespawn
			        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
							((CraftPlayer)player).getHandle().playerConnection.a(packet);	
						}
					}, 40L);
			        //Actualisation des morts
			        int mort = Main.infos.get(player).get(4) + 1;
			        Main.infos.get(player).set(4, mort);
			        Main.database.setInfos(player.getUniqueId(), Main.infos.get(player)); 
			        break;
			default:
		        event.getDrops().clear();
		        event.setDroppedExp(0);
		        event.setDeathMessage(null);
		        //AutoRespawn
		        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
						((CraftPlayer)player).getHandle().playerConnection.a(packet);	
					}
				}, 40L);
		        //Actualisation des morts
		        int mort1 = Main.infos.get(player).get(4) + 1;
		        Main.infos.get(player).set(4, mort1);
		        Main.database.setInfos(player.getUniqueId(), Main.infos.get(player)); 
		        break;
			}
					
		}
	
		  @EventHandler
		  public void onPickup(PlayerPickupItemEvent e) {
			  Player player = e.getPlayer();
			  if(player.isOp() == false) {
					e.setCancelled(true);
				} else {
					e.setCancelled(false);
				}
		  }
	
}
