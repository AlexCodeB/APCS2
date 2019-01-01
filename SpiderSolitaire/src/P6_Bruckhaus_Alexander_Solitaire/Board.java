//Solitaire Activities 4-5 Reflection

//Alex Bruckhaus, Period 6, 12/3/2017
//This program took me 8 hours.
//This was by far the longest and most difficult assignment I programmed. For this assignment, I got help from my father at times. Throughout the entire assignment, both the logic and syntax was quite confusing
//for me. However, after working on it for several hours, the assignment came together, with my program adhering to all of the rules and specifications. From this assignment, I learned a lot more about ArrayLists, 
//and their importance to solving problems like these. I learned how to represent a sequence of stacks as an arraylist. This assignment also refreshed my knowledge of arrays. One of the most challenging
//aspects of this assignment was the problem solving. It took me a while to come up with solutions to different cases, but after writing down cases on paper and working through them, it became a lot easier
//for me to understand. This assignment also forced me to use plenty of helper methods to use across the  assignment. It also taught me the importance of testing methods periodically to check if they work.
//I also learned that simply saying, "return;" exits the method as seen in methods such as makeMove();. All in all, although this assignment was the most challenging to program, it produced the
//most satisfying result because I learned a lot.



package P6_Bruckhaus_Alexander_Solitaire;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileWriter;
import java.io.*;
import javax.swing.JFileChooser;
import java.nio.file.FileSystemException;
import java.util.Scanner;

public class Board
{   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    private int numStacks;
    private int numDecks;

    private Deck allCards = new Deck();
    private Deck drawPile;
    private Deck completed;
    private ArrayList<Deck> stacks;

    String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    final int RUN_LENGTH = values.length;

    int cardCount = 0;

    /**
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    
    public Board(int numStacks, int numDecks) {
        this.numStacks = numStacks;
        this.numDecks = numDecks;
        //Add cards to allCards deck:
        for(int i = 0; i < numDecks; i++){
            for(int j = 0; j < RUN_LENGTH; j++){
                allCards.add(symbols[j], values[j]);
                cardCount++;
            }
        }
        restart();
    }

    public void load(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        // Tell the JFileChooser to show a Save dialog window
        chooser.showOpenDialog(null);
        // Ask the JFileChooser for the File the user typed in or selected
        File loadFile = chooser.getSelectedFile();
        BufferedReader br = null;
        
        try{
            // Construct BufferedReader from FileReader
            br = new BufferedReader(new FileReader(loadFile));
            String deckState = br.readLine();
            drawPile = new Deck(deckState);
            deckState = br.readLine();
            completed = new Deck(deckState);
            stacks = new ArrayList<Deck>();
            numStacks = 0;
            //Add stacks: 
            while ((deckState = br.readLine()) != null) {
                //System.out.println("Restoring deck: " + deckState);
                Deck stack = new Deck(deckState);
                stacks.add(stack);
                numStacks++;
                //System.out.println("Restored deck: " + stack);               
            }        
            br.close();
        }catch (IOException e){
            System.out.println("Error: could not read from file.");
        }
        
        //flipUpTopStackCards();

    }

    public void save(){
        // Create a JFileChooser that points to the current directory
        JFileChooser chooser = new JFileChooser(".");
        // Tell the JFileChooser to show a Save dialog window
        chooser.showSaveDialog(null);
        // Ask the JFileChooser for the File the user typed in or selected
        File saveFile = chooser.getSelectedFile();
        // Create a FileWriter that can write to the selected File

        FileWriter f = null;

        try{
            f = new FileWriter(saveFile);
        }catch (IOException e){
            System.out.println("Error. Cannot write to file");
        }
        String deckState;
        try{
            deckState = drawPile.getState();
            f.write(deckState + "\n");
            deckState = completed.getState();
            f.write(deckState + "\n");
            for(Deck stack : stacks){
                deckState = stack.getState();
                f.write(deckState + "\n");
            }
            f.close();
        }catch (IOException e){           
            System.out.println("Error. Cannot write to file");
        }

    }

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     */
    public void makeMove(String symbol, int src, int dest) {
        //System.out.println("Symbol: [" + symbol + "], src: " + src + ", dest: " + dest);
        Deck deck = stacks.get(dest);
        Deck deckSrc = stacks.get(src);
        if(!hasRun(src, symbol)){
            System.out.println("Error: There is no run at the source stack with that symbol.");
            return;
        }else if(!isMatch(dest, symbol)){
            System.out.println("Error: There is no matching card at the destination stack.");
            return;
        }else{
            doMove(symbol, src, dest);
            deck.flipTopUp();
            deckSrc.flipTopUp();
        }
    }

