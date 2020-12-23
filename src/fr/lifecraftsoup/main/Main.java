package fr.lifecraftsoup.main;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.lifecraftsoup.commands.AutoSoupCommand;
import fr.lifecraftsoup.commands.CreditCommand;
import fr.lifecraftsoup.commands.InfoCommand;
import fr.lifecraftsoup.commands.Leaderboard;
import fr.lifecraftsoup.commands.MenuCommand;
import fr.lifecraftsoup.commands.PrestigeCommand;
import fr.lifecraftsoup.commands.RankCommand;
import fr.lifecraftsoup.commands.RankupCommand;
import fr.lifecraftsoup.commands.SoupCommand;
import fr.lifecraftsoup.commands.StatsCommand;
import fr.lifecraftsoup.kitevent.Bumblebee;
import fr.lifecraftsoup.kitevent.Cheetah;
import fr.lifecraftsoup.kitevent.Dionysos;
import fr.lifecraftsoup.kitevent.DrugDealer;
import fr.lifecraftsoup.kitevent.Eagle;
import fr.lifecraftsoup.kitevent.Fisherman;
import fr.lifecraftsoup.kitevent.Frog;
import fr.lifecraftsoup.kitevent.Ghost;
import fr.lifecraftsoup.kitevent.Hermès;
import fr.lifecraftsoup.kitevent.IronMan;
import fr.lifecraftsoup.kitevent.Jedi;
import fr.lifecraftsoup.kitevent.Kangaroo;
import fr.lifecraftsoup.kitevent.LuckyHit;
import fr.lifecraftsoup.kitevent.Poseidon;
import fr.lifecraftsoup.kitevent.Prepgirl;
import fr.lifecraftsoup.kitevent.Shark;
import fr.lifecraftsoup.kitevent.Snail;
import fr.lifecraftsoup.kitevent.Snowman;
import fr.lifecraftsoup.kitevent.Soldat;
import fr.lifecraftsoup.kitevent.Stomper;
import fr.lifecraftsoup.kitevent.Switcher;
import fr.lifecraftsoup.kitevent.Tank;
import fr.lifecraftsoup.kitevent.Viper;
import fr.lifecraftsoup.kits.CommandGui;
import fr.lifecraftsoup.kits.Gui;
import fr.lifecraftsoup.listeners.AutoSoupListener;
import fr.lifecraftsoup.listeners.JoinListener;
import fr.lifecraftsoup.listeners.PlayerChatListener;
import fr.lifecraftsoup.listeners.PlayerListener;
import fr.lifecraftsoup.listeners.SoupListener;
import fr.lifecraftsoup.listeners.scoreboard.ScoreboardPlayer;
import fr.lifecraftsoup.mysql.DatabaseManager;
import fr.lifecraftsoup.utils.PlaceholderExpansion;
public class Main extends JavaPlugin {
	
	private static Main instance;
	public static DatabaseManager database;
	public Scoreboard sb;
    public static HashMap<Player, ArrayList<Integer>> infos;
    public static HashMap<Player,Integer> killstreaks;
    public static HashMap<Player,Boolean> autosoup;
    public static HashMap<Player,Boolean> vanishplus;
	static {
		Main.infos = new HashMap<Player, ArrayList<Integer>>();
		 Main.killstreaks = new HashMap<Player, Integer>();
		 Main.autosoup = new HashMap<Player, Boolean>();
		 Main.vanishplus = new HashMap<Player, Boolean>();
	}
	
	@Override
	public void onEnable() {
		Main.instance = this;
		PluginManager pm = Bukkit.getPluginManager();
		database = new DatabaseManager("jdbc:mysql://", Main.getInstance().getConfig().getString("Stockage.host"), Main.getInstance().getConfig().getString("Stockage.database"), 
				Main.getInstance().getConfig().getString("Stockage.username"), Main.getInstance().getConfig().getString("Stockage.password"));
		
		
		database.connect();
		saveDefaultConfig();
		boolean table = database.isTable("soup");
        if (!table) {
        	database.set("CREATE TABLE IF NOT EXISTS `soup` (`id` int(11) NOT NULL AUTO_INCREMENT, `uuid_player` varchar(255) NOT NULL, `pseudo_player` varchar(255) NOT NULL, `coins` float NOT NULL, `prestige` int(11) NOT NULL, `rank` int(11) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL, `killstreak` int(11) NOT NULL, PRIMARY KEY (`id`)) AUTO_INCREMENT=1 ;");
        }
        new PlaceholderExpansion().register();
		System.out.println("LifecraftSoup - V1 | Enabled");
		pm.registerEvents(new SoupListener(), this);
		pm.registerEvents(new ScoreboardPlayer(), this);
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new PlayerChatListener(), this);
		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new Gui(), this);
		pm.registerEvents(new CommandGui(), this);
		pm.registerEvents(new Jedi(), this);
		pm.registerEvents(new Fisherman(), this);
		pm.registerEvents(new Soldat(), this);
		pm.registerEvents(new Shark(), this);
		pm.registerEvents(new Poseidon(), this);
		pm.registerEvents(new Cheetah(), this);
		pm.registerEvents(new Eagle(), this);
		pm.registerEvents(new Tank(), this);
		pm.registerEvents(new Prepgirl(), this);
		pm.registerEvents(new Frog(), this);
		pm.registerEvents(new Snail(), this);
		pm.registerEvents(new Viper(), this);
		pm.registerEvents(new Kangaroo(), this);
		pm.registerEvents(new Hermès(), this);
		pm.registerEvents(new IronMan(), this);
		pm.registerEvents(new Stomper(), this);
		pm.registerEvents(new Switcher(), this);
		pm.registerEvents(new Bumblebee(), this);
		pm.registerEvents(new Dionysos(), this);
		pm.registerEvents(new DrugDealer(), this);
		pm.registerEvents(new AutoSoupListener(), this);
		pm.registerEvents(new Snowman(), this);
		pm.registerEvents(new LuckyHit(), this);
		pm.registerEvents(new Ghost(), this);
		pm.registerEvents(new SoupCommand(), this);
		getCommand("rankup").setExecutor(new RankupCommand());
		getCommand("leaderboard").setExecutor(new Leaderboard());
		getCommand("prestige").setExecutor(new PrestigeCommand());
		getCommand("soup").setExecutor(new SoupCommand());
		getCommand("crédit").setExecutor(new CreditCommand());
		getCommand("rank").setExecutor(new RankCommand());
		getCommand("stats").setExecutor(new StatsCommand());
		getCommand("menu").setExecutor(new MenuCommand());
		getCommand("autosoup").setExecutor(new AutoSoupCommand());
		getCommand("menucommand").setExecutor(new InfoCommand());
	}
	
	
	@Override
	public void onDisable() {
		database.disconnect();
		System.out.println("LifecraftSoup - V1 | Disabled");
	}
	
    public static Main getInstance() {
        return Main.instance;
    }
}
