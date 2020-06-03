package fr.jingie.fightgame.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.GameState;


public class EntityListeners implements Listener {
	
	private FightGame main;
	
	public EntityListeners(FightGame main) {
		this.main = main;
	}
	

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String joinMsg = "§f" + player.getName() + "§a a rejoint le serveur.";
		event.setJoinMessage(joinMsg);
		
		/*
		if (!main.isState(GameState.NOTHING)) {
			// Une partie est en cours
			player.setGameMode(GameMode.SPECTATOR);
		}
		*/
	}
	
	@EventHandler
	public void onDisconnect(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String quitMsg = "§f" + player.getName() + "§c a quitté le serveur.";
		event.setQuitMessage(quitMsg);
		
		if (main.getPlayers().contains(player)) {
			main.getPlayers().remove(player);
		}
	}
	
	/*
	@EventHandler
	public void saturation(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}
	*/
	
	@EventHandler
	public void onMovement(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		// Avoid movement of players in the game while starting
		if (main.isState(GameState.STARTING)) {
			if (main.getPlayers().contains(player)) {
				event.setTo(event.getFrom());
			}
		}
		
	}
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player victim = event.getEntity();
		
		if (event.getEntity().getKiller() instanceof Player) {
			Player killer = event.getEntity().getKiller();
			
			String deathMsg = "§f" + victim.getName() + " a été tué par " + killer.getName();
			event.setDeathMessage(deathMsg);
		}
		
		else {
			String deathMsg = "§f" + victim.getName() + " est mort.e dans d'atroces souffrances";
			event.setDeathMessage(deathMsg);
		}
		
		// victim.setGameMode(GameMode.SPECTATOR);
	}


	/*
	 * -----------------
	 * DAMAGES MANAGER |
	 * -----------------
	 */
	
	@EventHandler
	public void damageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			if (main.isState(GameState.STARTING) && main.getPlayers().contains(player)) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void damageByBlock(EntityDamageByBlockEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			if (main.isState(GameState.STARTING) && main.getPlayers().contains(player)) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void damage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			if (main.isState(GameState.STARTING) && main.getPlayers().contains(player)) {
				event.setCancelled(true);
			}
		}
		
		if (event.getEntity() instanceof Player) {
			Player p = (Player)event.getEntity();
			if ( (p.getHealth() - event.getFinalDamage() <= 0) && main.getPlayers().contains(p)) {
				event.setDamage(0);
				main.eleminate(p);
				main.checkWin();
			}
		}
		
	}
	
	
	
}
