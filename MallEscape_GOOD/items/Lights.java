package items;

import textadventure.Player;
import textadventure.World;

public class Lights extends Item{

	public Lights(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		Player player = getWorld().getPlayer();
		if(player.hasLights()) {
			World.print("The lights turn on and you can see a rusty, metal key under one of the matresses!");
		}
		
	}
	

}
