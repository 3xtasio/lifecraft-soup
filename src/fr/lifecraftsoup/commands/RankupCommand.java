package fr.lifecraftsoup.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lifecraftsoup.main.Main;
import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;

public class RankupCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(sender instanceof Player) {
			PlayerInfo playerInfo = new PlayerInfo(player);
			switch(playerInfo.getRank()) {
				case 0:
					if(playerInfo.getRank() == 0) {
						rankup(player, 20, 0, 1);
						break;
					}
				case 1:
					if(playerInfo.getRank() == 1) {
						rankup(player, 200, 1, 2);
						break;
					}
				case 2:
					if(playerInfo.getRank() == 2) {
						rankup(player, 400, 2, 3);
						break;
					}
				case 3:
					if(playerInfo.getRank() == 3) {
						rankup(player, 800, 3, 4);
						break;
					}
				case 4:
					if(playerInfo.getRank() == 4) {
						rankup(player, 1000, 4, 5);
						break;
					}
				case 5:
					if(playerInfo.getRank() == 5) {
						rankup(player, 2000, 5, 6);
						break;
					}
				case 6:
					if(playerInfo.getRank() == 6) {
						rankup(player, 3000, 6, 7);
						break;
					}
				case 7:
					if(playerInfo.getRank() == 7) {
						rankup(player, 4000, 7, 8);
						break;
					}
				case 8:
					if(playerInfo.getRank() == 8) {
						rankup(player, 6000, 8, 9);
						break;
					}
				case 9:
					if(playerInfo.getRank() == 9) {
						prestigeup(player, 10000, 9, playerInfo.getPrestige(), playerInfo.getPrestige()+1);
						break;
					}
			}
		}
		return false;
	}

	
	public void rankup(Player player, int credit, int actualRank, int nextRank) {
		PlayerInfo playerInfo = new PlayerInfo(player);
		if(playerInfo.getCoinsNumber() >= credit) {
			if(playerInfo.getRank() <= 8) {
				if(nextRank == actualRank+1) {
					int actualcoins = Main.infos.get(player).get(0);
					Main.infos.get(player).set(0, actualcoins - credit);
					Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));// On retire l'argent requis
					playerInfo.setRank(nextRank); // On lui met son nouveau rank
					player.sendMessage("§7[§cKitPvP§7] Vous passez au rang " + PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname() + RankSoup.powerToRank(nextRank).getDisplayname());
					Bukkit.broadcastMessage("§7[§cKitPvP§7] §a"+ player.getName() + " §7vient de passer " + PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname() + RankSoup.powerToRank(nextRank).getDisplayname() );
				}
			}
		} else {
			player.sendMessage("§7[§cKitPvP§7] §cVous n'avez pas assez de crédit pour passer au rang " + RankSoup.powerToRank(nextRank).getDisplayname() + " §7(Requis: §e"+credit+" §7crédits)");
		}	
	}
	
	public void prestigeup(Player player, int credit, int actualRank, int actualPrestige, int nextPrestige) {
		PlayerInfo playerInfo = new PlayerInfo(player);
		if(playerInfo.getCoinsNumber() >= credit) {
			if(playerInfo.getRank() == 9) {
				if(playerInfo.getPrestige() <= 10) {
					if(nextPrestige == actualPrestige+1) { // On verifie si son prestige
						int actualcoins = Main.infos.get(player).get(0);
						Main.infos.get(player).set(0, actualcoins - credit);
						Main.database.setInfos(player.getUniqueId(), Main.infos.get(player)); // On retire l'argent requis
						playerInfo.setPrestige(nextPrestige); // On lui met son nouveau prestige
						playerInfo.setRank(1); // On lui reset son rank
						player.sendMessage("§7[§cKitPvP§7] Vous passez prestige " + PrestigeSoup.powerToRank(nextPrestige).getDisplayname());
						Bukkit.broadcastMessage("§7[§cKitPvP§7] §a"+ player.getName() + " §7vient de passer " + PrestigeSoup.powerToRank(nextPrestige).getDisplayname());
					}
				} else {
					player.sendMessage("§7[§cKitPvP§7] §cVous avez atteint le prestige maximum, vous ne pouvez pas rankup");
				}
			}
		} else {
			player.sendMessage("§7[§cKitPvP§7] §cVous n'avez pas assez de crédit pour passer prestige " + PrestigeSoup.powerToRank(nextPrestige).getDisplayname() + " §7(Requis: §e"+credit+" §7crédits)");
		}	
	}
	
	
	
	
}
