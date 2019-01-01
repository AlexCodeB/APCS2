package P6_Bruckhaus_Alexander_Solitaire;

public class DeckTester{
    public static void main(String[] args){
        Deck deck = new Deck();
        String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        
        System.out.println("Testing sort");
        for(int i = 0; i < values.length; i++){
            deck.add(symbols[i], values[i]);
        }
        deck.flipUp();
        System.out.println("Created Deck: " + deck);
        deck.shuffle();
        System.out.println("Shuffled Deck: " + deck);
        deck.sort();
        System.out.println("Sorted Deck : " + deck);
        

        for(int i = 0; i < values.length; i++){
            deck.add(symbols[i], values[i]);
        }
        System.out.println("Testing print deck: " + deck);
        System.out.println("Flipping deck");
        deck.flipUp();
        System.out.println("Testing print deck: " + deck);
        System.out.println("Shuffling deck");
        deck.shuffle();
        deck.flipUp();
        System.out.println("Testing print deck: " + deck);
        System.out.println("Flipping deck");
        deck.flipDown();
        System.out.println("Testing print deck: " + deck);
        
        System.out.println("TESTING getState()");
        System.out.println(deck.getState());
        
        for(int i = 0; i < 5; i++){
            deck.add(symbols[i], values[i]);
        }
        System.out.println("Testing print deck: " + deck);
        System.out.println("Flipping deck");
        deck.flipUp();
        System.out.println("Testing print deck: " + deck);
        System.out.println("Shuffling deck");
        deck.shuffle();
        deck.flipUp();
        System.out.println("Testing print deck: " + deck);
        System.out.println("Flipping deck");
        deck.flipDown();
        System.out.println("Testing print deck: " + deck);
        
        System.out.println("TESTING getState()");
        System.out.println(deck.getState());
        
        
        System.out.println("Testing Deck(String)");
        String deckState = new String("D:Q:12,D:2:2,D:8:8,D:J:11,D:5:5,D:5:5,D:4:4,D:4:4,D:2:2,D:6:6,D:9:9,D:7:7,D:3:3,D:A:1,D:T:10,D:3:3,D:A:1,D:K:13");
        System.out.println("Restoring State: " + deckState);
        deck = new Deck(deckState);
        deck.flipUp();
        System.out.println("Restored deck: " + deck);
    }
}