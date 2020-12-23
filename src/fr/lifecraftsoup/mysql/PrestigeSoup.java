package fr.lifecraftsoup.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

public enum PrestigeSoup {

	PRESTIGE0(0, "§a§cAdmin", "", "§7"),
	PRESTIGE1(1, "§b§cAdmin", "§4§lP1 ", "§lP1 "),
	PRESTIGE2(2, "§9§cAdmin", "§4§lP2 ", "§lP2 "),
	PRESTIGE3(3, "§8§cAdmin", "§4§lP3 ", "§lP3 "),
	PRESTIGE4(4, "§7§cAdmin", "§4§lP4 ", "§lP4 "),
	PRESTIGE5(5, "§6§cAdmin", "§4§lP5 ", "§lP5 "),
	PRESTIGE6(6, "§5§cAdmin", "§4§lP6 ", "§lP6 "),
	PRESTIGE7(7, "§4§cAdmin", "§4§lP7 ", "§lP7 "),
	PRESTIGE8(8, "§3§cAdmin", "§4§lP8 ", "§lP8 "),
	PRESTIGE9(9, "§2§cAdmin", "§4§lP9 ", "§lP9 "),
	PRESTIGE10(10, "§1§cAdmin", "§4§lP10 ", "§lP10 "),
	PRESTIGE11(11, "§1§cAdmin", "§4§lP11 ", "§lP11 "),
	PRESTIGE12(12, "§1§cAdmin", "§4§lP12 ", "§lP12 "),
	PRESTIGE13(13, "§1§cAdmin", "§4§lP13 ", "§lP13 "),
	PRESTIGE14(14, "§1§cAdmin", "§4§lP14 ", "§lP14 "),
	PRESTIGE15(15, "§1§cAdmin", "§4§lP15 ", "§lP15 "),
	PRESTIGE16(16, "§1§cAdmin", "§4§lP16 ", "§lP16 "),
	PRESTIGE17(17, "§1§cAdmin", "§4§lP17 ", "§lP17 "),
	PRESTIGE18(18, "§1§cAdmin", "§4§lP18 ", "§lP18 "),
	PRESTIGE19(19, "§1§cAdmin", "§4§lP19 ", "§lP19 "),
	PRESTIGE20(20, "§1§cAdmin", "§4§lP20 ", "§lP20 ");
	
	private String name, orderRank, displayname;	
	private int power;
	public static Map<Integer, PrestigeSoup> ranks = new HashMap<>();
	
	private PrestigeSoup(int power, String orderRank,  String name, String displayname) {
		this.name = name;
		this.power = power;
		this.orderRank = orderRank;
		this.displayname = displayname;
	}
	
	static {
		for (PrestigeSoup rank: PrestigeSoup.values()) {
			ranks.put(rank.getPower(), rank);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public static PrestigeSoup powerToRank(int power) {
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
	
	
	public static int getPlayerRank(UUID uuid) {
		try {
			
			PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT `prestige` FROM `soup` WHERE `uuid_player` = ?	");
			preparedStatement.setString(1, uuid.toString());
			int power = 0;
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				power = rs.getInt("prestige");
			}
			preparedStatement.close();
			
			return power;
			
		} catch(SQLException e) {
			System.out.println("Prestige : Impossible de récupérer le prestige du joueur" + Bukkit.getPlayer(uuid).getName());
		}
		return 0;
	}
}
