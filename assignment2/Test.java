package assignment2;

import java.lang.reflect.Method;
import java.util.Random;

public class Test {
    public static final int seed = 809;

    public static void main(String[] args) {
        // The following runs all the
        // https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html
        Method[] methods = Test.class.getDeclaredMethods();
        for (Method method: methods) {
            try {
                Deck.gen = new Random(seed);
                System.out.println(method.getName());
                method.invoke(null, null);
            } catch (Exception e) {
                System.out.printf("Method %s could not be run due to %s.\n",
                        method.getName(),
                        e.toString());
            }
        }
    }

    public static void testConstructorSmall() {
        System.out.println("Testing constructor with 3-card deck:");
        Deck deck = new Deck(1, 1);
        System.out.println(deck);
    }

    public static void testConstructorFail1() {
        System.out.println("Testing constructor failing (too small):");
        try {
            Deck deck = new Deck(0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Test failed successfully.");
        }
    }

    public static void testConstructorFail2() {
        System.out.println("Testing constructor failing (too large):");
        try {
            Deck deck = new Deck(14, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Test failed successfully.");
        }
    }

    public static void testCopyConstructor() {
        System.out.println("Testing copy constructor:");
        Deck deck = new Deck(1, 1);
        System.out.println("Original:\n" + deck);
        Deck copy = new Deck(deck);
        System.out.println("Copy:\n" + copy);
    }

    public static void testTripleCut() {
        System.out.printf("Testing triple cut normal case with seed %d:\n", seed);
        Deck deck = new Deck(3, 2);
        deck.shuffle();

        Deck.Card card = deck.locateJoker("red");
        Deck.Card card2 = deck.locateJoker("black");

        System.out.println(deck);
        deck.tripleCut(card, card2);
        System.out.println(deck);
    }

    public static void testTripleCutSmall() {
        System.out.println("Testing triple cut with 3 cards:");
        Deck deck = new Deck(1, 1);
        System.out.println("Original:\n" + deck);
        Deck.Card middle = deck.head.next;
        deck.tripleCut(middle, middle);
        System.out.println("New:\n" + deck);
    }

    public static void testTripleCutFirstHead() {
        System.out.println("Testing triple cut when first card is head:");
        Deck deck = new Deck(1, 1);
        Deck.Card card2 = deck.locateJoker("red");

        System.out.println(deck);
        deck.tripleCut(deck.head, card2);
        System.out.println(deck);
    }

    public static void testTripleCutSecondTail() {
        System.out.println("Testing triple cut when second card is tail:");
        Deck deck = new Deck(1, 1);

        Deck.Card card = deck.locateJoker("red");
        Deck.Card card2 = deck.locateJoker("black");

        System.out.println(deck);
        deck.tripleCut(card, card2);
        System.out.println(deck);
    }

    public static void testTripleCutOneEitherSide() {
        // Case where there is only 1 card before the firstCard
        // And where there is only 1 card after the secondCard.
        // Head and Tail are the only cards swapping.
        System.out.println("Testing triple cut with only one card before/after first/secondCard:");
        // Creating the cards
        Deck d = new Deck();
        Deck.PlayingCard c = d.new PlayingCard("Clubs", 1);
        Deck.PlayingCard c2 = d.new PlayingCard("Clubs", 2);
        Deck.Joker j1 = d.new Joker("red");
        Deck.Joker j2 = d.new Joker("black");

        // Creating the deck
        Deck d2 = new Deck();
        d2.addCard(c);
        d2.addCard(j1);
        d2.addCard(j2);
        d2.addCard(c2);
        System.out.println(d2);

        // Triple Cut!
        d2.tripleCut(j1,j2);
        System.out.println(d2);
    }

    public static void testShuffle() {
        System.out.printf("Testing shuffle with seed %d:\n", seed);
        Deck deck = new Deck(3, 2);
        System.out.println("Original:\n" + deck);
        deck.shuffle();
        System.out.println("Shuffled:\n" + deck);
    }
}
