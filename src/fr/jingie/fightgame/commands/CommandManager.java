package fr.jingie.fightgame.commands;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.commands.subcommands.HelpCommand;
import fr.jingie.fightgame.commands.subcommands.sign.SetSignCommand;
import fr.jingie.fightgame.commands.subcommands.spawns.AddSpawnCommand;
import fr.jingie.fightgame.commands.subcommands.spawns.ListSpawnCommand;
import fr.jingie.fightgame.commands.subcommands.spawns.RemoveSpawnCommand;

public class CommandManager implements CommandExecutor {

	private FightGame main;
	private ArrayList<SubCommand> subCommands = new ArrayList<>();
	
	public CommandManager(FightGame main) {
		this.main = main;
		
		subCommands.add(new HelpCommand(main, this));
		subCommands.add(new AddSpawnCommand(main));
		subCommands.add(new ListSpawnCommand(main));
		subCommands.add(new RemoveSpawnCommand(main));
		subCommands.add(new SetSignCommand(main));
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (args.length > 0) {
				for (int i = 0; i < getSubCommands().size(); i++) {
					if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
						getSubCommands().get(i).perform(player, args);
						return true;
					}
				}
				player.sendMessage(main.prefix + "§cCommande inconnue, tapez /fightgame help");
				return true;
				
			} else if (args.length == 0) {
				player.sendMessage(main.prefix + "§cCommande inconnue, tapez /fightgame help");
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<SubCommand> getSubCommands() {
		return this.subCommands;
	}

}
