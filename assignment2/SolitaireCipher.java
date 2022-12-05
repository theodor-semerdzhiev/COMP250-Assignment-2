package assignment2;

import java.util.Arrays;

public class SolitaireCipher {
	public Deck key;
	private Deck decode_key;
	
	public SolitaireCipher (Deck key) {
		this.key = new Deck(key); // deep copy of the deck
		this.decode_key= new Deck(key);
	}

	public int[] getKeystream(int size) {
		int [] keystr=new int[size];
		//System.out.print("Size of KeyStream:"+size+"\n");
		for(int i=0; i< size; i++) {
			keystr[i]=key.generateNextKeystreamValue();
		}
		return keystr;
	}
	//FOR TESTING PURPOSES (REMOVE IN FINAL BUILD)
	public static void main (String [] args) {
		
	}

	public String encode(String msg) {
		char[] char_arr=msg.replaceAll("[^a-zA-Z]", "").toUpperCase().toCharArray();
		char[] encoded_msg=new char[char_arr.length];
		int[] keystr=getKeystream(char_arr.length);
		for(int i=0; i<char_arr.length; i++ ) {
			int newPos = (char_arr[i] - 'A' + keystr[i]) % 26;
			encoded_msg[i]=(char)('A'+ newPos);
			
		}
		return new String(encoded_msg);
	}

	public String decode(String msg) {
		/**** ADD CODE HERE ****/
		char[] char_arr=msg.toCharArray();
		char[] decoded_msg=new char[char_arr.length];
		SolitaireCipher cipher = new SolitaireCipher(decode_key);
		int[] keystr=cipher.getKeystream(char_arr.length);

		for(int i=0; i<char_arr.length; i++ ) {
			int newPos = Math.floorMod(char_arr[i] - 'A' - keystr[i], 26);
			decoded_msg[i]=(char)('A' + newPos);
		}
		return new String(decoded_msg);
	}

}
