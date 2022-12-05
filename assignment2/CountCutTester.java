package assignment2;

public class CountCutTester {

	public static void main(String[] args) {
		//Test #1 -> Regular test.
		Deck d1 = new Deck(6,1);
		Deck.Card d6 = d1.new PlayingCard("diamond", 6) ;//D6
		d1.addCard(d6);
		d1.printDeck();
		//Value at the bottom of deck is D6 which is 58 in this deck
		// 58 % 9 = 4, so 4 cards from the top should be moved
		d1.countCut();
		System.out.println("");
		d1.printDeck();
		System.out.println("----");
		
		//Test #2 -> Nothing should happen.
		Deck d2 = new Deck(2,2);
		Deck.Card c7 = d2.new PlayingCard("clubs", 7);
		d2.addCard(c7);
		d2.printDeck();
		//Value at the bottom of deck is C7, which is 7 in this deck
		//7 % 7 = 0.7 is a multiple of 7. So nothing should move
		d2.countCut();
		System.out.println("");
		d2.printDeck();
		System.out.println("----");
		
		//Test #3 -> Only one card moves.
		Deck d3 = new Deck(2,2);
		Deck.Card c8 = d3.new PlayingCard("clubs", 8);
		d3.addCard(c8);
		d3.printDeck();
		//Value at the bottom of deck is C8, which is 8 in this deck
		//8 % 7 = 1. So 1 card from the top should be moved.
		d3.countCut();
		System.out.println("");
		d3.printDeck();
		System.out.println("----");
		
		//Test #4 -> All cards but one move (Nothing happens)
		Deck d4 = new Deck(2,2);
		Deck.Card c6 = d4.new PlayingCard("clubs", 6);
		d4.addCard(c6);
		d4.printDeck();
		//Value at the bottom of deck is C6, which is 6 in this deck
		//6 % 7 = 6. So 6 card from the top should be moved.
		//But since those 6 cards get placed above the 7th card. Nothing changes.
		d4.countCut();
		System.out.println("");
		d4.printDeck();
		System.out.println("----");
		
		//Test #5 -> Funky Test (Probably not worth considering)
		Deck d = new Deck();
		Deck.Joker j1 = d.new Joker("red");
		Deck.Joker j2 = d.new Joker("black");
		Deck d5 = new Deck(2,2);
		//The jack here has a -1 value, because its from a different deck.
		d5.addCard(j1);
		d5.printDeck();
		//Value at the bottom deck is -1.
		//Modulo of negative numbers, is found by ignoring the negative sign.
		//-1 % 7 = 6. So like the last example, nothing should happen
		d5.countCut();
		d5.printDeck();
		System.out.println("----");
		System.out.println(-1 % 7);
	}

}
