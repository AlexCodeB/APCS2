package items;

import textadventure.World;

public class Picture extends Item{

	public Picture(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		World.print("You stare at the picture of the mall construction intently and realize you're stuck on the second floor!");
	}

}
