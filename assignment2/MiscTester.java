package assignment2;

public class MiscTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deck deck = new Deck(2, 2); // AC 2C AD 2D RJ BJ

        int seed = 26;
        Deck.gen.setSeed(seed);
        deck.shuffle();        // BJ 2C 2D AD RJ AC
        deck.printDeck();
        int value = deck.generateNextKeystreamValue();
        System.out.println(value);
	}

}
