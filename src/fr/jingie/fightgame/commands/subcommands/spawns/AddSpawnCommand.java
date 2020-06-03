package fr.jingie.fightgame.commands.subcommands.spawns;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.GameState;
import fr.jingie.fightgame.commands.SubCommand;

public class AddSpawnCommand extends SubCommand {

	private FightGame main;
	public AddSpawnCommand(FightGame main) {
		this.main = main;
	}
	
	
	@Override
	public String getName() {
		return "addspawn";
	}
	
	@Override
	public String getDescription() {
		return "Ajoute un spawn pour le duel";
	}
	
	@Override
	public String getSyntax() {
		return "/fightgame addspawn";
	}
	
	@Override
	public void perform(Player player, String[] args) {
		
		System.out.println("Addspawn");
		
		Location pLoc = player.getLocation();
		
		if (main.isState(GameState.NOTHING)) {
			if (main.getSpawns().size() < 2) {
				main.getSpawns().add(pLoc);
				player.sendMessage(main.prefix + "§aLe spawn a bien été ajouté");
			}
			else {
				player.sendMessage(main.prefix + "§cIl y a déjà 2 spawns existants");
			}
		}
		else {
			player.sendMessage(main.prefix + "§cUne partie est en cours, réessayez plus tard");
		}
		
	}

	
}
