package fr.lifecraftsoup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import fr.lifecraftsoup.main.Main;
import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;
import fr.lifecraftsoup.utils.ItemBuilder;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;


public class JoinListener implements Listener{
	ItemStack builder = new ItemBuilder(Material.ANVIL).setDisplayName("§eRejoindre §7- §f§lLife§4§lCraft").build();
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.teleport(new Location(Bukkit.getWorld("spawnsoup"), -511.3D, 127.0D, 663.5D, -46.3F, 0));
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
	    player.setHealth(20.0);
	    player.setFoodLevel(20);
	    player.getInventory().setItem(0, builder);
        player.setAllowFlight(false);
        player.setFlying(false);
   
		Main.getInstance().database.createAccount(player.getUniqueId());
		new PlayerInfo(player);
		PlayerInfo playerInfo = new PlayerInfo(player);
		Main.infos.put(player, Main.database.getInfos(player));
		
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setItem(0, builder);
		event.setRespawnLocation(new Location(Bukkit.getWorld("spawnsoup"), -511.3D, 127.0D, 663.5D, -46.3F, 0));
	}
	

}
