package fr.lifecraftsoup.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.lifecraftsoup.kits.Gui;
import fr.lifecraftsoup.main.Main;
import fr.lifecraftsoup.utils.ItemBuilder;

public class MenuCommand implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(sender instanceof Player) {
			if(player.getWorld().getName().equals("spawnsoup")) {
				player.getInventory().clear();
		    	player.getEquipment().clear();
		    	clearEffect(player);
		    	player.getInventory().setHelmet(new ItemBuilder(Material.AIR).build());
				player.getInventory().setChestplate(new ItemBuilder(Material.AIR).build());
				player.getInventory().setLeggings(new ItemBuilder(Material.AIR).build());
				player.getInventory().setBoots(new ItemBuilder(Material.AIR).build());
				Gui.INVENTORY.open(player);
			}
		}
		return true;
	}
	
    public void clearEffect(Player player) {
    	for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType()); 
        } 
    }
}
