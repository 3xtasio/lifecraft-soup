package fr.lifecraftsoup.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RandomTeleport implements Listener{
	  private static List<Location> spawndelfino = new ArrayList<>();
	  
	  private static World delfino = Bukkit.getWorld("delfino");
	  
	  public static void locdelfino() {
	    spawndelfino.add(new Location(delfino, 746.0D, 189.0D, 803.0D, -91.4F, 0F));
	    spawndelfino.add(new Location(delfino, 716.0D, 166.0D, 806.0D, -89.6F, 0F));
	    spawndelfino.add(new Location(delfino, 745.0D, 142.0D, 845.0D, -91.5F, 0F));
	    spawndelfino.add(new Location(delfino, 836.51D, 141.0D, 854.42D, -180.2F, 0F));
	    spawndelfino.add(new Location(delfino, 855.60D, 141.0D, 798.6D, -269.34F, 0F));
	    spawndelfino.add(new Location(delfino, 850.28D, 141.0D, 756.64D, 0.9F, 0F));
	    spawndelfino.add(new Location(delfino, 795.60D, 144.0D, 768.60D, 0.67F, 0F));
	    spawndelfino.add(new Location(delfino, 778.50D, 140.0D, 720.5D, -90.2F, 0F));
	    spawndelfino.add(new Location(delfino, 754.5D, 142.0D, 733.2D, -181.2F, 0F));
	    spawndelfino.add(new Location(delfino, 764.5D, 140.0D, 806.41D, 90.4F, 0F));
	    spawndelfino.add(new Location(delfino, 717.5D, 166.0D, 807.41D, 270.6F, 0F));
	    spawndelfino.add(new Location(delfino, 764.5D, 140.0D, 806.41D, 90.4F, 0F));
	    spawndelfino.add(new Location(delfino, 744.5D, 144.0D, 774.37D, 270.4F, 0F));
	    spawndelfino.add(new Location(delfino, 762.50D, 149.0D, 763.5D, 179.9F, 0F));
	    spawndelfino.add(new Location(delfino, 811.50D, 165.0D, 757.5D, 0.6F, 0F));
	    spawndelfino.add(new Location(delfino, 795.50D, 141.0D, 738.0D, 0.8F, 0F));
	    spawndelfino.add(new Location(delfino, 753.50D, 144.0D, 859.5D, 269.9F, 0F));
	    spawndelfino.add(new Location(delfino, 774.50D, 142.0D, 849.5D, 179.7F, 0F));
	    spawndelfino.add(new Location(delfino, 788.50D, 166.0D, 856.5D, 269.6F, 0F));
	    spawndelfino.add(new Location(delfino, 761.4D, 154.5D, 834.5D, 180.1F, 0F));
	    spawndelfino.add(new Location(delfino, 802.50D, 141.0D, 843.5D, 0.41F, 0F));
	    spawndelfino.add(new Location(delfino, 818.50D, 141.0D, 811.3D, -90.5F, 0F));
	  }
	  
	  public static void teleportdelfino(Player player) {
		locdelfino();
	    int spawn = new Random().nextInt(spawndelfino.size());
	    player.teleport(spawndelfino.get(spawn));
	  }
}
