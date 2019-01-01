package PracticeSortingQuiz;

public class Card implements Comparable<Card> {

	/* Attributes */
	private char symbol;
	private int value;
	private String suit;
	private boolean isTrump;

	/* Constructors */
	public Card(char symbol, int value, String suit) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = false;
	}

	public Card(char symbol, int value, String suit, boolean isTrump) {
		this.symbol = symbol;
		this.value = value;
		this.suit = suit;
		this.isTrump = isTrump;
	}

	/* Getters and Setters */
	public char getSymbol() {
		return symbol;
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}
	
	public boolean isTrump() {
		return isTrump;
	}

	public void setTrump(boolean isTrump) {
		this.isTrump = isTrump;
	}

	/* Other methods */
	@Override
	public String toString() {
		return symbol + "" + suit.charAt(0);
	}

	@Override
	public int compareTo(Card o) {
		if(this.getSuit() == o.getSuit() && this.getValue() == o.getValue()) {
			return 0;
		}else if(this.getSuit() == o.getSuit() && !this.isTrump() && !o.isTrump()){
			return this.value - o.value;
		}else if(this.getSuit() == o.getSuit() && this.isTrump() && o.isTrump()) {
			return this.value - o.value;
		}else if(this.getSuit() != o.getSuit() && (this.isTrump() || o.isTrump())) {
			if(this.isTrump()) {
				return 1;
			}else {
				return -1;
			}
		}else{
			return this.getSuit().compareTo(o.getSuit());
		}
	}
}
