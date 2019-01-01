package textadventure;

import java.awt.Container;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import command.Command;
import items.Food;
import items.Item;
import items.Keys;
import items.Pillow;
import items.Clothes;
//import interfaces.Closeable;
//import items.CloseableContainer;
//import items.Clothes;
import items.Container;
//import items.Food;
//import items.Item;
//import items.Keys;
//import items.Scenery;
//import items.Toothbrush;
//import items.UselessItem;
import items.UselessItem;

public class World {

	//Room String constants
	public static final String BEDSTORE = "BEDSTORE";
	public static final String FOOD_COURT = "FOOD COURT";
	public static final String EXIT = "EXIT";
	public static final String CLOTHING_STORE = "CLOTHING STORE";
	public static final String SPORTS_STORE = "SPORTS STORE";
	public static final String MACYS = "MACY'S";

	/** Line word wrap length for console output */
	public static final int LINE_WRAP_LENGTH = 70;

	/* private World attributes */	
	/** The main player of the game, which is you */
	private Player player;

	/** Keeps track of the Rooms in this World using <Key, Value>
	 *  pairs of <RoomName, RoomObject>
	 */
	private HashMap<String, Room> rooms;

	/** Reads user input */
	private static final Scanner IN = new Scanner(System.in);
	/**
	 * Create the game and initialize its internal map.
	 */
	public World() {
		initializeNewGame();
	}

	public void initializeNewGame() {
		// Initialize attributes
		createRooms();
		player = new Player(rooms.get(BEDSTORE), this, 0);
	}
	
	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		print("You wake up in a dark room on a comfortable matress. Somehow you slept through mall closing and are now trapped in the mall."
				+ "Find items to help you escape the mall and safely drive home.\n\n");
		print("Welcome to Mall Escape!\n");
		print("(c) 2018 By Alex Bruckhaus\n");
		print("Type 'help' if you need help.\n\n");
		print(player.getCurrentRoom().getDescription());
	}

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void createRooms() {

		// Create a new HashMap to store the rooms in
		rooms = new HashMap<String, Room>();

		// Create rooms and add them to our HashMap
		rooms.put(BEDSTORE, new Room(BEDSTORE, "A dark room with several comfortable beds.\n\n", this));
		rooms.put(FOOD_COURT, new Room(FOOD_COURT, "Smells great! You see some leftover scraps of food around the room.\n\n", this));
		rooms.put(EXIT, new Room(EXIT, "You see an exit, but you are on the second story.\n\n", Room.LOCKED, this));
		rooms.put(CLOTHING_STORE, new Room(CLOTHING_STORE, "So much nice clothing around you.\n\n", this));
		rooms.put(SPORTS_STORE, new Room(SPORTS_STORE, "A fun place to relax and enjoy sports.\n\n", Room.LOCKED, this));
		rooms.put(MACYS, new Room(MACYS, "There are some nice clothes and furniture around here!\n\n", this));

		// Define room exits.  Order is north, east, south, west
		rooms.get(BEDSTORE).setExits(null, null, rooms.get(SPORTS_STORE), null);
		rooms.get(FOOD_COURT).setExits(rooms.get(SPORTS_STORE), null, rooms.get(MACYS), rooms.get(CLOTHING_STORE));
		rooms.get(EXIT).setExits(rooms.get(CLOTHING_STORE), rooms.get(FOOD_COURT), null, null);
		rooms.get(CLOTHING_STORE).setExits(null, rooms.get(FOOD_COURT), rooms.get(EXIT), null);
		rooms.get(SPORTS_STORE).setExits(null, null, null, rooms.get(BEDSTORE));
		rooms.get(MACYS).setExits(null, null, rooms.get(EXIT), null);

		// Create game items and add them to rooms
		Container bag = new Container(this, "bag", 15, Item.NOT_TAKEABLE, "A good place for storage.");
		UselessItem picture = new UselessItem(this, "picture", 2, Item.NOT_TAKEABLE, "A picture of the mall's construction in 1999");
		Keys keys = new Keys(this, "keys", 2, Item.TAKEABLE, "A shiny set of keys");
		UselessItem watch = new UselessItem(this, "watch", 2, Item.TAKEABLE, "A broken watch. At least it's right twice a day!");
		UselessItem blanket = new UselessItem(this, "blanket", 2, Item.TAKEABLE, "A very warm blanket.");
		rooms.get(BEDSTORE).addItem(bag);
		bag.addItem(watch);
		bag.addItem(keys);
		bag.addItem(blanket);
		
		rooms.get(FOOD_COURT).addItem(new Food(this, "taco", 2, Item.TAKEABLE, "Stale taco with something shiny inside."));
		rooms.get(BEDSTORE).addItem(new Pillow(this, "pillow", 1, Item.TAKEABLE, "A nice, comfortable. Looks like it can cushion falls!"));
		rooms.get(SPORTS_STORE).addItem(new UselessItem(this, "baseball_bat", 5, Item.TAKEABLE, "A strong, sturdy wooden baseball bat."));
		rooms.get(CLOTHING_STORE).addItem(new Clothes(this, "clothes_pile", 15, Item.TAKEABLE, "Some very warm clothes."));
		rooms.get(SPORTS_STORE).addItem(new Rope(this, "rope", 4, Item.TAKEABLE, "Tons of rope laying around near the basketballs."));
	}

	public Room getRoom(String key) {
		return rooms.get(key);
	}

	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Main play routine. Loops until end of play.
	 */
	public void play() {
		printWelcome();
		//updateTime();

		// Enter the main command loop. Here we repeatedly read commands and
		// execute them until the game is over.
		boolean finished = false;
		while (!finished) {
			System.out.print("> "); // print prompt
			String command = IN.nextLine();
			finished = processCommand(command);
			//finished = finished ? true : updateTime();  // Remove this line if you don't need time in your game
		}
		print("\nThank you for playing!  Good bye!\n\n");
	}

