package command;

import items.Container;
import items.Item;
import textadventure.World;

public class CommandTake extends Command {

	@Override
	public String[] getCommandWords() {
		return new String[]{"take", "get", "grab", "hold"};
	}

	@Override
	public void doCommand(String cmd, String[] params, World world) {
		// Handle "take [Item]" command
		if (params.length == 1) {
			String itemName = params[0];
			if (world.getPlayer().getCurrentRoom().hasItem(itemName)) {
				Item item = world.getPlayer().getCurrentRoom().getItem(itemName);
				item.doTake(world.getPlayer().getCurrentRoom());
			}
			else if (world.getPlayer().hasItem(itemName))
				World.print("You already have that!\n\n");
			else
				World.print("You can't see any " + itemName + " here.\n\n");
		}
		// Handle "take [Item] from [Container]" command
		else if (params.length == 3) {
			// Handle errors
			if (!params[1].equals("from")) {
				World.print("I don't understand.\n\n");
				return;
			}

			String itemName = params[0];
			String containerName = params[2];

			// Search the player's inventory and room for the requested container
			Item container = world.getPlayer().hasItem(containerName) ? world.getPlayer().getItem(containerName) : world.getPlayer().getCurrentRoom().getItem(containerName);
			
			if (container == null) {
				World.print("You can't see any " + containerName + " here!\n\n");
				return;
			}
			if (!(container instanceof Container)) {
				World.print("The " + containerName + " can't hold things!\n\n");
				return;
			}
			if (!((Container)container).hasItem(itemName)) {
				World.print("The " + containerName + " doesn't have a " + itemName + ".\n\n");
				return;
			}
			// If we made it this far, then it's safe to take the item from the container.
			Item item = ((Container)container).getItem(itemName);
			((Container)container).doTake(item);
		}
		else {
			World.print("I don't understand.\n\n");
		}
	}

	@Override
	public String getHelpDescription() {
		return "[item] or [item] from [container]";
	}
}