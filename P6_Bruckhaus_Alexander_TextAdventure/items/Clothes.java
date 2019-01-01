package items;

import textadventure.Player;
import textadventure.World;

public class Clothes extends Item {

	public Clothes(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
	}

	@Override
	public void doUse() {
		Player player = getWorld().getPlayer();
		World.print("You put on your " + getName() + " for the day.\n\n");
		player.removeItem(this);
		player.setWearingClothes(true);
		player.removeItem(this);
	}

}
