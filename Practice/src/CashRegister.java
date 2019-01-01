import java.util.Scanner;

public class CashRegister {
	private int pennies;
	private int nickels;
	private int dimes;
	private int quarters;


	public CashRegister(int pennies, int nickels, int dimes, int quarters) {
		this.pennies = pennies;
		this.nickels = nickels;
		this.dimes = dimes;
		this.quarters = quarters;
	}

	public double getValue() {
		return (pennies * 0.01) + (nickels * 0.05) + (dimes * 0.10) + (quarters * 0.25);
	}

	public void amounts() {
		System.out.printf("%-10s", "Quarters");
		System.out.printf("%11d\n", quarters);
		System.out.printf("%-10s", "Dimes");
		System.out.printf("%11d\n", dimes);
		System.out.printf("%-10s", "Nickels");
		System.out.printf("%11d\n", nickels);
		System.out.printf("%-10s", "Pennies");
		System.out.printf("%11d\n", pennies);
	}

	public void dispenseChange(double cents) {
		while(cents > 0) {
			if(cents >= 25){
				quarters--; 
				cents -= 25;
			}else if(cents >= 10 && cents < 25) {
				dimes--;
				cents -= 10;
			}else if(cents >= 5 && cents < 10) {
				nickels--;
				cents -= 5;
			}else {
				pennies--;
				cents -=1;
			}
		}
	}


	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		System.out.println("How much change would you like to dispense?");
		double changeDue = in.nextDouble();
		CashRegister cr = new CashRegister(5,
				5, 5, 5);
		System.out.println(cr.getValue());
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
		cr.amounts();
		System.out.println("--------------------------");
		
		System.out.println("Change for " + changeDue +  " cents");
		System.out.println("--------------------------");
		cr.dispenseChange(changeDue);
		cr.amounts();
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println(cr.getValue());
	}
}
