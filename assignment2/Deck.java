package assignment2;

import java.util.Random;

public class Deck {
	public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
	public static Random gen = new Random();
	public int numOfCards; // contains the total number of cards in the deck
	public Card head; // contains a pointer to the card on the top of the deck

	public Deck(int numOfCardsPerSuit, int numOfSuits) {
		//Constructor works
		if(numOfCardsPerSuit < 1 || numOfCardsPerSuit > 13) {
			throw new IllegalArgumentException("numOfCardsPerSuit: " + numOfCardsPerSuit + " is not a valid");
		} else if(numOfSuits < 1 || numOfSuits > 4) {
			throw new IllegalArgumentException("numOfSuits: " + numOfSuits + " is not a valid");
		}
		numOfCards=(numOfCardsPerSuit*numOfSuits) +2;
		head=new PlayingCard(suitsInOrder[0],1);
		Card tmp=head;
		for(int i=1; i < numOfSuits+1; i++) {
			for(int j=1; j < numOfCardsPerSuit+1; j++) {
				if(i==1 && j==1) {
					continue;
				}
				tmp.next=new PlayingCard(suitsInOrder[i-1],j);
				tmp.next.prev=tmp;
				tmp=tmp.next;
			}
		}
		tmp.next=new Joker("red");
		tmp.next.prev=tmp;
		tmp=tmp.next;
		tmp.next=new Joker("black");
		tmp.next.prev=tmp;
		tmp=tmp.next;
		head.prev=tmp;
		tmp.next=head; 
		//shuffle();
	}

	//main method for testing purposes
	public static void main (String[] args) {
//		Deck deck1=new Deck(3,2);
//		Deck deck2=deck1;
//		SolitaireCipher cipher1=new SolitaireCipher(deck1);
//		String msg1 = cipher1.encode("GBEHNIGSWUKOEEY");
//		System.out.println(msg1);
//		
//		
//		SolitaireCipher cipher2=new SolitaireCipher(deck1);
//		String msg2 = cipher2.encode("CBLNWLRGE");
//		System.out.println(cipher2.decode("CBLNWLRGE"));
//		
//		
//		SolitaireCipher cipher3=new SolitaireCipher(deck1);
//		String msg3 = cipher3.encode("KEVBPRBZ");
//		System.out.println(cipher3.decode("KEVBPRBZ"));
//		
//		
//		SolitaireCipher cipher4=new SolitaireCipher(deck1);
//		String msg4 = cipher4.encode("CBLTHIL");
//		System.out.println(cipher4.decode("CBLTHIL"));
//		SolitaireCipher cipher2=new SolitaireCipher(deck2);
//		String encodedMSG = cipher.encode("Is that you, Bob?");
//		String crypt1=cipher1.encode("HI MY NAME IS THEO BECAUSE YOUR COOL");
//		System.out.println(crypt1);
//		System.out.println(cipher1.decode(cipher1.encode("HIMOTHERFUCKER")));
//		System.out.println(cipher1.decode(crypt));
	}
	public Deck(Deck d) {
		numOfCards=d.numOfCards;
		if (numOfCards == 0) {
			return;
		}
		head=d.head.getCopy();
		Card tmp1=head;
		Card tmp2=d.head;
		while (tmp2.next != d.head) {
			tmp1.next=tmp2.next.getCopy();
			tmp1.next.prev=tmp1;
			tmp1=tmp1.next;
			tmp2=tmp2.next;
		}
		tmp1.next=head;
		head.prev=tmp1;
	}
	/*
	 * For testing purposes we need a default constructor.
	 */
	public Deck() {}

	//O(1)
	public void addCard(Card c) {
		if(this.numOfCards == 0) {
			head=c;
			c.next=c;
			c.prev=c;
			numOfCards++;
			return;
		} else if(this.numOfCards == 1) {
			head.next=c;
			head.prev=c;
			c.next=head;
			c.prev=head;
			numOfCards++;
			return;
		}
		c.prev=head.prev;
		c.next=head;
		head.prev.next=c;
		head.prev=c;
		this.numOfCards++;
	}

	//O(n)
	public void shuffle() {
		Card[] card_arr=new Card[this.numOfCards];
		Card tmp1=head;
		
		if(this.numOfCards == 0) {
			return;
		}
		card_arr[0]=head;
		
		int count=1;
		//creates array of deck
		while(tmp1.next!=head) {
			card_arr[count]=tmp1.next;
			tmp1=tmp1.next;
			count++;
		}
		//Fisher-Yates shuffle algorithm
		int rand_int;
		for(int j=card_arr.length-1; j>=1; j--) {
			rand_int=gen.nextInt(j+1);
			tmp1=card_arr[rand_int];
			card_arr[rand_int]=card_arr[j];
			card_arr[j]=tmp1;
		}
		//resets the DLinked list with the next shuffled array
		head=card_arr[0];
		tmp1=head;
		for(int i=1; i<card_arr.length; i++) {
			tmp1.next=card_arr[i];
			tmp1.next.prev=tmp1;
			tmp1=tmp1.next;		
		}
		//connects head and tail
		head.prev=tmp1;
		tmp1.next=head;
	}

	//O(n)
	public Joker locateJoker(String color) {
		Card tmp=head;
		if (tmp == null) {
			return null;
		}
		do {
			if(tmp instanceof Joker && ((Joker) tmp).getColor() == color) {
				return (Joker) tmp;
			}
			tmp=tmp.next;
		} while(tmp!=head);
		
		return null;
	}

