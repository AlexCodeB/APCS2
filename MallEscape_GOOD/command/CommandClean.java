package command;

import items.Food;
import textadventure.World;

public class CommandClean extends Command{

	@Override
	public String[] getCommandWords() {
		return new String[] {"clean"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if(params.length != 1) {
			World.print("I don't understand!\n\n");
		}
		if(params[0] != Food.isDirty()) {
			World.print("There is nothing to clean!\n\n");
		}else if(params[0] == dirty) {
			World.print("The food is safe for consumption.\n\n");
			params[0] == clean;
		}else {
			World.print("I don't understand.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item]";
	}

}
