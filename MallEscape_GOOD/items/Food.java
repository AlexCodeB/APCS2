package items;

import interfaces.Edible;
import textadventure.Player;
import textadventure.World;

public class Food extends Item implements Edible{
	
	private static boolean isDirty = false;

	public Food(World world, String name, int weight, boolean takeable, String description, boolean isDirty) {
		super(world, name, weight, takeable, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doEat(){
		Player player = getWorld().getPlayer();
		World.print("You eat the " + getName() + " and feel stronger!\n\n");
		player.setHealth(player.getHealth() + getWeight());
		player.removeItem(this);
		player.setHasBrushedTeeth(false);
	}

	@Override
	public void doUse() {
		doEat();
	}

	public static boolean isDirty() {
		return isDirty;
	}

	public static void setDirty(boolean isDirty) {
		Food.isDirty = isDirty;
	}
}
