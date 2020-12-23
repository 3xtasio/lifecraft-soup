package fr.lifecraftsoup.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lifecraftsoup.main.Main;
import fr.lifecraftsoup.mysql.PlayerInfo;


public class CreditCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		PlayerInfo playerinfo = new PlayerInfo(player);
		
		if(args.length == 0	) {
			player.sendMessage("§7[§cKitPvP§7] Vous avez actuellement §e" + Main.infos.get(player).get(0) +" §7crédits");
			return true;
		}
		
		if (args.length >= 1) {
			if(player.hasPermission("lcsoup.credit")) {
				if(args[0].equalsIgnoreCase("add")) {
					if(args.length == 3) {
						Player target = Bukkit.getPlayer(args[2]);
						if(target != null) {
							int amount = Integer.valueOf(args[1]);
							int actualcoins = Main.infos.get(target).get(0);
							Main.infos.get(target).set(0, actualcoins + amount);
							Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));
							target.sendMessage("§7[§cKitPvP§7] §9Vous avez reçu §e" + amount + " §9crédits");
							player.sendMessage("§7[§cKitPvP§7] " + target.getDisplayName() + " a reçu §c" + amount + " §7crédits" );
						}
					}
				} else if(player.hasPermission("lcsoup.credit")) {
					if(args[0].equalsIgnoreCase("remove")) {
						if(args.length == 3) {
							Player target = Bukkit.getPlayer(args[2]);
							if(target != null) {
								int amount = Integer.valueOf(args[1]);
								int actualcoins = Main.infos.get(target).get(0);
								Main.infos.get(target).set(0, actualcoins - amount);
								Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));
								target.sendMessage("§7[§cKitPvP§7] §9Vous avez perdu §e" + amount + " §9crédits");
								player.sendMessage("§7[§cKitPvP§7] " + target.getDisplayName() + " a perdu §c" + amount + " §7crédits" );
							}
						}
					}
				} else if(!player.hasPermission("lcsoup.credit") || !player.hasPermission("lcsoup.coins")) {
					player.sendMessage("§7Erreur : §cVous n'avez pas la permission");
					return true;
				}
			}	
		}
		
		return true;
	}
}