//	/**
//	 * Updates and prints out the current game time. Every second spent between game
//	 * commands becomes a second spent within the game.
//	 */
//	private boolean updateTime() {
//		long endMilliseconds = System.currentTimeMillis();		
//		localTime.add(Calendar.SECOND, (int)((endMilliseconds - startMilliseconds)/1000));
//		int hour = localTime.get(Calendar.HOUR_OF_DAY);
//		int minute = localTime.get(Calendar.MINUTE);
//		int second = localTime.get(Calendar.SECOND);		
//		startMilliseconds = endMilliseconds;
//		System.out.printf("The time is %d:%02d:%02d A.M.\n\n", hour, minute, second);
//		if (localTime.get(Calendar.MINUTE) >= 20) {
//			print("Ack!  You missed your carpool!  You're so triggered!!\n\n");
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Given a command, process (that is: execute) the command. If this command ends
	 * the game, true is returned, otherwise false is returned.
	 */
	private boolean processCommand(String command) {
		if (command == null || command.length() < 1) {
			return false;
		}
		if (new StringTokenizer(command).nextToken().equals("quit"))
			return true;
		else if (!Command.hasValidCommandWord(command)) {
			print("I don't know the command '" + new StringTokenizer(command).nextToken() + "'\n\n");
			return false;
		}
		else {
			Command.doCommand(command, this);
			return false;
		}
	}

	/**
	 * Helper method to print any String in a line-wrapped format.
	 * Prints the input String line-wrapped to a column width of LINE_WRAP_LENGTH,
	 * which is a constant defined at the top of this class.
	 * 
	 * Pseudocode and strategy:
	 * There are so many special cases, I could not have written this without planning it out.
	 * I decided to leave the comments here so you can see the strategy.
	 *  - Mr. Ferrante
	 * 
	 * while(length of str >= lengthLimit)
	 * 		Find the first occurrence of \n.
	 * 		If it's < lengthLimit then add substring(0, occurrence + 1) to output and reduce str by same amount.
	 * 		Else if there's a space at lengthLimit then add substring(0, lengthLimit) to output and
	 *  		reduce str by same amount
	 * 		Else find last occurrence of space within substring(0, lengthLimit)
	 * 			If no space anywhere then
	 * 				If there's a space at least somewhere in str, then add substring(0, firstSpace) to
	 * 					output and reduce str by same amount
	 * 				Else (no space anywhere)
	 * 					add rest of str to output and reduce by same amount
	 * 			Else (space somewhere within substring)
	 * 				add str.substring(0, index of last space) to output
	 * 				reduce str by same amount
	 * If there's anything left in str, add it.
	 */
	public static void print(String str) {
		String output = "";
		
		while (str.length() >= LINE_WRAP_LENGTH) {
			int lineBreakIndex = str.indexOf("\n");
			if (lineBreakIndex < LINE_WRAP_LENGTH) {
				output += str.substring(0, lineBreakIndex + 1);
				str = str.substring(lineBreakIndex + 1);
			}
			else if (str.charAt(LINE_WRAP_LENGTH) == ' ') {
				output += str.substring(0, LINE_WRAP_LENGTH);
				str = str.substring(LINE_WRAP_LENGTH + 1);
				if (str.length() > 0)
					output += "\n";
			}
			else {
				int lastSpaceIndex = str.substring(0, LINE_WRAP_LENGTH).lastIndexOf(" ");
				if (lastSpaceIndex == -1) {
					int firstSpaceIndex = str.indexOf(" ");
					if (firstSpaceIndex != -1) {
						output += str.substring(0, firstSpaceIndex);
						str = str.substring(firstSpaceIndex + 1);
						if (str.length() > 0)
							output += "\n";
					}
					else {
						output += str;
						str = "";
					}
				}
				else {
					output += str.substring(0, lastSpaceIndex);
					str = str.substring(lastSpaceIndex + 1);
					if (str.length() > 0)
						output += "\n";
				}
			}
		}
		if (str.length() > 0) {
			output += str;
		}
		System.out.print(output);
	}
}