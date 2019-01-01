package items;

import textadventure.Player;
import textadventure.World;

public class Toothbrush extends Item{

	public Toothbrush(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		Player player = getWorld().getPlayer();
		World.print("You brush your teeth.\n\n");
		player.setHasBrushedTeeth(true);
	}

}
