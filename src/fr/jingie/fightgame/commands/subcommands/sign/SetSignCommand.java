package fr.jingie.fightgame.commands.subcommands.sign;

import org.bukkit.Location;

import org.bukkit.entity.Player;

import fr.jingie.fightgame.FightGame;
import fr.jingie.fightgame.commands.SubCommand;

public class SetSignCommand extends SubCommand {

	private FightGame main;
	
	public SetSignCommand(FightGame main) {
		this.main = main;
	}

	@Override
	public String getName() {
		return "setsign";
	}

	@Override
	public String getDescription() {
		return "Ajoute la location du panneau pour joindre le duel";
	}

	@Override
	public String getSyntax() {
		return "/fightgame setsign";
	}

	@Override
	public void perform(Player player, String[] args) {
		Location loc = player.getLocation();
		main.setSignLocation(loc);
		player.sendMessage(main.prefix + "§aLe panneau est maintenant situé !");
	}


}
