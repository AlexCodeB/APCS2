//Farm Reflection

//Alex Bruckhaus, Period 6, 2/11/2018
//This program took me 30 minutes.
//This program was quite simple, but relatively new to me. Although we touched the surface on inheritance earlier 
//in the school year, this assignment was very informative to me and helped me understand more about inheritance.
//The assignment itself was not too challenging, but took me a little while to complete because it was new to me.
//Overall, I learned a lot about inheritance and interfaces during this lab.

import java.util.ArrayList;
import java.util.Random;

public class P6_Bruckhaus_Alexander_FarmDriver {

	public static void main(String[] args){
		Farm f = new Farm();
		f.animalSounds();
	}
}

interface Animal{
	public String getSound(); 
	public String getType();
}

class Cow implements Animal{
	private String myType;
	private String mySound;

	Cow(){
		myType = "cow";
		mySound = "moo";
	}

	public String getSound(){
		return mySound; 
	}

	public String getType(){
		return myType; 
	}
}

class NamedCow extends Cow{
	private String myName;

	NamedCow(String name){
		myName = name;
	}

	public String getName() {
		return myName;
	}
}

class Chick implements Animal{
	private String myType;

	Chick(){
		myType = "chick";
	}

	public String getSound(){
		Random random = new Random();
		int r = random.nextInt(2);
		if(r == 0) {
			return "cheep";
		}else {
			return "cluck";
		} 
	}

	public String getType(){
		return myType; 
	}
}

class Pig implements Animal{
	private String myType;
	private String mySound;

	Pig(){
		myType = "pig";
		mySound = "oink";
	}

	public String getSound(){
		return mySound; 
	}

	public String getType(){
		return myType; 
	}
}

class Farm{
	private ArrayList <Animal> myFarm = new ArrayList <Animal>();

	public Farm(){
		myFarm.add(new Cow());
		myFarm.add(new Chick());
		myFarm.add(new Pig());
		myFarm.add(new NamedCow("Elsie"));
	}

	public void animalSounds(){
		Animal temp;
		for (int i = 0; i < myFarm.size(); i++){
			temp = myFarm.get(i);
			System.out.println(temp.getType() + " goes " + temp.getSound());
		}
		
		NamedCow named = (NamedCow)myFarm.get(3);
	    System.out.println(named.getName());
	}
}