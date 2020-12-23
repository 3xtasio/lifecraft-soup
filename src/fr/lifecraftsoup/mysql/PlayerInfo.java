package fr.lifecraftsoup.mysql;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;


public class PlayerInfo {

	private static Map<Player, PlayerInfo> playerInfo = new HashMap<Player, PlayerInfo>();
	private Player player;
	private PlayerData playerData;
	
	public PlayerInfo(Player player) {
		this.player = player;
		this.playerData = new PlayerData(player.getUniqueId());
		playerInfo.put(player, this);
	}
	
	public static PlayerInfo getInfoPlayer(Player player) {
		return playerInfo.get(player);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public PlayerData getPlayerData() {
		return playerData;
	}
	
	public float getCoinsNumber() {
		return playerData.getCoins();
	}
	
	public void addCoins(float amount) {
		playerData.addCoins(amount);
	}
	
	public void removeCoins(float amount) {
		playerData.removeCoins(amount);
	}
	
	public int getRank() {
		return RankSoup.getPlayerRank(player.getUniqueId());
	}
	
	public int getPrestige() {
		return PrestigeSoup.getPlayerRank(player.getUniqueId());
	}
	
	public void setRank(int power) {
		playerData.setRank(power);
	}
	
	public void setPrestige(int power) {
		playerData.setPrestige(power);
	}
	
}