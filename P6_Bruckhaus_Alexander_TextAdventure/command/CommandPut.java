package command;

import items.Container;
import items.Item;
import textadventure.World;

public class CommandPut extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"put", "place", "insert"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {

		// Handle errors
		if (!(params.length == 3 && params[1].equals("in"))) {
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
		
		if (!playerHasItem && !roomHasItem) {
			World.print("You can't see any " + itemName + " here!\n\n");
			return;
		}
		if (!playerHasContainer && !roomHasContainer)  {
			World.print("You can't see any " + containerName + " here!\n\n");
			return;
		}
		if (! (container instanceof Container)) {
			World.print("The " + containerName + " can't hold things.\n\n");
			return;
		}
		if (itemName.equals(containerName)) {
			World.print("You can't put the " + containerName + " into itself!\n\n");
			return;
		}
		
		// If we made it this far, then it's safe to put [item] into [container]

		if (playerHasItem) {
			((Container)container).doPut(item, world.getPlayer());
		}
		else { // current room has the item
			((Container)container).doPut(item, world.getPlayer().getCurrentRoom());
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] in [container]";
	}
}