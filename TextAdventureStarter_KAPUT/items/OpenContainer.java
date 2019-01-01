package items;

import java.util.ArrayList;

import interfaces.Container;
import textadventure.World;

/**
 * An OpenContainer is an Item that can contain things but is not closeable.
 * Examples are things like open boxes, jars without a lid, etc.  Once you
 * have implemented all the required methods of the Container interface,
 * you'll be able to make OpenContainer Items that can hold Items.
 */
public class OpenContainer extends Item implements Container {

	// Add an attribute to keep track of the Items in this container.
	// This could be an ArrayList, HashMap, or whatever you'd like.
	
	ArrayList<Item> items = new ArrayList<Item>();
	
	
	public OpenContainer(World world, String name, int weight, boolean isTakeable, String description) {
		super(world, name, weight, isTakeable, description);
		// Initialize your list of items here
	}

	@Override
	public void addItem(Item item) {
		items.add(item);
	}

	@Override
	public Item removeItem(String itemName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getItem(String itemName) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getName().equals(itemName)) {
				return items.get(i);
			}
		}
		return null;
	}

	@Override
	public boolean hasItem(Item item) {
		return false;
	}

	@Override
	public boolean hasItem(String itemName) {
		for(int i = 0; i < items.size(); i++) {
			if(items.get(i).getName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getItemString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doUse() {
		// TODO Auto-generated method stub
		
	}

	// Add the required methods of the Container interface here
}