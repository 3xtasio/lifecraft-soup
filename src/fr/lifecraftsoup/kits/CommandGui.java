package fr.lifecraftsoup.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;

import fr.lifecraftsoup.utils.ItemBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

public class CommandGui implements InventoryProvider, Listener{

	 public static final SmartInventory INVENTORY = SmartInventory.builder()
	            .id("Infocmd")
	            .provider(new CommandGui())
	            .size(3, 9)
	            .title("§7» §6Commandes")
	            .build();
	 
	 
	 public void init(Player player, InventoryContents contents) {
	    	contents.set(1, 0, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(), 
	    			e-> player.closeInventory()));
	    	contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(), 
	    			e-> player.closeInventory()));
	    	contents.set(1, 8, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(), 
	    			e-> player.closeInventory()));
	        contents.set(1, 1, ClickableItem.of(new ItemBuilder(Material.BOOK).setDisplayName("§6Commandes").setLore("§7Voici la liste des commandes","§7 du PvPSoup")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.closeInventory()));
	        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.BOWL).setDisplayName("§cSoup").setLore("§7- §a/soup","§7Rempli votre inventaire de soup","§7Coût: §620$")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.closeInventory()));
	        contents.set(1, 4, ClickableItem.of(new ItemBuilder(Material.PAPER).setDisplayName("§eStatistiques").setLore("§7- §a/stats §9<pseudo>","§7Affiche les statistiques d'un joueur")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.chat("/stats")));
	        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.GOLD_INGOT).setDisplayName("§eMonnaie").setLore("§7- §a/credit","§7Affiche ta monnaie")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.chat("/credit")));
	        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.EMERALD).setDisplayName("§bRankup").setLore("§7- §a/rankup","§7Passe un rang")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.chat("/rankup")));
	        contents.set(1, 7, ClickableItem.of(new ItemBuilder(Material.BONE).setDisplayName("§dClassement").setLore("§7- §a/classement §6[mort|kill]","§7Affiche le classement des morts")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> player.closeInventory()));
	        contents.fillRow(2, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(),
	                e -> player.closeInventory()));
	        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(),
	                e -> player.closeInventory()));
	    }


	@Override
	public void update(Player arg0, InventoryContents arg1) {
		
	}
}
