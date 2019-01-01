package items;

import textadventure.Player;
import textadventure.World;

public class Rope extends Item{

	public Rope(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		Player player = getWorld().getPlayer();
		player.setHasRope(true);
	}
}
