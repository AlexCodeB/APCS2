package textadventure;

public class Exit extends Room{

	public Exit(String name, String description, boolean isLocked, World world) {
		super(name, description, isLocked, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doGo() {
		World.print("You're at the exit! The door closes behind you. No turning back now.");
		Player player = getWorld().getPlayer();
		if(player.hasRope() && !player.hasPillow()) {
			World.print("You shimmy down the rope onto ground level but don't have a pillow to break your fall. You are severely injured! Game over!\n\n");
			System.exit(0);
		}else if(!player.hasPillow() && !player.hasRope()) {
			World.print("You realize there is no way down the building and starve. Game over!\n\n");
			System.exit(0);
		}else if(player.hasPillow() && !player.hasRope()) {
			World.print("You attempt to use the pillow to break your fall but it doen't protect you enough as you become severely injured. Game over!\n\n");
			System.exit(0);
		}else {
			World.print("Have a great day at school! You win!\n\n");
			System.exit(0);
		}
	}
}