	//O(p)
	public void moveCard(Card c, int p) {
		if(this.numOfCards ==1) {
			return;
		}
		Card tmp=c;
		c.prev.next=c.next;
		c.next.prev=c.prev;
		for(int i=0; i<p; i++) {
			tmp=tmp.next;
		}
		c.prev=tmp;
		c.next=tmp.next;
		tmp.next.prev=c;
		tmp.next=c;
	}

	//O(1)
	public void tripleCut(Card firstCard, Card secondCard) {
		//WORKS
		Card newhead;
		if(firstCard.prev==secondCard && firstCard==head) {
			//case where firstCard is head and secondCard is tail
			return;
		} else if(secondCard.next!=head && head!=firstCard) {
			//case where no joker at bottom or top of` deck
			newhead=secondCard.next;
			secondCard.next.prev=firstCard.prev;
			firstCard.prev.next=secondCard.next;
			firstCard.prev=head.prev;
			head.prev.next=firstCard;
			secondCard.next=head;
			head.prev=secondCard;
			head=newhead;
		} else if (secondCard.next==head && head!=firstCard) {
			//case where joker at bottom of deck
			newhead=firstCard;
			secondCard.next.prev=firstCard.prev;
			firstCard.prev.next=secondCard.next;
			firstCard.prev=head.prev;
			head.prev.next=firstCard;
			secondCard.next=head;
			head.prev=secondCard;
			head=newhead;
		} else if (firstCard==head && secondCard !=head.prev) {
			//case where joker at top of deck
			head=secondCard.next;
		}
	}

	//O(n)
	public void countCut() {
		if(numOfCards == 0) {
			return;
		}
		int cut_count=Math.floorMod(head.prev.getValue(), numOfCards);
		if(cut_count == this.numOfCards-1) {
			return;
		} else if(cut_count == 0) {
			return;
		}
		Card tmp=head;
		for(int i=1; i < cut_count; i++) {
			tmp=tmp.next;
		}
		Card newHead=tmp.next;
		Card tmp1=head.prev;
		Card tmp2=head.prev.prev;
		head.prev=tmp2;
		tmp2.next=head;
		tmp.next=tmp1;
		tmp1.prev=tmp;
		tmp1.next=newHead;
		newHead.prev=tmp1;
		head=newHead;
	}

	//O(n)
	public Card lookUpCard() {
		Card tmp=head;
		for(int i=0; i<head.getValue(); i++) {
			tmp=tmp.next;
		}
		if(tmp instanceof Joker) {
			return null;
		} else {
			return tmp;
		}
	}

	public int generateNextKeystreamValue() {
		while(true) {
			Card firstJoker=null;
			Card SecondJoker=null;
			Card tmp=head;
			moveCard(locateJoker("red"), 1);
			moveCard(locateJoker("black"),2);
			while(tmp.next!=head || tmp==head.prev) {
				if(tmp instanceof Joker) {
					if(firstJoker==null) {
						firstJoker=tmp;
					} else {
						SecondJoker=tmp;
						break;
					}
				}
				tmp=tmp.next;
			} 
			tripleCut(firstJoker,SecondJoker);
			countCut();
			if(lookUpCard()==null) {
				continue;
			} else {
				return lookUpCard().getValue();
			}
		}
	}

///////////////////////////////////////////////////////////////////////////////////////
	public abstract class Card { 
		public Card next;
		public Card prev;

		public abstract Card getCopy();
		public abstract int getValue();

	}

	public class PlayingCard extends Card {
		public String suit;
		public int rank;

		public PlayingCard(String s, int r) {
			this.suit = s.toLowerCase();
			this.rank = r;
		}

		public String toString() {
			String info = "";
			if (this.rank == 1) {
				//info += "Ace";
				info += "A";
			} else if (this.rank > 10) {
				String[] cards = {"Jack", "Queen", "King"};
				//info += cards[this.rank - 11];
				info += cards[this.rank - 11].charAt(0);
			} else {
				info += this.rank;
			}
			//info += " of " + this.suit;
			info = (info + this.suit.charAt(0)).toUpperCase();
			return info;
		}

		public PlayingCard getCopy() {
			return new PlayingCard(this.suit, this.rank);   
		}

		public int getValue() {
			int i;
			for (i = 0; i < suitsInOrder.length; i++) {
				if (this.suit.equals(suitsInOrder[i]))
					break;
			}

			return this.rank + 13*i;
		}

	}

	public class Joker extends Card{
		public String redOrBlack;

		public Joker(String c) {
			if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black")) 
				throw new IllegalArgumentException("Jokers can only be red or black"); 

			this.redOrBlack = c.toLowerCase();
		}

		public String toString() {
			//return this.redOrBlack + " Joker";
			return (this.redOrBlack.charAt(0) + "J").toUpperCase();
		}

		public Joker getCopy() {
			return new Joker(this.redOrBlack);
		}

		public int getValue() {
			return numOfCards - 1;
		}

		public String getColor() {
			return this.redOrBlack;
		}
	}
	void printDeck()
	{
		Card currentCard = head;
		System.out.println("Previous\tCurrent\t\t\tNext ");
		for(int cardIndex = 0; cardIndex < numOfCards; cardIndex++)
		{
			System.out.println(currentCard.prev + " <--------- " + currentCard + " ---------> " + currentCard.next + ", Values are: " +currentCard.prev.getValue() + " and " + currentCard.getValue() + " and " + currentCard.next.getValue());
			currentCard = currentCard.next;
		}

		System.out.println("Number of cards: " + numOfCards);
	}
}
