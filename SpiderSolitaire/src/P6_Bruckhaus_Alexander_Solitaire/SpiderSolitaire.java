//Spider Solitaire Activity 6 

//Alex Bruckhaus, Period 6, 12/5/2017
//This program took me 30 minutes.
//This section of making the game was by far the easiest part of this project. Activity 6 refreshed my memory of error handling and helped me understand it a little more. This activity was good practice 
//for handling errors and the different types of errors. This part of the assignment was actually pretty interesting because I could see the game from the player's perspective. Overall,
//this assignment helped refresh my memory.


//Spider Solitaire Activity 7
//Alex Bruckhaus, Period 6, 12/13/2017
//This program took me 1 hour and 30 minutes.
//This section was a little complicated because I was not really strong with reading and writing to files. However, with some time and some helps, it became easier and I started to understand
//it more. I got stuck writing my load method so I used the internet as a resource for understanding. From this assignment, I learned a lot about loading and saving. It was really
//satisfying to finish the assignment and have a fully working game.

package P6_Bruckhaus_Alexander_Solitaire;

import java.util.Scanner;
import java.util.InputMismatchException;


public class SpiderSolitaire
{
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;
        Scanner in = null;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                int destinationStack = 0;
                String symbol = input.next();
                int sourceStack = 0;
                try {
                    sourceStack = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Enter source stack as an integer");
                    input.nextLine();
                    continue;
                }
                try {
                    destinationStack = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Error: Enter destination stack as an integer");
                    input.nextLine();
                    continue;
                }
                board.makeMove(symbol, sourceStack - 1, destinationStack - 1);
            }
            else if (command.equals("draw")) {
                board.drawCards();
            }
            else if (command.equals("clear")) {
                int sourceStack = 0;
                try{
                    sourceStack = input.nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Error: Enter source stack as an integer");
                    input.nextLine();
                    continue;
                }
                board.clear(sourceStack - 1);
            }
            else if (command.equals("restart")) {
                board = new Board(NUM_STACKS, NUM_DECKS);
            }
            else if (command.equals("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }else if(command.equals("save")){
                board.save();
            }else if(command.equals("load")){
                board.load();
                //System.out.println("Loaded Board: ");
                //board.printBoard();
            }
            else {
                System.out.println("Invalid command.");
            }

            board.printBoard();

            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }

    public static void main(String[] args){
        SpiderSolitaire spider = new SpiderSolitaire();
        spider.play();
    }
}
