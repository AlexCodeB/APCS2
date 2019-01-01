package items;

import textadventure.Player;
import textadventure.World;

public class RustyKey extends Container{
	public RustyKey(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		Player player = getWorld().getPlayer();
		
		if(player.hasLights()) {
			addItem(new RustyKey(getWorld(), "rusty_key", 1, isTakeable(), "A rusty, dusty golden key!\n\n"));
			World.print("As you turn on the lights, you see a rusty key glimmer from the distance.\n\n");
		}
		
	}
}