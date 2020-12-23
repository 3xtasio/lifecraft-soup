package fr.lifecraftsoup.utils;

import org.bukkit.entity.Player;

import fr.lifecraftsoup.mysql.PlayerInfo;
import fr.lifecraftsoup.mysql.PrestigeSoup;
import fr.lifecraftsoup.mysql.RankSoup;

public class PlaceholderExpansion extends me.clip.placeholderapi.expansion.PlaceholderExpansion {
    @Override
    public boolean canRegister() {
        return true;
    }
	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return "Extasio15";
	}

	@Override
	public String getIdentifier() {
		return "LCSoup";
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return "1.0";
	}

	@Override
	public String onPlaceholderRequest(Player p, String identifier) {

	        if (p == null) {
	            return "";
	        }
	        if (identifier.equals("prestige_DisplayName")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return PrestigeSoup.powerToRank(playerInfo.getPrestige()).getDisplayname();
	        }
	        if (identifier.equals("rank_DisplayName")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return RankSoup.powerToRank(playerInfo.getRank()).getDisplayname();
	        }
	        if (identifier.equals("rank_power")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return RankSoup.powerToRank(playerInfo.getRank()).getPower() + "";
	        }
	        if (identifier.equals("prestige_power")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return PrestigeSoup.powerToRank(playerInfo.getPrestige()).getPower() + "";
	        }
	        if (identifier.equals("rank_color")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return RankSoup.powerToRank(playerInfo.getRank()).getColor() + "";
	        }
	        if (identifier.equals("coins")) {
	        	PlayerInfo playerInfo = new PlayerInfo(p);
	            return playerInfo.getCoinsNumber() + "";
	        }
	        
	 
	        return null;
	}
}
