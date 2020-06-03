package fr.jingie.fightgame.commands.subcommands;

import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.commands.CommandManager;
import fr.jingie.fightgame.commands.SubCommand;

public class HelpCommand extends SubCommand {

	private FightGame main;
	private CommandManager cmdmanager;
	public HelpCommand(FightGame main, CommandManager cmdmanager) {
		this.main = main;
		this.cmdmanager = cmdmanager;
	}
	

	@Override
	public String getName() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "Affiche les différentes commandes du plugins";
	}

	@Override
	public String getSyntax() {
		return "/fightgame help";
	}

	@Override
	public void perform(Player player, String[] args) {
		System.out.println("help");
		
		String cmdList = "";
		
		for (int i = 0; i < cmdmanager.getSubCommands().size(); i++) {
			cmdList += (cmdmanager.getSubCommands().get(i).getSyntax() + " -> " + cmdmanager.getSubCommands().get(i).getDescription() + "\n");
		}
		
		player.sendMessage("§7Aide du Plugin [FightGame] :\n"
				+ "Pour lancer un duel, il faut que 2 spawns et l'emplacement du panneau <join> soient indiqués par les commandes correspondantes.\n"
				+ "Il ne peut y avoir que deux spawns maximums.\n"
				+ "Le panneau <join> doit contenir et uniquement contenir <join> sur la première ligne (les majuscules ne sont pas prises en compte.\n"
				+ "---------------\n"
				+ "Liste des commandes :\n"
				+ cmdList);
	}

}
