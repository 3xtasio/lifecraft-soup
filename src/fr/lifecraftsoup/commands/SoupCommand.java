package fr.lifecraftsoup.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.SirBlobman.combatlogx.api.ICombatLogX;
import com.SirBlobman.combatlogx.api.utility.ICombatManager;

import fr.lifecraftsoup.main.Main;

public class SoupCommand implements CommandExecutor,Listener {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player player = (Player) sender;
		ICombatLogX plugin = (ICombatLogX) Bukkit.getPluginManager().getPlugin("CombatLogX");
		ICombatManager combatManager = plugin.getCombatManager();
		if(combatManager.isInCombat(player)) {
			player.sendMessage("§cVous ne pouvez pas vous refill en combat !");
			return true;
		}
		if(Main.infos.get(player).get(0)>=20) {
			if(player.getWorld().getName().equalsIgnoreCase("delfino")) {
							for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
					            if (player.getInventory().getItem(slot) == null)
					              player.getInventory().setItem(slot, new ItemStack(Material.MUSHROOM_SOUP)); 
					            if (player.getInventory().getItem(slot).getType() == Material.BOWL)
					              player.getInventory().setItem(slot, new ItemStack(Material.MUSHROOM_SOUP)); 
					        }
							int actualcoins = Main.infos.get(player).get(0);
							Main.infos.get(player).set(0, actualcoins -20);
							Main.database.setInfos(player.getUniqueId(), Main.infos.get(player));
							player.sendMessage("§7[§cKitPvP§7] Vous avez rempli votre inventaire de soupe pour §c20 crédits");
			            } else {
		    	  player.sendMessage("§7[§cKitPvP§7] Vous devez être dans l'arène pour faire ceci.");
		    	  return true;
			            }
		    } else {
			player.sendMessage("§7[§cKitPvP§7] Vous n'avez pas assez de crédits pour régénérer vos soupes");
			return true;
		}
		return true;
	}
}
