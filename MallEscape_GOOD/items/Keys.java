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
		if(room.getName().equals(World.CLOTHING_STORE)){
			getWorld().getRoom(World.EXIT).doUnlock();
			World.print("You unlock the exit door and find yourself on the second story.\n\n");
		}else if(room.getName().equals(World.MACYS)) {
			getWorld().getRoom(World.EXIT).doUnlock();
			World.print("You unlock the exit door and find yourself on the second story.\n\n");
		}else {
			World.print("The item doesn't fit anything here.\n\n");
		}
	}

}
