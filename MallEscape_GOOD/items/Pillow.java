package items;

import textadventure.World;

public class Pillow extends Item{

	public Pillow(World world, String name, int weight, boolean takeable, String description) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doUse() {
		World.print("A fluffy pillow that looks so comfortable you decide to rest your head on it and zzz.... You fall asleep! Game over!\n\n");
		System.exit(0);
	}
	
}
