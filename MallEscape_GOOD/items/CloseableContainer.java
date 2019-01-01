package items;

import interfaces.Closeable;
import textadventure.World;

public class CloseableContainer extends Container implements Closeable {
	private boolean isOpen;
	
	public CloseableContainer(World world, String name, int weight, boolean isTakeable, boolean isOpen, String description) {
		super(world, name, weight, isTakeable, description);
		this.isOpen = isOpen;
	}

	@Override
	public boolean isOpen() {
		return isOpen;
	}

	@Override
	public void doOpen() {
		isOpen = OPEN;
		World.print("Opened.\n\n");
	}

	@Override
	public void doClose() {
		isOpen = CLOSED;
		World.print("Closed.\n\n");
	}
	
	@Override
	public void doExamine() {
		if(isOpen) {
			World.print("Inside the " + getName() + " you see "  + getItemString() + ".\n\n");
		}else {
			World.print("The " + getName() + " is Closed\n\n");
		}
	}
	
	@Override
	public Item doTake(Item item) {
		if(isOpen) {
			super.doTake(item);
			return item;
		}else {
			World.print("It's closed.\n\n");
			return null;
		}
	}	
	
	@Override
	public Item doPut(Item item, Container source) {
		if(isOpen) {
			super.doPut(item, source);
			return source;
		}else {
			World.print("It's closed.\n\n");
			return null;
		}
	}
}
