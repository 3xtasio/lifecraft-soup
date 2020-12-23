package fr.lifecraftsoup.listeners.scoreboard;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;

import fr.lifecraftsoup.main.Main;
import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;
import me.clip.placeholderapi.PlaceholderAPI;

public class ScoreboardPlayer implements Listener {
	
	public Map<Player, ScoreboardSign> boards = new HashMap<>();
	
	//Affiche le scoreboard au joueur lors de la connexion
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		PlayerInfo playerInfo = new PlayerInfo(p);
		String prestigeSoupString = PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname();
		String rankSoupString = RankSoup.powerToRank(playerInfo.getRank()).getDisplayname();
		Main.infos.put(p, Main.database.getInfos(p));
		Main.killstreaks.put(p, 0);
		ScoreboardSign scoreboard = new ScoreboardSign(p, "§4§lLife§f§lCraft");
		scoreboard.create();
		scoreboard.setLine(0, "§1 ");
		scoreboard.setLine(1, "§1 ");
		scoreboard.setLine(2, "§7Rang: §9 " +  rankSoupString);
		scoreboard.setLine(3, "§7Prestige: §9 " + PlaceholderAPI.setPlaceholders(p, "%LCSoup_rank_color%") + prestigeSoupString);
		scoreboard.setLine(4, "§4 ");
		scoreboard.setLine(5, "§7Tués: §e " + Main.infos.get(p).get(3));
		scoreboard.setLine(6, "§7Morts: §b " + Main.infos.get(p).get(4));
		scoreboard.setLine(7, "§2 ");
		scoreboard.setLine(8, "§7Série: §a " + Main.killstreaks.get(p) + " §7kills");
		scoreboard.setLine(9, "§7Série max: §a " + Main.infos.get(p).get(5) + " §7kills");
		scoreboard.setLine(10, "§5 ");
		scoreboard.setLine(11, "§7Crédits: §e " + Main.infos.get(p).get(0));
		scoreboard.setLine(12, "§c ");
		scoreboard.setLine(13, "§a    §6play.lifecraft-mc.fr  §1 ");
		boards.put(p,scoreboard);
		event.setJoinMessage(null);;
        for (final PotionEffect pe : p.getActivePotionEffects()) {
            p.removePotionEffect(pe.getType());
        }
		
	}
	
	
	//Stop le scoreboard à la deconnexion du joueur
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(null);
		if(boards.containsKey(p)) {
			boards.get(p).destroy();
		}
		Main.database.setInfos(e.getPlayer().getUniqueId(), Main.infos.get(e.getPlayer()));
        if (Main.infos.containsKey(e.getPlayer())) {
            Main.infos.remove(e.getPlayer());
        }
        if (Main.killstreaks.containsKey(e.getPlayer())) {
            Main.killstreaks.remove(e.getPlayer());
        }
        p.kickPlayer("");
	}
	
	
	//Actualisation du scoreboard à la mort d'un joueur
	@EventHandler
	public void onDeath(PlayerRespawnEvent e) {
		Player victim = e.getPlayer();
		Player attacker = e.getPlayer().getKiller();
		PlayerInfo victimInfo = new PlayerInfo(victim);
		String RankSoupVictim = RankSoup.powerToRank(victimInfo.getRank()).getDisplayname();
		String PrestigeSoupVictim = PrestigeSoup.powerToRank(victimInfo.getPrestige()).getDisplayname();
		if(attacker instanceof Player) {
			PlayerInfo attackerInfo = new PlayerInfo(attacker);
			String RankSoupAttacker = RankSoup.powerToRank(attackerInfo.getRank()).getDisplayname();
			String PrestigeSoupAttacker = PrestigeSoup.powerToRank(attackerInfo.getPrestige()).getDisplayname();
			if(this.boards.containsKey(victim)) {
				Main.infos.put(victim, Main.database.getInfos(victim));
			    boards.get(victim).setLine(2, "§7Rang: §9 " + RankSoupVictim);
			    boards.get(victim).setLine(3, "§7Prestige: §9 " + PlaceholderAPI.setPlaceholders(victim, "%LCSoup_rank_color%") + PrestigeSoupVictim);
				boards.get(victim).setLine(5, "§7Tués: §e " + Main.infos.get(victim).get(3));
				boards.get(victim).setLine(6, "§7Morts: §b " + Main.infos.get(victim).get(4));
				boards.get(victim).setLine(8, "§7Série: §a " + Main.killstreaks.get(victim) + " §7kills");
				boards.get(victim).setLine(9, "§7Série max: §a " + Main.infos.get(victim).get(5) + " §7kills");
				boards.get(victim).setLine(11, "§7Crédits: §e " + Main.infos.get(victim).get(0));
			}
			if(this.boards.containsKey(attacker)) {
				Main.infos.put(attacker, Main.database.getInfos(attacker));
			    boards.get(attacker).setLine(2, "§7Rang: §9 " + RankSoupAttacker);
			    boards.get(attacker).setLine(3, "§7Prestige: §9 " + PlaceholderAPI.setPlaceholders(attacker, "%LCSoup_rank_color%") + PrestigeSoupAttacker);
				boards.get(attacker).setLine(5, "§7Tués: §e " + Main.infos.get(attacker).get(3));
				boards.get(attacker).setLine(6, "§7Morts: §b " + Main.infos.get(attacker).get(4));
				boards.get(attacker).setLine(8, "§7Série: §a " + Main.killstreaks.get(attacker) + " §7kills");
				boards.get(attacker).setLine(9, "§7Série max: §a " + Main.infos.get(attacker).get(5) + " §7kills");
				boards.get(attacker).setLine(11, "§7Crédits: §e " + Main.infos.get(attacker).get(0));
			}
		}
	}
	
}