    public boolean hasRun(int src, String symbol){
        Deck stack = stacks.get(src);
        int lastValue = -1;
        int value = -1;
        int i = stack.size() - 1;
        while(true){
            if(i < 0){
                System.out.println("i < 0");
                return false;
            }
            Card card = stack.get(i);
            //System.out.println("found card: " + card);
            if(card.isFaceUp() == false){
                System.out.println("card not up");
                return false;
            }
            if(card.getSymbol().equals(symbol)){
                //System.out.println("found symbol");
                return true;
            }
            value = card.getValue();
            if(lastValue != -1 && value != lastValue + 1){
                //System.out.println("not a run");
                return false;
            }
            //System.out.println("check next card...");
            lastValue = value;
            i--;
        }
    }

    public boolean isMatch(int dest, String symbol){
        //System.out.println("looking for symbol: [" + symbol + "]");
        Deck destStack = stacks.get(dest);
        for(int i = 0; i < RUN_LENGTH - 1; i++){
            if(symbols[i].equals(symbol)){
                if(destStack.getTop().getSymbol().equals(symbols[i + 1])){
                    return true;
                }else{
                    return false;
                }
            }
        } 
        return false;
    }

    public void doMove(String symbol, int src, int dest){
        Deck srcStack = stacks.get(src);
        Deck destStack = stacks.get(dest);
        Deck temp = new Deck();
        boolean found = false;
        while(!found && srcStack.size() > 0){
            temp.add(srcStack.pop());
            if(temp.getTop().getSymbol().equals(symbol)){
                found = true;
            }
        }
        while(temp.size() > 0){
            destStack.add(temp.pop());
            destStack.flipTopUp();
        }
    }

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        for(int i = 0; i < numStacks; i++){
            Deck stack = stacks.get(i);
            if(stack.isEmpty()){
                System.out.println("Error: You cannot draw cards when there are any empty stacks.");
                return;
            }
        }
        for(int i = 0; i < numStacks && drawPile.size() > 0; i++){
            int stackNum = i % numStacks;
            Deck stack = stacks.get(stackNum);
            Card card = drawPile.pop();
            stack.add(card.getSymbol(), card.getValue());
            flipUpTopStackCards();
        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        for(int i = 0; i < numStacks; i++){
            Deck stack = stacks.get(i);
            if(stack.size() > 0){
                return false;
            }
        }
        return drawPile.size() == 0;
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        if(!hasFullRun(sourceStack)){
            System.out.println("Error: There is no run on source stack " + sourceStack + ".");
            return;
        }
        Deck stack = stacks.get(sourceStack);
        for(int i = 0; i < RUN_LENGTH; i++){
            completed.add(stack.pop());
        }
    }

    public boolean hasFullRun(int sourceStack){
        Deck stack = stacks.get(sourceStack);
        for(int i = 0; i < RUN_LENGTH; i++){
            int index = stack.size() - i - 1;
            if(index < 0 || index > stack.size() - 1){
                return false;
            }
            Card card = stack.get(index);
            if(card.getValue() != i + 1){
                return false;
            }
        }       
        return true;
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        System.out.println("Stacks:");
        for(int i = 0; i < numStacks; i++){
            Deck stack = stacks.get(i);
            System.out.println((i + 1) + ": " + stack);
        }
        System.out.println("Draw Pile: " + drawPile);
    }

