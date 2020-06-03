package fr.jingie.fightgame.commands.subcommands.spawns;


import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.GameState;
import fr.jingie.fightgame.commands.SubCommand;

public class RemoveSpawnCommand extends SubCommand {

	private FightGame main;
	public RemoveSpawnCommand(FightGame main) {
		this.main = main;
	}
	
	@Override
	public String getName() {
		return "removespawn";
	}
	@Override
	public String getDescription() {
		return "Permet de retirer un spawn de duel";
	}
	@Override
	public String getSyntax() {
		return "/fightgame removespawn <numéro>";
	}
	@Override
	public void perform(Player player, String[] args) {
		
		if (main.isState(GameState.NOTHING)) 
		{
			
			if (args.length == 1) 
			{ 
				// /fightgame removespawn -> afficher les spawns
				if (main.getSpawns().isEmpty()) {
					player.sendMessage(main.prefix + "§cAucun spawn à retirer");
				}
				else {
					String spawnList = "";
					for (int i = 0; i < main.getSpawns().size(); i++) {
						spawnList += (i+1 + " : x=" + (int)main.getSpawns().get(i).getX() + ", z=" + (int)main.getSpawns().get(i).getZ() + "\n");
					}
					
					player.sendMessage(main.prefix + "§fSpawns disponibles :\n" + spawnList + "Pour retirer un spawn : /fightgame removespawn <numéro>");
				}
			}
			else if (args.length == 2) {
				// /fightgame removespawn <numéro>
				// Try if arg is an int and if it exists
				try {
					Integer spawnToDelete = Integer.parseInt(args[0]);
					main.getSpawns().remove(spawnToDelete-1);
					
				} catch (NumberFormatException e) {
					player.sendMessage(main.prefix + "§c[Erreur] - Entrer un nombre entier existant");
				} catch (IndexOutOfBoundsException e) {
					player.sendMessage(main.prefix + "§c[Erreur] - Ce spawn n'existe pas");
				}
			}
			
		}
		else {
			player.sendMessage(main.prefix + "§cUne partie est en cours, réessayez plus tard");
		}
		
	}

	

}
