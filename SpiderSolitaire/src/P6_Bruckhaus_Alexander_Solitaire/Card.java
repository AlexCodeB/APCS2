package P6_Bruckhaus_Alexander_Solitaire;

//Solitaire activities 1-3

//Alex Bruckhaus, Period 6, 11/30/2017
//This program took me 2 hours.
//In this program, I did not have too much trouble in the card class. However, it took me some time to look up Comparable API and apply knowledge from there. After that, finishing the CardTester();
//class was not too difficult. For the Deck class, it was a bit more challenging because I had to brainstorm what methods to create. It took me a while to come up with some of the methods, but I did
//not end up shuffling the deck. This assignment helped me gain a better understanding of ArrayLists.

/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public Card(String str){
        //System.out.println("Constructing card with: [" + str + "]");
        String[] states = str.split(":");
        if(states[0].equals("U")){
            this.setFaceUp(true);
            //System.out.println("State is up");
        }else{
            this.setFaceUp(false);
            //System.out.println("State is down");
        }
        this.symbol = states[1];
        this.value = Integer.parseInt(states[2]);  
        //System.out.println("New Card: " + this.getState());
    }

    public String getState(){
        String faceState = new String();
        if(isFaceUp){
            faceState = "U";
        }else{
            faceState = "D";
        }
        return faceState + ":" + getSymbol() + ":" + getValue();
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter method to access this <code>Card</code>'s value.
     * 
     * @return value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter method to access whether this <code>Card</code> is face up.
     * 
     * @return whether or not this card is face up
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Setter method to set whether this <code>Card</code> is face up.
     * 
     * @param value a <code>boolean</code> containing whether the card is face up.
     * 
     */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }

    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @return whether or not this Card is equal to other.
     *  @param value a <code>boolean</code> containing whether this <code>Card</code> is equal to other card.
     */
    public boolean equals(Card other) {
        return this.getValue() == other.getValue();
    }

    /**
     * Returns the difference of values of this <code>Card</code> and other card.
     * 
     * @return difference of this <code>Card</code> and other card.
     */
    public int compareTo(Card other){
        return this.getValue() - other.getValue();
    }

    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if(!this.isFaceUp()){
            return "X";
        }else{
            return this.getSymbol() + ":" + this.getValue();
            //return this.getSymbol();
        }
    }
}
