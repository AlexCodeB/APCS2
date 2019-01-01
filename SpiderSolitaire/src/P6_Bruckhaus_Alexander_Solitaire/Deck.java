package P6_Bruckhaus_Alexander_Solitaire;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Deck{
    private ArrayList<Card> cards;
    private int count = 0;
    /**4
     * Constructor for objects of class Deck
     */
    public Deck(){
        cards = new ArrayList<Card>();
    }

    public Deck(String str){
        //System.out.println("Constructing string with: " + str);
        cards = new ArrayList<Card>();
        if(str.equals("")){
            return;
        }
        String[] cardStates = str.split(",");
        //System.out.println("Got cardStates: " + Arrays.toString(cardStates));
        if(cardStates.length < 1){
            return;
        }
        for(String cardState : cardStates){
            Card card = new Card(cardState);
            //System.out.println("CardState: " + cardState);
            cards.add(card);
        }
        //System.out.println("Done creating deck: " + cards);
    }
    
    public void sort() {
    		Card temp = new Card(null, 0);
    		int maxIndex = 0;
		for(int outer = 0; outer < cards.size() - 1; outer++) {
			maxIndex = 0;
			for(int inner = 0; inner < cards.size() - outer; inner++) {
				if(cards.get(inner).getValue() > cards.get(maxIndex).getValue()) {
					maxIndex = inner;
				}
			}
			temp = cards.get(count - outer - 1);
			cards.set(count - outer - 1, cards.get(maxIndex));
			cards.set(maxIndex, temp);
		}
    }

    public String getState(){
        String state = new String();
        for(int i = 0; i < cards.size(); i++){
            state += cards.get(i).getState() + ",";
        }
        if(state.length() > 0){
            state = state.substring(0, state.length() - 1);
        }
        return state;
    }

    public void add(String symbol, int value){
        Card card = new Card(symbol, value);
        cards.add(card);
        count++;
    }

    public boolean isEmpty(){
        return cards.size() < 1;
    }

    public void add(Card card){
        Card newCard = new Card(card.getSymbol(), card.getValue());
        cards.add(newCard);
        count++;
    }

    public Card get(int i){
        return cards.get(i);
    }

    public Card getTop(){
        if(cards == null || cards.size() == 0){
            return null;
        }
        return cards.get(cards.size() - 1);
    }

    public int size(){
        return cards.size();
    }

    public void removeCards(){
        cards = new ArrayList<Card>();
        count = 0;
    }

    public void remove(int i){
        cards.remove(i);
        count--;
    }

    public Card pop(){
        if(cards == null || size() < 1){
            return null;
        }
        Card card = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        count--;
        return card;
    }

    public void shuffle(){
        Random r = new Random();
        Card temp;
        for(int i = 0; i < count; i++){
            int j = r.nextInt(count);
            temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public void flipTopUp(){
        if (!isEmpty()){
            Card card = cards.get(cards.size() - 1);
            card.setFaceUp(true);
        }
    }

    public void flipUp(){
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            card.setFaceUp(true);
        }
    }

    public void flipDown(){
        for(int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            card.setFaceUp(false);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < cards.size(); i++){
            result += cards.get(i).toString() + ", ";
        }
        if(result.length() > 0){
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}

