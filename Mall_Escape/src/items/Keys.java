package items;

import textadventure.Room;
import textadventure.World;

public class Keys extends Item {

	public Keys(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		Room room = getWorld().getPlayer().getCurrentRoom();
		if(room.getName().equals(World.HALLWAY)){
			getWorld().getRoom(World.BATHROOM).doUnlock();
			World.print("You unlock the bathroom.\n\n");
		}else if(room.getName().equals(World.KITCHEN)) {
			getWorld().getRoom(World.OUTSIDE).doUnlock();
			World.print("You unlock the door leading outside.\n\n");
		}else {
			World.print("The item doesn't fit anything here.\n\n");
		}
	}

}
