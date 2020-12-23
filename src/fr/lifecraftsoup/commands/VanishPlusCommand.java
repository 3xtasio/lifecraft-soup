package fr.lifecraftsoup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import fr.lifecraftsoup.main.Main;

public class VanishPlusCommand implements CommandExecutor, Listener{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.hasPermission("lcsoup.vanishplus")) {
			if(args.length==0) {
				player.sendMessage("§7Veuillez insérez un argument (true/false)");
				return true;
			}
			if(args.length>=1) {
				if(args[0].equalsIgnoreCase("true")) {
					if(!Main.vanishplus.containsKey(player)) {
						player.sendMessage("§e>§7 Vous activez le Vanish+.");
						player.chat("/vanish total");
						player.setFlying(true);
						player.setAllowFlight(true);
						player.getInventory().clear();
						player.getEquipment().clear();
						Main.vanishplus.put(player, true);
					}
				}
				if(args[0].equalsIgnoreCase("false")) {
					if(Main.vanishplus.containsKey(player)) {
						player.sendMessage("§e>§7 Vous désactivez le Vanish+.");
						player.chat("/vanish total");
						player.setFlying(false);
						player.setAllowFlight(false);
						player.getInventory().clear();
						player.getEquipment().clear();
						Main.vanishplus.remove(player);	
					}
				}
			}
		} else {
			player.sendMessage("§cVous n'avez pas la permission.");
		}
		return true;
	}
	

}
