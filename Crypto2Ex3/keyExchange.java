import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;


public class keyExchange {
	static BigInteger m = null;
	static BigInteger base = null;
	static BigInteger valueA = null;
	static BigInteger valueB = null;
	static BigInteger keyA = null;
	static BigInteger keyB = null;
	static BigInteger pKeyA = null;
	static BigInteger pKeyB = null;
	static String msg = "";
	static int length = 1024;
	static Random r = new Random();
	public static void main(String[] args) {
		pKeyB = new BigInteger("13");
		pKeyA = new BigInteger("15");

		msg = msg +("Secret Key A :" + pKeyA + "\nSecret Key B :" + pKeyB );

		ComputeMessageAtoB();
		
	}

	public static void ComputeMessageAtoB(){

		m = BigInteger.probablePrime(length, r);
		base = BigInteger.probablePrime(length/2, r);

		valueA = base.modPow(pKeyA, m);

		msg = msg +("\nm = " + m +"\nbase = "+ base +"\nvalueA = "+ valueA);
		ComputeMessageBtoA(m, base, valueA);
	}

	public static void ComputeMessageBtoA(BigInteger m, BigInteger base, BigInteger valueA){		


		valueB = base.modPow(pKeyB, m);
		msg = msg +("valueB = " + valueB);
		ComputeKeyA(valueB);
	}

	public static void ComputeKeyA(BigInteger valueB){

		keyA = valueB.modPow(pKeyA, m);
		msg = msg +("\nkey a = " + keyA);
		ComputeKeyB(m, base, valueA);
	}


	public static void ComputeKeyB(BigInteger m, BigInteger base, BigInteger valueA){

		keyB = valueA.modPow(pKeyB, m);
		msg = msg +("\nkey b = " + keyB);
		writeToFile(msg);
	}

	public static void writeToFile(String s) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter("file.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(s);
			e.printStackTrace();
			
		}
		out.println(s);
		out.flush();
	}
}
