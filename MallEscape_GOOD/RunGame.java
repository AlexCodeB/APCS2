//TextAdventure Reflection

//Alex Bruckhaus, Period 6, 2/25/2018
//This program took me 3 hours.
//Overall, this assignment was quite challenging but fun and informative at the same time. What took me the most time to figure out was creating the Command
//classes, especially CommandPut. The hints and solutions were quite helpful and gave me a clearer picture of the assignment. From this assignment, I learned a lot
//about inheritance, parent classes, subclasses, packages, and interfaces. It was very interesting to see all the code come to life and to see
//how java can create a whole playable game like this one. I think this assignment will be very helpful and will guide me in the right direction when I 
//begin creating my playable text adventure game. This assignment was very enjoyable and informative.


import textadventure.World;

public class RunGame {
	
	public static void main(String[] args) {
		
		World game = new World();		
		game.play();
		
	}	
}