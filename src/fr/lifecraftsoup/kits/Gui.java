package fr.lifecraftsoup.kits;


import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.utils.ItemBuilder;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;


public class Gui implements InventoryProvider, Listener {


	    public static final SmartInventory INVENTORY = SmartInventory.builder()
	            .id("KitPvP")
	            .provider(new Gui())
	            .size(6, 9)
	            .title("§f§lLife§4§lCraft §7» §eKitPvP")
	            .build();


	    @Override
	    public void init(Player player, InventoryContents contents) {
	    	Potion splash = new Potion(PotionType.SPEED, 1);//poison 1
	    	splash.setSplash(true);
	    	ItemStack potion = splash.toItemStack(1);
	    	ItemMeta meta = potion.getItemMeta();
	    	meta.setDisplayName("§6Strafe");
	    	meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
	    	meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
	    	meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	    	meta.setLore(Arrays.asList("§7État : Reservé aux §2Chevaliers","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Vitesse 2"));
	    	potion.setItemMeta(meta);
	        contents.set(0, 0, ClickableItem.of(new ItemBuilder(Material.BOW).setDisplayName("§6Archer").setLore("§7État : Reservé aux Novices","§6Contenu :","§7- Armure en cuir, épée en pierre","§7- Arc puissance 3 & infinité 1","§6Abilité :","§7- Vitesse 2")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Archer")));
	        contents.set(0, 1, ClickableItem.of(new ItemBuilder(Material.IRON_SWORD).setDisplayName("§6Soldat").setLore("§7État : Reservé aux Novices","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Clique-droit vous propulse en l'air")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Soldat")));
	        contents.set(0, 2, ClickableItem.of(new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§6Knight").setLore("§7État : Reservé aux §eSoldats","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant","§6Abilité :","§7Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Knight")));
	        contents.set(0, 3, ClickableItem.of(new ItemBuilder(Material.BEACON).setDisplayName("§6Shark").setLore("§7État : Reservé aux §eSoldats","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en cuir, épée en pierre","§6Abilité :","§7- Force sous-marine")
	        		.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Shark")));
	        contents.set(0, 4, ClickableItem.of(new ItemBuilder(Material.FLINT_AND_STEEL).setDisplayName("§6Pyro").setLore("§7État : Reservé aux §6Fantassins","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en maille, épée en pierre, arc","§6Abilité :","§7- Résistance au feu")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Pyroman")));
	        contents.set(0, 5, ClickableItem.of(new ItemBuilder(Material.STICK).setDisplayName("§6Jedi").setLore("§7État : Reservé aux §6Fantassins","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en cuir, épée en pierre","§6Abilité :","§7- Clique-droit avec la force propulse tout", "§7 le monde dans un rayon de 10 blocks")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Jedi")));
	        contents.set(0, 6, ClickableItem.of(new ItemBuilder(Material.FISHING_ROD).setDisplayName("§6Fisherman").setLore("§7État : Reservé aux §6Fantassins","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant","§6Abilité :","§7- Pêche les joueurs et attire-les vers toi !")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Fisherman")));
	        contents.set(0, 7, ClickableItem.of(new ItemBuilder(Material.SUGAR).setDisplayName("§6Cheetah").setLore("§7État : Reservé aux §cMercenaires","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Vitesse féline")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Cheetah")));
	        contents.set(0, 8, ClickableItem.of(new ItemBuilder(Material.LEATHER_HELMET).setDisplayName("§6Poseidon").setLore("§7État : Reservé aux §cMercenaires","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en diamant & cuir, épée en fer","§6Abilité :","§7- Vitesse du Roi des Mers")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Poseidon")));
	        contents.set(1, 0, ClickableItem.of(new ItemBuilder(Material.CACTUS).setDisplayName("§6Cactus").setLore("§7État : Reservé aux §cMercenaires","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en mailles, épée en fer","§6Abilité :","§7- Renvoie des dégats à l'adversaire")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Cactus")));
	        contents.set(1, 1, ClickableItem.of(potion,
	                e -> giveKit(player,"Strafe")));
	        contents.set(1, 2, ClickableItem.of(new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§6Percy").setLore("§7État : Reservé aux §2Chevaliers","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Percy")));
	        contents.set(1, 3, ClickableItem.of(new ItemBuilder(Material.QUARTZ).setDisplayName("§6Eagle").setLore("§7État : Reservé aux §2Chevaliers","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer & cuir, ailes","§6Abilité :","§7- Envole-toi et survole la concurrence")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Eagle")));
	        contents.set(1, 4, ClickableItem.of(new ItemBuilder(Material.PUMPKIN).setDisplayName("§6Tank").setLore("§7État : Reservé aux §2Combattants","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en mailles & fer, épée en diamant","§6Abilité :","§7- Mort explosive..")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Tank")));
	        contents.set(1, 5, ClickableItem.of(new ItemBuilder(Material.VINE).setDisplayName("§6Dionysos").setLore("§7État : Reservé aux §2Combattants","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en cuir, épée en fer, vigne","§6Abilité :","§7- Régénération du Vin (attention à la nausée)")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Dionysos")));
	        contents.set(1, 6, ClickableItem.of(new ItemBuilder(Material.DIAMOND_AXE).setDisplayName("§6Minotaure").setLore("§7État : Reservé aux §3Gladiateurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en cuir & fer, hache en diamant","§6Abilité :","§7- Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Minotaure")));
	        contents.set(1, 7, ClickableItem.of(new ItemBuilder(Material.SUGAR_CANE).setDisplayName("§6DrugDealer").setLore("§7État : Reservé aux §3Gladiateurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en mailles, épée en pierre, weed","§6Abilité :","§7- Weed du turfu")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"DrugDealer")));
	        contents.set(1, 8, ClickableItem.of(new ItemBuilder(Material.STONE_HOE).setDisplayName("§6Demeter").setLore("§7État : Reservé aux §aSpartiate","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en cuir, une faux","§6Abilité :","§7- Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Demeter")));
	        contents.set(2, 0, ClickableItem.of(new ItemBuilder(Material.IRON_INGOT).setDisplayName("§6Prepgirl").setLore("§7État : Reservé aux §aSpartiates","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en mailles, épée en fer","§6Abilité :","§7- Clique-droit avec votre iPhone","§7 pour éblouir vos adversaires")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Prepgirl")));
	        contents.set(2, 1, ClickableItem.of(new ItemBuilder(Material.SNOW_BLOCK).setDisplayName("§6Snowman").setLore("§7État : Reservé aux §bSeigneurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant, boules de neige","§6Abilité :","§7 - Gèle ton adversaire")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Snowman")));
	        contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.WATER_LILY).setDisplayName("§6Frog").setLore("§7État : Reservé aux §bSeigneurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant, nénuphar","§6Abilité :","§7- Saut & vitesse amélioré ")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Frog")));
	        contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.POTION).setDisplayName("§6Snail").setLore("§7État : Reservé aux §4§lPourfendeurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant","§6Abilité :","§7- 20% de chances de ralentir votre adversaire ")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Snail")));
	        contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.EYE_OF_ENDER).setDisplayName("§6Viper").setLore("§7État : Reservé aux §4§lPourfendeurs","§7Tapez §6/rankup §7pour changer de grade","§7Tuez pour gagner de l'§aargent","§6Contenu :","§7- Armure en fer, épée en diamant","§6Abilité :","§7- 20% de chances d'empoisonner votre adversaire ")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Viper")));
	        contents.set(4, 0, ClickableItem.of(new ItemBuilder(Material.ENDER_PEARL).setDisplayName("§6Ninja").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en cuir, épée en diamant, enderpearl","§6Abilité :","§7- Vitesse 2 ")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Ninja")));
	        contents.set(4, 1, ClickableItem.of(new ItemBuilder(Material.FIREWORK).setDisplayName("§6Kangaroo").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en cuir, épée en fer, feu d'artifice","§6Abilité :","§7- Saut du Kangaroo")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Kangaroo")));
	        contents.set(4, 2, ClickableItem.of(new ItemBuilder(Material.FEATHER).setDisplayName("§6Hermès").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en cuir & or, pioche en or","§6Abilité :","§7- Vol pendant 15 secondes")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Hermès")));
	        contents.set(4, 3, ClickableItem.of(new ItemBuilder(Material.STONE_AXE).setDisplayName("§6Cyclope").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en cuir, hache en pierre","§6Abilité :","§7- Vision réduite mais dégâts amplifiés")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Cyclope")));
	        contents.set(4, 4, ClickableItem.of(new ItemBuilder(Material.IRON_BLOCK).setDisplayName("§6IronMan").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Vol pendant 10 secondes")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"IronMan")));
	        contents.set(4, 5, ClickableItem.of(new ItemBuilder(Material.ANVIL).setDisplayName("§6Stomper").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en or & cuir, épée en pierre","§6Abilité :","§7- Donne des dégâts de chutes","§7 aux adversaires proches")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Stomper")));
	        contents.set(4, 6, ClickableItem.of(new ItemBuilder(Material.SNOW_BALL).setDisplayName("§6Switcher").setLore("§7État : Reservé aux §bVIP","§6Contenu :","§7- Armure en fer, épée en fer","§6Abilité :","§7- Echange sa position avec l'adversaire")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Switcher")));
	        contents.set(4, 7, ClickableItem.of(new ItemBuilder(Material.GOLD_SWORD).setDisplayName("§6Arès").setLore("§7État : Reservé aux §bVIP§c+","§6Contenu :","§7- Armure en diamant & maille, épée en fer","§6Abilité :","§7- Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Arès")));
	        contents.set(4, 8, ClickableItem.of(new ItemBuilder(Material.BONE).setDisplayName("§6Rhino").setLore("§7État : Reservé aux §bVIP§c+","§6Contenu :","§7- Armure fer, os","§6Abilité :","§7- Aucune")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Rhino")));
	        contents.set(5, 0, ClickableItem.of(new ItemBuilder(Material.ARROW).setDisplayName("§6Bumblebee").setLore("§7État : Reservé aux §bVIP§c+","§6Contenu :","§7- Armure en cuir, arc, épée","§6Abilité :","§7- Donne des effets aléatoires à l'adversaire")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Bumblebee")));
	        contents.set(5, 1, ClickableItem.of(new ItemBuilder(Material.GOLD_SWORD).setDisplayName("§6LuckyHit").setLore("§7État : Reservé aux §bVIP§c+","§6Contenu :","§7- Armure en or & fer, épée en or","§6Abilité :","§7- Roulette, donne des dégâts au hasard à l'adversaire")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"LuckyHit")));
	        contents.set(5, 2, ClickableItem.of(new ItemBuilder(Material.PAPER).setDisplayName("§6Ghost").setLore("§7État : Reservé aux §bVIP§c+","§6Contenu :","§7- Epée en diamant","§6Abilité :","§7- Deviens invisible quand tu le souhaites")
					.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build(),
	                e -> giveKit(player,"Ghost")));
	        contents.set(5, 8, ClickableItem.of(new ItemBuilder(Material.BARRIER).setDisplayName("§cFermer").build(),
	                e -> player.closeInventory()));
	        contents.fillRow(3, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§c").build(),
	                e -> player.closeInventory()));
	    }

	    
	    public void fillsoup(Player player) {
	    	for (int slot = 0; slot < player.getInventory().getSize(); slot++) {
	            if (player.getInventory().getItem(slot) == null) {
		              player.getInventory().setItem(slot, new ItemStack(Material.MUSHROOM_SOUP));
	            }
	    	}
	    }
	    public void giveKit(Player player, String kitname) {
	    	PlayerInfo playerInfo = new PlayerInfo(player);
	    		if(kitname.equalsIgnoreCase("Archer")) {
	    				if(playerInfo.getRank()>=0) {
				    		player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("§cArc").addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_DAMAGE, 3)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(9, new ItemBuilder(Material.ARROW).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.MAROON)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.GREEN)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.YELLOW)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
								.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit Archer");
							player.closeInventory();
	    				}
	    			}
			   		if(kitname.equalsIgnoreCase("Soldat")) {
			   			if(playerInfo.getRank()>=0) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée du Soldat")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).setDisplayName("§eSoldat")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §eSoldat");
							player.closeInventory();
			   			 } 
			   		 }
		    		if(kitname.equalsIgnoreCase("Knight")) {
		    			if(playerInfo.getRank()>=1) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit Knight");
							player.closeInventory();
				    	} else {
						   	player.closeInventory();
						   	player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §eSoldat §7pour utiliser ce kit.");
				    	}
		    		 }
			   		 if(kitname.equalsIgnoreCase("Shark")) {
			   			if(playerInfo.getRank()>=1) {
			   				player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.BLUE)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.WHITE)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.WHITE)
								.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§eBottes du Shark").setColor(Color.BLUE)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §6Shark");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §eSoldat §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Jedi")) {
			   			if(playerInfo.getRank()>=2) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.TRIPWIRE_HOOK).setDisplayName("§6Jedi")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §6Jedi");
							player.closeInventory();
				    	} else {
				    		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §6Fantassin §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Fisherman")) {
			   			if(playerInfo.getRank()>=2) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.FISHING_ROD).setDisplayName("§cCanne à pêche")
									.addEnchant(Enchantment.KNOCKBACK, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §eFisherman");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §6Fantassin §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Pyroman")) {
			   			if(playerInfo.getRank()>=2) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.FIRE_ASPECT, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("§cArc")
									.addEnchant(Enchantment.ARROW_FIRE, 2).addEnchant(Enchantment.ARROW_INFINITE, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(9, new ItemBuilder(Material.ARROW)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §6Pyroman");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §6Fantassin §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Cactus")) {
			   			if(playerInfo.getRank()>=3) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.CACTUS)
									.addEnchant(Enchantment.THORNS, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addEnchant(Enchantment.THORNS, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
									.addEnchant(Enchantment.THORNS, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS)
									.addEnchant(Enchantment.THORNS, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §cCactus");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §cMercenaire §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Poseidon")) {
			   			if(playerInfo.getRank()>=3) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eTrident")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.fromRGB(0, 0, 204))
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.setColor(Color.fromRGB(0, 0, 204)).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §cPoseidon");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §cMercenaire §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Cheetah")) {
			   			if(playerInfo.getRank()>=3) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.SUGAR).setDisplayName("§cVitesse")
									.setLore("§7Clique-droit pour activer la vitesse").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §cCheetah");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §cMercenaire §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Strafe")) {
			   			if(playerInfo.getRank()>=4) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §2Strafe");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §2Chevalier §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Percy")) {
			   			if(playerInfo.getRank()>=4) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§cTurbulences")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.addEnchant(Enchantment.DEPTH_STRIDER, 1).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §2Percy");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §2Chevalier §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Eagle")) {
			   			if(playerInfo.getRank()>=4) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.QUARTZ).setDisplayName("§eAiles")
									.addEnchant(Enchantment.DAMAGE_ALL, 6).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("§eAiles")
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §2Eagle");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §2Chevalier §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Tank")) {
			   			if(playerInfo.getRank()>=5) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée du Tank")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).setDisplayName("§eTank")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §9Tank");
							player.closeInventory();
				    	} else {
				    		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §9Combattant §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Dionysos")) {
			   			if(playerInfo.getRank()>=5) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.VINE).setDisplayName("§aVignes").setLore("§7Clique-droit pour te soigner")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §9Dionysos");
							player.closeInventory();
				    	} else {
				    		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §9Combattant §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Minotaure")) {
			   			if(playerInfo.getRank()>=6) {
			   				ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			   				SkullMeta im = (SkullMeta) item.getItemMeta();
			   				im.setOwner("MHF_Cow");
			   				im.setDisplayName("§eTête de Minotaure");
			   				item.setItemMeta(im);
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.GOLD_AXE).setDisplayName("§eHache")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(item);
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.MAROON)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.MAROON)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.MAROON)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
							player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §3Minotaure");
							player.closeInventory();
				    	} else {
				    		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §3Gladiateur §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("DrugDealer")) {
			   			if(playerInfo.getRank()>=6) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.SUGAR_CANE).setDisplayName("§2Weed").setLore("§7Tire dessus frérot..")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §3DrugDealer");
							player.closeInventory();
				    	} else {
				    		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §3Gladiateur §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Demeter")) {
			   			if(playerInfo.getRank()>=7) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_HOE).setDisplayName("§eFaux")
									.addEnchant(Enchantment.DAMAGE_ALL, 5).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.GREEN)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.GREEN)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.GREEN)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.GREEN)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §aDemeter");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §aSpartiate §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Prepgirl")) {
			   			if(playerInfo.getRank()>=7) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.IRON_INGOT).setDisplayName("§ciPhone 7s")
									.setLore("§7Clique-droit pour aveugler","§7les autres joueurs").addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.WHITE)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.WHITE)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.WHITE)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.WHITE)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §aPrepgirl");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §aSpartiate §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Snowman")) {
			   			if(playerInfo.getRank()>=8) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.SNOW_BALL).setAmount(16).setDisplayName("§cSnowman")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.PUMPKIN)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §aSnowman");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bSeigneur §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Frog")) {
			   			if(playerInfo.getRank()>=8) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.WATER_LILY).setDisplayName("§cNénuphar")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bFrog");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bSeigneur §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Snail")) {
			   			if(playerInfo.getRank()==9) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée du Snail")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §4§lSnail");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §4§lPourfendeur §7pour utiliser ce kit.");
				    	}
			   		 }	
			   		 if(kitname.equalsIgnoreCase("Viper")) {
			   			if(playerInfo.getRank()==9) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée du Viper")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §4§lViper");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §4§lPourfendeur §7pour utiliser ce kit.");
				    	}
			   		 }	
			   		 if(kitname.equalsIgnoreCase("Ninja")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.ENDER_PEARL).setAmount(16)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.BLACK)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.BLACK)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.BLACK)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.BLACK)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bNinja");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Kangaroo")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.FIREWORK).setDisplayName("§cKangaroo")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setDisplayName("§cKangaroo")
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
									.addEnchant(Enchantment.PROTECTION_FALL, 4).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 3));
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bKangaroo");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Hermès")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_PICKAXE).setDisplayName("§ePioche")
									.addEnchant(Enchantment.DAMAGE_ALL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER).setDisplayName("§cHermès")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS)
									.addEnchant(Enchantment.PROTECTION_FALL, 4).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bHermès");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		 if(kitname.equalsIgnoreCase("Cyclope")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_AXE).setDisplayName("§eHache")
									.addEnchant(Enchantment.KNOCKBACK, 4).addEnchant(Enchantment.DAMAGE_ALL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.DISPENSER)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS)
									.addEnchant(Enchantment.THORNS, 1).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).addEnchant(Enchantment.PROTECTION_FALL, 4).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bCyclope");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("IronMan")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.IRON_BLOCK).setDisplayName("§cIronMan")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.addEnchant(Enchantment.PROTECTION_FALL, 4).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bIronMan");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Stomper")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.KNOCKBACK, 1).addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.GOLD_CHESTPLATE).setDisplayName("§eStomper")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.GOLD_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bStomper");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Switcher")) {
			   			if(player.hasPermission("lcsoup.vip")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.SNOW_BALL).setDisplayName("§cSwitcher").setAmount(16)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bSwitcher");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Arès")) {
			   			if(player.hasPermission("lcsoup.vipplus")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bArès");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP§c+ §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Rhino")) {
			   			if(player.hasPermission("lcsoup.vipplus")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.BONE).setDisplayName("§eCorne")
									.addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.KNOCKBACK, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bRhino");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP§c+ §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Bumblebee")) {
			   			if(player.hasPermission("lcsoup.vipplus")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§eEpée")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("§eArc du Bumblebee")
									.addEnchant(Enchantment.ARROW_DAMAGE, 3).addEnchant(Enchantment.ARROW_INFINITE, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(9, new ItemBuilder(Material.ARROW)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.YELLOW)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.BLACK)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.YELLOW)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.BLACK)
									.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bBumblebee");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP§c+ §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("LuckyHit")) {
			   			if(player.hasPermission("lcsoup.vipplus")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.GOLD_SWORD).setDisplayName("§eRoulette")
									.addEnchant(Enchantment.DAMAGE_ALL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.GOLD_LEGGINGS)
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS)
									.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
							player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bLuckyHit");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP§c+ §7pour utiliser ce kit.");
				    	}
			   		 }
			   		if(kitname.equalsIgnoreCase("Ghost")) {
			   			if(player.hasPermission("lcsoup.vipplus")) {
					    	player.getInventory().clear();
							player.getEquipment().clear();
							clearEffect(player);
							player.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setDisplayName("§eEpée")
									.addEnchant(Enchantment.DAMAGE_ALL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setItem(1, new ItemBuilder(Material.PAPER).setDisplayName("§aInvisibilité").setLore("§e» §6Clique-droit pour devenir invisible")
									.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
							player.getInventory().setHelmet(new ItemBuilder(Material.AIR).build());
							player.getInventory().setChestplate(new ItemBuilder(Material.AIR).build());
							player.getInventory().setLeggings(new ItemBuilder(Material.AIR).build());
							player.getInventory().setBoots(new ItemBuilder(Material.AIR).build());
							fillsoup(player);
							player.sendMessage("§7[§cKitPvP§7] Vous avez reçu le kit §bGhost");
							player.closeInventory();
				    	} else {
					   		player.closeInventory();
					   		player.sendMessage("§7[§cKitPvP§7] Vous devez être au minimum §bVIP§c+ §7pour utiliser ce kit.");
				    	}
			   		 }
   	}

		@Override
	    public void update(Player player, InventoryContents contents) {

	    }
	    @EventHandler
		public void onInteract(PlayerInteractEvent event){
	    	ItemStack it = event.getItem();
			Player player = event.getPlayer();
		    if(it != null && it.getType() == Material.ANVIL) {
		    	if(it.hasItemMeta()) {
		    		if(it.getItemMeta().getDisplayName().equalsIgnoreCase("§eRejoindre §7- §f§lLife§4§lCraft") && player.getWorld().getName().equals("spawnsoup")) {
					    Gui.INVENTORY.open(player);
		    		}
		    	}
		    }
	    }
	    
	    public void clearEffect(Player player) {
	    	for (PotionEffect potionEffect : player.getActivePotionEffects()) {
	            player.removePotionEffect(potionEffect.getType()); 
	        } 
	    }
		
	    
	    
}

