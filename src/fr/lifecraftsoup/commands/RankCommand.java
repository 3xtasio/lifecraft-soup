package fr.lifecraftsoup.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.query.QueryOptions;



public class RankCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		if(args.length == 0) {
			player.sendMessage("§7Erreur : §cVeuillez faire /rank help");
			return true;
		}
		if(args.length >= 1) {
			if(player.hasPermission("lcsoup.rank")) {
				if(args[0].equalsIgnoreCase("set")) {
					Player target = Bukkit.getPlayer(args[1]);
					int power = Integer.valueOf(args[2]);
					PlayerInfo playerinfo = PlayerInfo.getInfoPlayer(target);
					playerinfo.setRank(power);
					player.sendMessage("§7Le joueur §e" + target.getName() + " §7est désormais " + RankSoup.powerToRank(playerinfo.getRank()).getDisplayname());
					target.sendMessage("§7Vous êtes désormais "+ RankSoup.powerToRank(playerinfo.getRank()).getDisplayname());
				}
				if(args[0].equalsIgnoreCase("help")) {
					player.sendMessage("§7---------------------");
					player.sendMessage("§e- §7/rank set §6| §aPermet de définir le rank d'un joueur");
					player.sendMessage("§e- §7/rank info §6| §aPermet de voir le rank d'un joueur");
					player.sendMessage("§e- §7/rank list §6| §aPermet de voir la liste des ranks");
					player.sendMessage("§7---------------------");
				}
				if(args[0].equalsIgnoreCase("info")) {
					if(args[1] != null) {
						Player target = Bukkit.getPlayer(args[1]);
						PlayerInfo playerinfo = PlayerInfo.getInfoPlayer(target);
						player.sendMessage("§7Le joueur §e" + target.getName() + " §7est " + RankSoup.powerToRank(playerinfo.getRank()).getDisplayname());
					}
				}
				if(args[0].equalsIgnoreCase("list")) {
					player.sendMessage("§7Liste des ranks:");
					player.sendMessage("§7ID: §e0 §7- §7Novice (par défaut)");
					player.sendMessage("§7ID: §e1 §7- §eSoldat");
					player.sendMessage("§7ID: §e2 §7- §6Fantassin");
					player.sendMessage("§7ID: §e3 §7- §cMercenaire");
					player.sendMessage("§7ID: §e4 §7- §2Chevalier");
					player.sendMessage("§7ID: §e5 §7- §9Combattant");
					player.sendMessage("§7ID: §e6 §7- §3Gladiateur");
					player.sendMessage("§7ID: §e7 §7- §aSpartiate");
					player.sendMessage("§7ID: §e8 §7- §bSeigneur");
					player.sendMessage("§7ID: §e9 §7- §4Pourfendeur");
				}
			}
		}else if(!player.hasPermission("lcsoup.rank")) {
			player.sendMessage("§7Erreur : §cVous n'avez pas la permission");
			return true;
		
		}
		
		return true;
	}

}