    public void flipUpTopStackCards(){
        for(int i = 0; i < numStacks; i++){
            Deck stack = stacks.get(i);
            Card top = stack.getTop();
            if(top != null){
                top.setFaceUp(true);
            }
        }
    }

    public void restart(){
        allCards.shuffle();

        //Add stacks:
        stacks = new ArrayList<Deck>();
        for(int i = 0; i < numStacks; i++){
            Deck stack = new Deck();
            stacks.add(stack);
        }

        //Distribute half of all cards to stacks:
        for(int i = 0; i < cardCount / 2; i++){
            int stackNum = i % numStacks;
            Card card = allCards.get(i);
            Deck stack = stacks.get(stackNum);
            stack.add(card.getSymbol(), card.getValue());
        }

        //Flip up top card of each stack:
        flipUpTopStackCards();

        //Move remaining cards to drawPile:
        drawPile = new Deck();
        for(int i = cardCount / 2; i < cardCount; i++){
            Card card = allCards.get(i);
            drawPile.add(card.getSymbol(), card.getValue());
        }

        completed = new Deck();
    }

    public void emptyDrawPile(){
        drawPile.removeCards();
    }

    public void emptyStacks(){
        for(int i = 0; i < numStacks; i++){
            stacks.get(i).removeCards();
        }
    }

    public void addRun(int stackNum){
        Deck stack = stacks.get(stackNum);
        for (int i = RUN_LENGTH - 1; i >= 0; i--){
            stack.add(symbols[i], values[i]);
        }
    }

    public void addReverseRun(int stackNum){
        Deck stack = stacks.get(stackNum);
        for (int i = 0; i < RUN_LENGTH; i++){
            stack.add(symbols[i], values[i]);
        }
    }

    public void revealStacks(){
        for (int i = 0; i < numStacks; i++){
            stacks.get(i).flipUp();
        }
    }

    public void emptyOneStack(int stackNum){
        stacks.get(stackNum).removeCards();
    }

    public void addRandomCards(int stackNum, int numCards){
        Random random = new Random();
        Deck stack = stacks.get(stackNum);
        for(int i = 0; i < numCards; i++){
            int r = random.nextInt(RUN_LENGTH);
            stack.add(symbols[r], values[r]);
        }
    }

    public void removeCardsFromStack(int stackNum, int numCards){
        Deck stack = stacks.get(stackNum);
        for(int i = 0; i < numCards; i++){
            stack.remove(0);
        }
    }

    public void removeTopCardsFromStack(int stackNum, int numCards){
        Deck stack = stacks.get(stackNum);
        for(int i = 0; i < numCards; i++){
            stack.pop();
        }
    }

