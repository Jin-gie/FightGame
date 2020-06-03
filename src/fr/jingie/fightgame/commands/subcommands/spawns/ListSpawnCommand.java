package fr.jingie.fightgame.commands.subcommands.spawns;

import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.commands.SubCommand;

public class ListSpawnCommand extends SubCommand {

	private FightGame main;
	public ListSpawnCommand(FightGame main) {
		this.main = main;
	}
	
	@Override
	public String getName() {
		return "listspawn";
	}
	@Override
	public String getDescription() {
		return "Liste les spawn déjà ajoutés";
	}
	@Override
	public String getSyntax() {
		return "/fightgame listspawn";
	}
	@Override
	public void perform(Player player, String[] args) {
		
		String spawnList = "";
		
		for (int i = 0; i < main.getSpawns().size(); i++) {
			spawnList += (i+1 + " : x=" + (int)main.getSpawns().get(i).getX() + ", z=" + (int)main.getSpawns().get(i).getZ() + "\n");
			player.sendMessage(main.prefix + "§fSpawns disponibles :\n" + spawnList + "Pour retirer un spawn: /fightgame removespawn <numéro>");
		}
		
	}



}
