package assignment2;

public class TripleCutTester {

	public static void main(String[] args) {
		//func1();
		//func2();
		//func3();
		//func4();
		 Deck deck = new Deck(5,2);
         System.out.println(deck.generateNextKeystreamValue());
	}

	public static void func1() {
		// For the edge case of tripleCut() when there are no cards after the 2nd card
		// secondCard is the tail
		System.out.println("-------------- TEST 1 -----------------");
		Deck deck = new Deck(1, 1);

		Deck.Card card = deck.locateJoker("red");
		Deck.Card card2 = deck.locateJoker("black");
		    
		deck.printDeck();
		System.out.println("");
		deck.tripleCut(card, card2);
		deck.printDeck();
		System.out.println("---------------------------------------");
	}
	public static void func2() {
		// For the edge case of tripleCut() when there are no cards before the 1st card 
		// firstCard is the head

		System.out.println("-------------- TEST 2 -----------------");
		Deck deck = new Deck(1, 1);
		Deck.Card card2 = deck.locateJoker("red");

		deck.printDeck();
		System.out.println("");
		deck.tripleCut(deck.head, card2);
		deck.printDeck();
	}
	public static void func3() {
		// for regular case
		System.out.println("-------------- TEST 3 -----------------");
		Deck deck = new Deck(3, 2);

		deck.shuffle();     // i used seed 37 for the shuffle, so if you
		                    // use that your output should be the same as mine
		                    // see Daniel's tester on shuffling

		Deck.Card card = deck.locateJoker("black");
		Deck.Card card2 = deck.locateJoker("red");

		deck.printDeck();
		System.out.println("");
		deck.tripleCut(card, card2);
		deck.printDeck();
	}
	public static void func4() {
		//Case where there is only 1 card before the firstcard
		//And where there is only 1 card after the secondcard.
		//Head and Tail are the only cards swapping.

		//Creating the cards
		Deck d = new Deck();
		Deck.PlayingCard c = d.new PlayingCard("Clubs", 1);
		Deck.PlayingCard c2 = d.new PlayingCard("Clubs", 2);
		Deck.Joker j1 = d.new Joker("red");
		Deck.Joker j2 = d.new Joker("black");

		//Creating the deck
		Deck d2 = new Deck();
		d2.addCard(c);
		d2.addCard(j1);
		d2.addCard(j2);
		d2.addCard(c2);
		d2.printDeck();
		//Triple Cut!
		d2.tripleCut(j1,j2);
		d2.printDeck();
	}
}
