package fr.jingie.fightgame.commands.running;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.GameState;

public class RunningGame extends BukkitRunnable {

	private FightGame main;
	public RunningGame(FightGame main) {
		this.main = main;
	}

	public int timerPVP = 10;
	
	@Override
	public void run() {
		
		if (timerPVP == 10) {
			Bukkit.broadcastMessage(main.prefix + "Le PVP va �tre activ� dans " + timerPVP + "s");
		}
		
		for (Player p : main.getPlayers()) {
			p.setLevel(timerPVP);
		}
		
		Bukkit.broadcastMessage(main.prefix + "Il reste " + timerPVP + "s");
		
		if (timerPVP == 0) {
			Bukkit.broadcastMessage(main.prefix + "Le PVP est activ� !");
			main.setState(GameState.PVP);
			cancel();
		}
		
		timerPVP--;
	}

}
