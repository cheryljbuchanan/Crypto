import java.math.BigInteger;
import java.util.LinkedList;

public class textbookRSA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BigInteger c = new BigInteger("674472526620593903800497637242400187916753185909");
		long m = 0;
		
		m = (long)Math.cbrt(c.doubleValue());

		System.out.println(m);
		
		char word = 0;
		int letter = 0;

		LinkedList<Character> list = new LinkedList<Character>();

		//remove last 2 numbers from m, convert to char and add to list
		for(int i = 0; i< 8; i++){
			letter = (int) (m % 100);
			m /= 100;
			word = (char)letter;
			//would print out packward if didnt
			list.addFirst(word);
			System.out.print(word);
		}
		System.out.println();
		//prints with commas,
		//but could itterate through and do without or add to string 
		System.out.println(list);
	}

}

