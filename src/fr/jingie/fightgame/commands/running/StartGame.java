package fr.jingie.fightgame.commands.running;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jingie.fightgame.FightGame;

public class StartGame extends BukkitRunnable {

	private FightGame main;
	public StartGame(FightGame main) {
		this.main = main;
	}
	
	private int timer = 5;
	
	@Override
	public void run() {
		
		/*
		 * GAMESTATE == WAITING
		 */
		
		if (timer == 5) {
			Bukkit.broadcastMessage(main.prefix + "Les joueurs vont êtres TP dans " + timer + "s");
		}
		
	
		for (Player p : main.getPlayers()) {
			p.setLevel(timer);
		}
		
		if (timer == 0) {
			for (int i = 0; i < main.getPlayers().size(); i++) { // this design of for loop to iterate through the spawns at the same time
				Player p = main.getPlayers().get(i);
				Location loc = main.getSpawns().get(i);
				
				p.getInventory().clear();
				p.setGameMode(GameMode.SURVIVAL);
				
				// Creation stuff
				p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
				p.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
				p.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
				p.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
				p.getInventory().setItem(0, new ItemStack(Material.IRON_SWORD));
				
				// Heal
				p.setHealth(20.0);
				p.setSaturation(20);
				
				// TP le joueur (1er joueur sur 1er spawn, etc.)
				p.teleport(loc);
			}
			
			RunningGame runGame = new RunningGame(main);
			runGame.runTaskTimer(main, 0, 20);
			
			cancel();
			
		}
		
		timer --;		
	}

}
