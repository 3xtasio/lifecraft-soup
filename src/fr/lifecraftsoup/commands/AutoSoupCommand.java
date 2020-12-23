package fr.lifecraftsoup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lifecraftsoup.main.Main;

public class AutoSoupCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("lcsoup.autosoup")) {
			if(args.length==0) {
				player.sendMessage("§7Veuillez insérez un argument (true/false)");
				return true;
			}
			if(args.length>=1) {
				if(args[0].equalsIgnoreCase("true")) {
					if(!Main.autosoup.containsKey(player)) {
						player.sendMessage("§e>§7 Vous activez l'autosoup.");
						Main.autosoup.put(player, true);
					}
				}
				if(args[0].equalsIgnoreCase("false")) {
					if(Main.autosoup.containsKey(player)) {
						player.sendMessage("§e>§7 Vous désactivez l'autosoup.");
						Main.autosoup.remove(player);	
					}
				}
			}
		} else {
			player.sendMessage("§cVous n'avez pas la permission.");
		}
		return true;
	}

}
