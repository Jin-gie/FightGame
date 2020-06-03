package fr.jingie.fightgame.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.GameState;
import fr.jingie.fightgame.commands.running.StartGame;

public class BlockListeners implements Listener {

	private FightGame main;
	
	public BlockListeners(FightGame main) {
		this.main = main;
	}
	
	@EventHandler
	public void onClickSign(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		
		if (event.getClickedBlock() != null) {
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) 
			{				
				if (event.getClickedBlock().getType() == Material.WALL_SIGN) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					if (sign.getLine(0).equalsIgnoreCase("<Join>")) {
						if (main.getSpawns().size() < 2 || main.getSignLocation() == null) {
							player.sendMessage(main.prefix + "§cLes spawns et/ou panneaux ne sont pas encore assignés");
							return;
						}
						
						else if (main.isState(GameState.NOTHING)) {
							if (!main.getPlayers().contains(player)) {
								main.getPlayers().add(player);
								Bukkit.broadcastMessage(main.prefix + player.getName() + "§6 a rejoint le du-du-du-duel !");
							}
							
							else {
								player.sendMessage(main.prefix + "§cVous avez déjà rejoint la partie !");
								return;
							}
						}
						
						else {
							player.sendMessage(main.prefix + "§cUne partie est déjà en cours, réessayez plus tard !");
							return;
						}
						
					}
				}
			}
		}
		
		
		/*
		 * -----------------------
		 * BEGINNING OF THE GAME |
		 * -----------------------
		 */
		
		if (main.isState(GameState.NOTHING) && main.getPlayers().size() == 2) {
			// Regarder s'il y a 2 spawns
			if (main.getSpawns().size() == 2 && main.getSignLocation() != null) {
				// Débuter la partie
				main.setState(GameState.STARTING);
				StartGame newGame = new StartGame(main);
				newGame.runTaskTimer(main, 0, 20);
			}
			
			else if (main.getSpawns().size() < 2) {
				// Il manque un spawn
				Bukkit.broadcastMessage(main.prefix + "§cIl manque au moins 1 spawn sur 2 nécessaires");
			}
			
			else if (main.getSignLocation() == null) {
				// Il n'y a pas de spawn de panneau
				Bukkit.broadcastMessage(main.prefix + "§cL'emplacement du panneau n'a pas été indiquée");
			}

		}
		
	}
}
