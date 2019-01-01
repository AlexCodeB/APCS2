package textadventure;

public class OutsideRoom extends Room{

	public OutsideRoom(String name, String description, boolean isLocked, World world) {
		super(name, description, isLocked, world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doGo() {
		//World.print(">>> in doGo() of OutsideRoom\n");
		World.print("Your carpool arrives.");
		Player player = getWorld().getPlayer();
		if(!player.getHasBrushedTeeth()){
			World.print("Your carpool mates notice that you haven't brushed your teeth. How embarassing! Game over!\n\n");
			System.exit(0);
		}else if(player.getHealth() == 2) {
			World.print("Your stomach churns as you realize you forgot to toast the bread. You get sick in on the way to school. How embarassing! Game over!\n\n");
			System.exit(0);
		}else if(!player.isWearingClothes()) {
			World.print("This isn't a dream, you're not wearing clothes. How embarassing! Game over!\n\n");
			System.exit(0);
		}else {
			World.print("Have a great day at school! You win!\n\n");
			System.exit(0);
		}
	}
}