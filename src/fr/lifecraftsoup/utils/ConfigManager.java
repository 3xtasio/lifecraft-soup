package fr.lifecraftsoup.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.lifecraftsoup.main.Main;




public class ConfigManager
{
  public static HashMap<String, FileConfiguration> configs = new HashMap();
  
  public static void loadConfigs() {
    Main main = Main.getInstance();
    load(main.getDataFolder(), "config.yml");
  }
  
  public static void load(File parent, String fileName) {
    Main main = Main.getInstance();
    try {
      if (!main.getDataFolder().exists()) {
        main.getDataFolder().mkdir();
      }
      File file = new File(parent, fileName);
      if (!file.exists()) {
        InputStream in = main.getResource(fileName);
        
        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
          out.write(buffer, 0, len);
        }
        in.close();
        out.close();
      } 
      
      YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
      configs.put(fileName, yamlConfiguration);
    } catch (Exception ex) {
      ex.printStackTrace();
      Main.getInstance().getLogger().severe("Disable with error");
      main.getPluginLoader().disablePlugin(main);
    } 
  }

  
  public static void loadSilent(File parent, String fileName) {
    Main main = Main.getInstance();
    try {
      if (!main.getDataFolder().exists()) {
        main.getDataFolder().mkdir();
      }
      File file = new File(parent, fileName);
      if (!file.exists()) {
        InputStream in = main.getResource(fileName);
        
        OutputStream out = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) != -1) {
          out.write(buffer, 0, len);
        }
        in.close();
        out.close();
      } 
      
      YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
      configs.put(fileName, yamlConfiguration);
    } catch (Exception ex) {
      ex.printStackTrace();
      Main.getInstance().getLogger().severe("Disable with error");
      main.getPluginLoader().disablePlugin(main);
    } 
  }

  
  public static FileConfiguration getConfig(String conf) {
    if (configs.containsKey(conf)) {
      return (FileConfiguration)configs.get(conf);
    }
    loadSilent(Main.getInstance().getDataFolder(), conf);
    return (FileConfiguration)configs.get(conf);
  }

  
  public static void saveConfig(String config, File parent) {
    File file = new File(parent, config);
    try {
      getConfig(config).save(file);
    } catch (Exception e) {
      Main.getInstance().getLogger().severe("Error saving file");
      e.printStackTrace();
    } 
  }
  
  public static void createConfig(String config, File parent) {
    File file = new File(parent, config);
    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    configs.put(config, yamlConfiguration);
  }
  
  public static boolean exist(String config) {
    if (configs.containsKey(config)) {
      return true;
    }
    File file = new File(Main.getInstance().getDataFolder(), config);
    if (file.exists()) {
      loadSilent(Main.getInstance().getDataFolder(), config);
      return true;
    } 
    return false;
  }
}
