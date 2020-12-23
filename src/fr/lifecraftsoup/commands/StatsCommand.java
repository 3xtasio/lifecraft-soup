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


public class StatsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
		Player player = (Player)s;
		PlayerInfo playerInfo = new PlayerInfo(player);
		String prestigeSoupString = PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname();
		String rankSoupString = RankSoup.powerToRank(playerInfo.getRank()).getDisplayname();
		if(label.equalsIgnoreCase("stats")) {
			if(args.length == 0) {
				s.sendMessage("");
				s.sendMessage("§8-----------------");
				s.sendMessage("");
				s.sendMessage("§7Information sur §a" + player.getDisplayName());
				s.sendMessage("");
				s.sendMessage("§8• §7Rank : §e" + prestigeSoupString +  rankSoupString);
				s.sendMessage("");
				s.sendMessage("§8• §7Tués : §e" + Main.infos.get(player).get(3));
				s.sendMessage("§8• §7Morts : §e" + Main.infos.get(player).get(4));
				s.sendMessage("§8• §7Série max : §e" + Main.infos.get(player).get(5));
				s.sendMessage("");
				s.sendMessage("§8-----------------");
				s.sendMessage("");
			} else {
				Player target = Bukkit.getPlayerExact(args[0]);
				PlayerInfo targetInfo = new PlayerInfo(target);
				String prestigeSoupTarget = PrestigeSoup.powerToRank(targetInfo.getPrestige()).getDisplayname();
				String rankSoupTarget = RankSoup.powerToRank(targetInfo.getRank()).getDisplayname();
				if(target == null) {
					s.sendMessage("§cErreur: Le joueur n'est pas en ligne");
				} else {
					s.sendMessage("");
					s.sendMessage("§8-----------------");
					s.sendMessage("");
					s.sendMessage("§7Information sur §a" + target.getDisplayName());
					s.sendMessage("");
					s.sendMessage("§8• §7Rank : §e" + prestigeSoupTarget +  rankSoupTarget);
					s.sendMessage("");
					s.sendMessage("§8• §7Tués : §e" + Main.infos.get(target).get(3));
					s.sendMessage("§8• §7Morts : §e" + Main.infos.get(target).get(4));
					s.sendMessage("§8• §7Série max : §e" + Main.infos.get(target).get(5));
					s.sendMessage("");
					s.sendMessage("§8-----------------");
					s.sendMessage("");
				}	
			
			}
			
		}
		return true;
	}

	
	
}
