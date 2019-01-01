package command;

import interfaces.Container;
import items.Item;
import textadventure.World;

public class CommandPut extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"put", "place"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		if(!(params.length == 3 && params[1].equals("in"))) {
			World.print("I don't understand.\n\n");
			return;
		}
		String itemName = params[0];
		String containerName = params[2];
		Item item = world.getPlayer().hasItem(itemName) ? world.getPlayer().getItem(itemName) : world.getPlayer().getCurrentRoom().getItem(itemName);
		Item container = world.getPlayer().hasItem(containerName) ? world.getPlayer().getItem(containerName) : world.getPlayer().getCurrentRoom().getItem(containerName);

		boolean playerHasItem = world.getPlayer().hasItem(itemName);
		boolean roomHasItem = world.getPlayer().getCurrentRoom().hasItem(itemName);
		boolean playerHasContainer = world.getPlayer().hasItem(containerName);
		boolean roomHasContainer = world.getPlayer().getCurrentRoom().hasItem(containerName);
		
		if(!(playerHasItem && roomHasItem)) {
			World.print("You can't see " + itemName + " here!\n\n");
			return;
		}
		if(!(playerHasContainer && roomHasContainer)) {
			World.print("You can't see " + container + " here!\n\n");
			return;
		}
		if(!(container instanceof Container)) {
			World.print("The " + containerName + " can't hold things.\n\n");
			return;
		}
		
		if(playerHasItem) {
			((Container)container).doPut(item, world.getPlayer());
		}else {
			((Container)container).doPut(item, world.getPlayer().getCurrentRoom());
		}
		
	}

	@Override
	public String getHelpDescription() {
		return "[item] in [container]";
	}

}
