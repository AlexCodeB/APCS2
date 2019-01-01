package items;

import textadventure.World;

public class Toaster extends Container {

	public Toaster(World world, String name, String description) {
		super(world, name, description);
	}

	@Override
	public void doUse() {
		if (numItems() > 1) {
			World.print("There are too many items in the " + getName() + ".\n\n");
			return;
		}
		if (numItems() <= 0) {
			World.print("There's nothing in the " + getName() + " to toast!\n\n");
			return;
		}
		Item item = getItems().get(0);
		if (item.getName().equals("bread")) {
			World.print("You toast the " + item.getName() + " transforming it into a nice, crisp piece of toast.\n\n");
			removeItem("bread");
			addItem(new Food(getWorld(), "toast", 2, isTakeable(), "Tasty toast!"));
		}
		else {
			World.print("You attempt to toast the " + item.getName() + " and burn the house down! Game over : ( \n\n");
			System.exit(0);
		}
	}
}