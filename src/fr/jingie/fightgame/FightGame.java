package fr.jingie.fightgame;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.jingie.fightgame.commands.CommandManager;
import fr.jingie.fightgame.listeners.BlockListeners;
import fr.jingie.fightgame.listeners.EntityListeners;

public class FightGame extends JavaPlugin {
	
	/*
	 * --------------------------
	 * MAIN CLASS OF THE PLUGIN |
	 * --------------------------
	 */
	
	private GameState state;
	private List<Player> playersInGame = new ArrayList<>();
	private List<Location> spawns = new ArrayList<>();
	private Location signLoc;
	
	public String prefix = "§f[FightGame] ";
	
	@Override
	public void onEnable() {
		this.setState(GameState.NOTHING);
		
		getServer().getPluginManager().registerEvents(new EntityListeners(this), this);
		getServer().getPluginManager().registerEvents(new BlockListeners(this), this);
		
		// Before the game
		this.getCommand("fightgame").setExecutor(new CommandManager(this));
		
		
	}
	
	/*
	 * ---------
	 * METHODS |
	 * ---------
	 */
	
	public void setState(GameState state) {
		this.state = state;
	}
	
	public boolean isState(GameState state) {
		return this.state == state;
	}
	
	public List<Player> getPlayers() {
		return this.playersInGame;
	}
	
	public List<Location> getSpawns() {
		return this.spawns;
	}
	
	public void setSignLocation(Location location) {
		this.signLoc = location;
	}
	
	public Location getSignLocation() {
		return signLoc;
	}

	public void eleminate(Player player) {
		this.getPlayers().remove(player);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setHealth(20.0);
		player.setSaturation(20);
		player.teleport(this.signLoc);
		
	}

	public void checkWin() {
		if (this.getPlayers().size() == 1) {
			Bukkit.broadcastMessage(this.prefix + "§6" + this.getPlayers().get(0).getName() + " a gagné le duel !");
			this.getPlayers().get(0).teleport(this.signLoc);
			this.setState(GameState.NOTHING);
			this.eleminate(this.getPlayers().get(0));
		}
		
		else if (this.getPlayers().size() == 0) {
			Bukkit.broadcastMessage(this.prefix + "§6Il n'y a pas de gagnant...");
			this.setState(GameState.NOTHING);
		}
		
		else {
			// No win
			System.out.println("No winner");
		}
	}
	
}
