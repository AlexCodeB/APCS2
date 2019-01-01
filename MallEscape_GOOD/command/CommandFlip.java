package command;

import textadventure.Player;
import textadventure.World;

public class CommandFlip extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[] {"flip", "switch"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		Player player = world.getPlayer();
		if(params.length != 1) {
			World.print("I don't understand!\n\n");
		}else {
			player.setHasLights(true);
			World.print("The room is illuminated with a bright yellow light and you can see things you couldn't before!\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item]";
	}
	
}