    public static void main(String[] args){
        Board board = new Board(2, 2);

        System.out.println("\nTest printBoard():");
        board.printBoard();

        System.out.println("\nTest restart():");
        board.restart();
        board.printBoard();

        System.out.println("\nTest The stacks and draw pile have cards:");
        board.restart();
        board.printBoard();
        System.out.println("Board is empty: " + board.isEmpty());

        System.out.println("\nTest Some stacks have cards and the draw pile is empty");
        board.restart();
        board.emptyDrawPile();
        board.printBoard();
        System.out.println("Board is empty: " + board.isEmpty());

        System.out.println("\nTest The stacks are empty but the draw pile has at least one card");
        board.restart();
        board.emptyStacks();
        board.printBoard();
        System.out.println("Board is empty: " + board.isEmpty());

        System.out.println("\nTest The stacks and draw pile are empty ");
        board.restart();
        board.emptyStacks();
        board.emptyDrawPile();
        board.printBoard();
        System.out.println("Board is empty: " + board.isEmpty());

        System.out.println("\nTest drawCards()");
        board.restart();
        board.printBoard();
        System.out.println("Drawing cards: ");
        board.drawCards();
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest clear() A");
        board.revealStacks();
        board.printBoard();
        System.out.println("Checking for runs: ");
        for (int i = 0; i < 2; i++){
            System.out.println("Stack " + i + " has run: " + board.hasFullRun(i));
        }
        System.out.println("Clear first stack: ");
        board.emptyOneStack(0);
        board.printBoard();
        System.out.println("Adding run: ");
        board.addRun(0);
        board.revealStacks();
        board.printBoard();
        System.out.println("Stack 0 has run: " + board.hasFullRun(0));
        System.out.println("Clear run:");
        board.clear(0);
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest clear() B");
        board.revealStacks();
        board.printBoard();
        System.out.println("Checking for runs: ");
        for (int i = 0; i < 2; i++){
            System.out.println("Stack " + i + " has run: " + board.hasFullRun(i));
        }
        board.printBoard();
        System.out.println("Adding run: ");
        board.addRun(0);
        board.revealStacks();
        board.printBoard();
        System.out.println("Stack 0 has run: " + board.hasFullRun(0));
        System.out.println("Clear run:");
        board.clear(0);
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest clear() C");
        board.revealStacks();
        board.printBoard();
        System.out.println("Checking for runs: ");
        for (int i = 0; i < 2; i++){
            System.out.println("Stack " + i + " has run: " + board.hasFullRun(i));
        }
        System.out.println("Adding run: ");
        board.addRun(0);
        board.revealStacks();
        board.printBoard();        
        System.out.println("Adding random cards: ");
        board.addRandomCards(0, 5);
        board.revealStacks();
        board.printBoard();
        System.out.println("Stack 0 has run: " + board.hasFullRun(0));
        System.out.println("Try clearing a run:");
        board.clear(0);
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest clear() D");
        board.revealStacks();
        board.printBoard();
        System.out.println("Clear first stack: ");
        board.emptyOneStack(0);
        board.printBoard();
        System.out.println("Adding reverse run: ");
        board.addReverseRun(0);
        board.revealStacks();
        board.printBoard();
        System.out.println("Stack 0 has run: " + board.hasFullRun(0));
        System.out.println("Try clearing a run:");
        board.clear(0);
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest clear() E");
        board.revealStacks();
        board.printBoard();
        System.out.println("Clear first stack: ");
        board.emptyOneStack(0);
        board.printBoard();
        System.out.println("Adding run: ");
        board.addRun(0);
        board.revealStacks();
        board.printBoard();
        System.out.println("Removing some of the run:");
        board.removeCardsFromStack(0, 4);
        board.revealStacks();
        board.printBoard();
        System.out.println("Stack 0 has run: " + board.hasFullRun(0));
        System.out.println("Try clearing a run:");
        board.clear(0);
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest makeMove()");
        board.revealStacks();
        board.printBoard();
        System.out.println("Clear both stacks: ");
        board.emptyOneStack(0);
        board.emptyOneStack(1);
        board.printBoard();
        System.out.println("Adding runs to both stacks: ");
        board.addRun(0);
        board.addRun(1);
        board.revealStacks();
        board.printBoard();
        System.out.println("Remove 5 cards from stack 1");
        board.removeTopCardsFromStack(1, 5);
        board.revealStacks();
        board.printBoard();
        System.out.println("Move run with symbol 5 from stack 0 to stack 1");
        board.makeMove("5", 0, 1);
        board.revealStacks();
        board.printBoard();
        System.out.println("Move run with symbol 8 from stack 0 to stack 1");
        board.makeMove("8", 0, 1);
        board.revealStacks();
        board.printBoard();
        System.out.println("Move run with symbol 5 from stack 0 to stack 1");
        board.makeMove("5", 0, 1);
        board.revealStacks();
        board.printBoard();

        board = new Board(2, 2);
        System.out.println("\nTest drawCards()");
        board.revealStacks();
        board.printBoard();
        System.out.println("Draw Cards");
        board.drawCards();
        board.revealStacks();
        board.printBoard();
        System.out.println("Clear stack 1: ");
        board.emptyOneStack(0);
        board.revealStacks();
        board.printBoard();
        System.out.println("Draw Cards again");
        board.drawCards();
        board.revealStacks();
        board.printBoard();
    }
}

