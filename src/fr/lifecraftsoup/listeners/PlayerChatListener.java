package fr.lifecraftsoup.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;


public class PlayerChatListener  implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		LuckPerms api = LuckPermsProvider.get();
		User user = api.getUserManager().getUser(player.getUniqueId());
		QueryOptions queryOptions = api.getContextManager().getQueryOptions(player);
		CachedMetaData metaData = user.getCachedData().getMetaData(queryOptions);

		event.setCancelled(true);
		
		if(event.getMessage() != null) {
			if(metaData.getPrimaryGroup().equalsIgnoreCase("default")) {
				event.setFormat("§7[§cKitPvP§7] "+ PlaceholderAPI.setPlaceholders(player, "%LCSoup_rank_color%"+ PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname() + RankSoup.powerToRank(playerInfo.getRank()).getDisplayname()) + player.getName() + " §7» " + event.getMessage());
			}  else if(metaData.getPrimaryGroup().equalsIgnoreCase("hub_pvpsoup_pvpbox_vip") || metaData.getPrimaryGroup().equalsIgnoreCase("hub_pvpsoup_pvpbox_vip+") ) {
				event.setFormat("§7[§cKitPvP§7] "+ PlaceholderAPI.setPlaceholders(player, metaData.getPrefix().replaceAll("&", "§") + "%LCSoup_rank_color%" +PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname() + RankSoup.powerToRank(playerInfo.getRank()).getDisplayname()) + player.getName() + " §7» " + event.getMessage());
			} else {
				event.setFormat("§7[§cKitPvP§7] "+ PlaceholderAPI.setPlaceholders(player, "%LCSoup_rank_color%"+ PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname() + "§7["+ RankSoup.powerToRank(playerInfo.getRank()).getColor() +"*§7] " +metaData.getPrefix().replaceAll("&", "§")) + player.getName() + " §7» " + event.getMessage());
			}
			
			for (Player players: Bukkit.getOnlinePlayers()){
				players.sendMessage(event.getFormat());
			}
		}
		
	}
}
