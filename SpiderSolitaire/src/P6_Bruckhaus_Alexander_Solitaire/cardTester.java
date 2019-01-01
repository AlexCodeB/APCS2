package P6_Bruckhaus_Alexander_Solitaire;

public class cardTester{
    public static void main(String[] args){
        Card c1 = new Card("hi", 5);

        System.out.println("Testing toString():");
        System.out.println("c1: " + c1);

        System.out.println("Testing setFaceUp():");
        c1.setFaceUp(true);
        System.out.println("c1: " + c1);

        System.out.println("Testing getValue():");
        System.out.println("value: " + c1.getValue());

        Card c2 = new Card("hello", 4);
        System.out.println("Testing getSymbol():");
        System.out.println("symbol: " + c2.getSymbol());

        Card c3 = new Card("hi", 7);
        System.out.println("Testing isFaceUp():");
        System.out.println("card is face up: " + c3.isFaceUp());

        System.out.println("Testing equals():");
        System.out.println("c1 equal c2: " + c1.equals(c2));

        System.out.println("Testing == operator: ");
        System.out.println("c1 equal c2: " + (c1 == c2));

        System.out.println("Testing compareTo():");
        System.out.println("difference between c1 and c2 is: " + c1.compareTo(c2));
        System.out.println("difference between c3 and c2 is: " + c3.compareTo(c2));
    }
}