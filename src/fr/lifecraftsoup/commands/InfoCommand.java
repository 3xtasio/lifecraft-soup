package fr.lifecraftsoup.commands;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import fr.lifecraftsoup.kits.CommandGui;
import fr.lifecraftsoup.kits.Gui;
import fr.lifecraftsoup.utils.ItemBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;

public class InfoCommand implements CommandExecutor{
	 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player.getWorld().getName().equalsIgnoreCase("spawnsoup")) {
			 CommandGui.INVENTORY.open(player);
		}
		return true;
	}
	
	
}
