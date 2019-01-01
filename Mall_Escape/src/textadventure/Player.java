package textadventure;

import items.Food;

/**
 * A Player can contain objects and knows the Room it's currently in.
 */
public class Player{
	private int health;
	private boolean hasPillow = false;
	private boolean hasRope = false;
	private boolean isWearingClothes = false;

	/** Room this Player is in at the moment */
	private Room currentRoom;
	
	/** Creates a player that starts in the given Room with no items */
	public Player(Room startRoom, World world, int health) {
		super();
		currentRoom = startRoom;
		health = 0;
	}

	/* Getters and setters */

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public void setCurrentRoom(Room newRoom) {
		currentRoom = newRoom;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public boolean hasRope() {
		return hasRope;
	}
	
	public void setHasRope(boolean hasRope) {
		this.hasRope = hasRope;
	}
	
	public boolean hasPillow() {
		return hasPillow;
	}
	
	public void setHasPillow(boolean hasPillow) {
		this.hasPillow = hasPillow;
	}
	

	public boolean isWearingClothes() {
		return isWearingClothes;
	}

	public void setWearingClothes(boolean isWearingClothes) {
		this.isWearingClothes = isWearingClothes;
	}
	
}