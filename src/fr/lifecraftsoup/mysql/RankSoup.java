package fr.lifecraftsoup.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

public enum RankSoup {

	NOVICE(0, "§a§cAdmin", "§7Nov ", "§7Novice ", "§7"),
	SOLDAT(1, "§9§cAdmin", "§eSold ", "§eSoldat ", "§e"),
	FANTASSIN(2, "§8§cAdmin", "§6Fant ", "§6Fantassin ", "§6"),
	MERCENAIRE(3, "§7§cAdmin", "§cMerce ", "§cMercenaire ", "§c"),
	CHEVALIER(4, "§6§cAdmin", "§2Chev ", "§2Chevalier ","§2"),
	COMBATTANT(5, "§5§cAdmin", "§9Comb ", "§9Combattant ","§9"),
	GLADIATEUR(6, "§4§cAdmin", "§3Glad ", "§3Gladiateur ","§3"),
	SPARTIATE(7, "§3§cAdmin", "§aSpart ", "§aSpartiate ","§a"),
	SEIGNEUR(8, "§2§cAdmin","§bSeign ", "§bSeigneur ","§b"),
	POURFENDEUR(9, "§1§cAdmin", "§4§lPourf ", "§4§lPourfendeur ","§4");
	
	private String name, orderRank, displayname, color;	
	private int power;
	public static Map<Integer, RankSoup> ranks = new HashMap<>();
	
	private RankSoup(int power, String orderRank,  String name, String displayname, String color) {
		this.name = name;
		this.power = power;
		this.orderRank = orderRank;
		this.displayname = displayname;
		this.color = color;
	}
	
	static {
		for (RankSoup rank: RankSoup.values()) {
			ranks.put(rank.getPower(), rank);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public static RankSoup powerToRank(int power) {
		return ranks.get(power);
	}
	
	
	public Integer getPower() {
		return power;
	}
	
	public String getOrderRank() {
		return orderRank;
	}
	
	public String getDisplayname() {
		return displayname;
	}
	
	public String getColor() {
		return color;
	}
	
	public static int getPlayerRank(UUID uuid) {
		try {
			
			PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT `rank` FROM `soup` WHERE `uuid_player` = ?	");
			preparedStatement.setString(1, uuid.toString());
			int power = 0;
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				power = rs.getInt("rank");
			}
			preparedStatement.close();
			
			return power;
			
		} catch(SQLException e) {
			System.out.println("Rank : Impossible de récupérer le grade du joueur" + Bukkit.getPlayer(uuid).getName());
		}
		return 0;
	}
}
