package fr.lifecraftsoup.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.lifecraftsoup.main.Main;

public class DatabaseManager {
	private String urlBase;
	private String host;
	private String database;
	private String username;
	private String password;
	private static Connection connection;
	
	public DatabaseManager(String urlBase, String host, String database, String username, String password) {
		this.urlBase = urlBase;
		this.host = host;
		this.database = database;
		this.username = username;
		this.password = password;
	
	}
	/**
	 * Méthode qui récupère la connexion
	 */
	
	public static Connection getConnexion() {
		return connection;
	}
	
	
	/**
	 * Méthode qui créer le compte d'un utilisateur dans la BDD
	 */
	public void createAccount(UUID uuid) {
		if(!hasAccount(uuid)) {	
			try {
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `soup` (`uuid_player`, `pseudo_player`, `coins`, `prestige`, `rank`, `kills`, `deaths`, `killstreak`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setString(2, Bukkit.getPlayer(uuid).getName());
				preparedStatement.setFloat(3, 0.0F);
				preparedStatement.setInt(4, 0);
				preparedStatement.setInt(5, 0);
				preparedStatement.setInt(6, 0);
				preparedStatement.setInt(7, 0);
				preparedStatement.setInt(8, 0);
				preparedStatement.execute();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Vérifie si le joueur est déjà enregistré sur la BDD
	 */
	
	public boolean hasAccount(UUID uuid) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT `coins` FROM `soup` WHERE `uuid_player` = ?");
			preparedStatement.setString(1, uuid.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Méthode qui permet d'effectuer la connexion à la BDD qd le plugin s'allume
	 */
	
	
	public void connect() {
		if (!isOnline()) {
			try {
				connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database + "?autoReconnect=true&characterEncoding=latin1", this.username, this.password);
				System.out.println("§e[LifecraftSoupBDD] §aConnexion réussi");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Méthode qui permet d'effectuer la déconnexion à la BDD qd le plugin s'allume
	 */
	
	public void disconnect() {
		if (isOnline()) {
			try {
				connection.close();
				System.out.println("§e[LifecraftSoupBDD] §cDéconnexion");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean isOnline() {
		try {
			if((connection == null) || (connection.isClosed())) {
				return false;
			}
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
    public boolean isTable(String table) {
        Statement statement;
        try {
            statement = DatabaseManager.getConnexion().createStatement();
        }
        catch (Exception e) {
            return false;
        }
        try {
            statement.executeQuery("SELECT 1 FROM " + table);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public void set(String syntax) {
        try {
            DatabaseManager.getConnexion().createStatement().executeUpdate(syntax);
        }
        catch (SQLException e) {
            Main.getInstance().getLogger().severe("Error with a SQL syntax : " + syntax);
            e.printStackTrace();
        }
    }
    
    public ResultSet get(String syntax) {
        ResultSet res = null;
        try {
            res = this.getConnexion().createStatement(1003, 1007).executeQuery(syntax);
        }
        catch (SQLException e) {
            Main.getInstance().getLogger().severe("Error with a SQL syntax : " + syntax);
            e.printStackTrace();
        }
        return res;
    }
    
    public ArrayList<Integer> getInfos(Player player) {
        ResultSet res = this.get("SELECT * FROM `soup` WHERE `pseudo_player`=('" + player.getName() + "');");
        ArrayList<Integer> infos = new ArrayList<Integer>();
        try {
            if (res.first()) {
                infos.add(res.getInt("coins"));
                infos.add(res.getInt("prestige"));
                infos.add(res.getInt("rank"));
                infos.add(res.getInt("kills"));
                infos.add(res.getInt("deaths"));
                infos.add(res.getInt("killstreak"));
                return infos;
            }
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            return infos;
        }
        catch (SQLException e) {
            e.printStackTrace();
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            infos.add(0);
            return infos;
        }
    }

    
    public Object getTop(String column, int amount) {
    	Object array = new Object();
    	ResultSet r = this.get("SELECT * FROM `soup` ORDER BY " + column + " DESC LIMIT " + amount + ";");
		array = r;
		return array;
    }
    
    public void setInfos(UUID uuid, ArrayList<Integer> infos) {
        if (this.hasAccount(uuid)) {
            this.set("UPDATE `soup` SET `kills`='" + infos.get(3) + "', `deaths`='" + infos.get(4) + "', `killstreak`='" + infos.get(5) + "', `coins`='"+ infos.get(0)+"' WHERE pseudo_player='" + Bukkit.getPlayer(uuid).getName() + "';");
        }
        else {
            this.createAccount(uuid);
            this.set("UPDATE `soup` SET `kills`='" + infos.get(3) + "', `deaths`='" + infos.get(4) + "', `killstreak`='" + infos.get(5) + "', `coins`='"+ infos.get(0)+ "' WHERE pseudo_player='" + Bukkit.getPlayer(uuid).getName() + "';");
        }
    }
}
