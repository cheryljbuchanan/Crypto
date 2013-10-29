import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class vigenereCipher { 
	private static int keyLength = 0;

	//public static String encrypted;
	//TODO need to sort files to pass as parameters
	//TODO need to split method so get max is seperate to freq maybe
	public static void main(String [] Args) throws IOException{
		String pw = "eutv";
		String key;
		Double maximum;
		//String encrypted ="";
		String test = "psqmedv juugqbftj's ujh aexhnuwuet qi sigulpen hpnpet, db asvkus ernbp goznh";
		Scanner s = new Scanner(new File("vigTxt.txt"));
		String stuff = new Scanner(new File("file.txt")).useDelimiter("\\Z").next();

		String encrypted = encrypt(pw);

		//System.out.println("test");
		//System.out.print(encrypted);
		//System.out.println();
		//System.out.println("****************test 2****************");
		//System.out.println();
		//String plainText = decrypt(encrypted, pw);
		//System.out.print(plainText);
		freq();
		breakText();


	}

	public static String passWord(String pw){

		// user input!!!!
		return pw;
	}

	public static String encrypt(final String word){
		String x ="";
		Scanner s = null;
		try {
			s = new Scanner(new File("vigTxt.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char result = 0;
		s.useDelimiter(System.getProperty("line.separator"));
		int k = 0;
		while(s.hasNext()){	
			String line = s.next().toLowerCase();

			for(int i = 0; i < line.length(); i++){
				int j = (int)line.charAt(i);
				if(!(j >= 97 && j <= 122)){
					result = line.charAt(i);
					//System.out.print(line.charAt(i));
					x += line.charAt(i);
				}else {
					//Ci = Ti + Ki (%m)
					result = (char)((j + word.charAt(k)-2 *97)% 26 +97);
					x += result;
					k = ++k % word.length();
				}
			}
			x += "\n";
		}
		return x;
	}

	public static String decrypt(String s, final String word){

		char result;
		String decrypted = "";
		String line = s.toLowerCase();
		int k = 0;
		for(int i = 0; i < line.length(); i++){
			int j = (int)line.charAt(i);

			if(!(j >= 97 && j <= 122)){
				decrypted += line.charAt(i);
			}else {
				//Ti = Ci - Ki (%m)
				result = (char)(((j - word.charAt(k)+26)% 26) + 97);
				decrypted += result;
				k = ++k % word.length();
			}
		}
		return decrypted;
	}


	//change: take parameter Array and int(for di,tri etc, will need break)
	public static void freq() throws IOException{
		int count = 0;
		BufferedReader file = new BufferedReader(new FileReader("file.txt"));
		LinkedHashMap<String, Double> freq =  new LinkedHashMap<String, Double>();
		try{
			StringBuilder s = new StringBuilder();
			String line = file.readLine();

			while (line != null){
				s.append(line);
				s.append('\n');

				line = file.readLine();
			}

			String x = s.toString().toLowerCase();

			for(int i = 0; i < x.length()-3; i++){
				String c = x.substring(i, i+3);
				Double val = (Double) freq.get(new String(c));
				if(val != null){
					freq.put(c, new Double(val + 1));
					count++;
				}else{
					freq.put(c,1.0);
				}
			}		
		}finally{file.close();}

		/*Iterator<Map.Entry<String, Double>> a = freq.entrySet().iterator(); 
		while(a.hasNext()){
			String key = a.next().getKey().toString();
			double percent = ((((double)freq.get(key)/count)*100));
			DecimalFormat dp = new DecimalFormat("#.##");
		    freq.put(key, percent);
			System.out.println(key + " = " + dp.format(freq.get(key)) +"%");
		}
		System.out.println("letters counted = " + count);
		System.out.println();*/

		Entry<String,Double> max = null;

		for(Entry<String, Double> e : freq.entrySet()) {
			if (max == null || e.getValue() > max.getValue()) {
				max = e;
			}
		}
		String maximum = max.getKey(); 
		System.out.println(maximum);
		//return maximum;
		getKeyLength(maximum);

	}

	public static int getKeyLength(String maximum) throws IOException{

		String s = new Scanner(new File("file.txt")).useDelimiter("\\Z").next();

		int x = s.indexOf(maximum);
		int y = s.indexOf(maximum, x +1);
		int z = s.indexOf(maximum, y +1);

		int dist = y - x;
		int dist2 = z - y;

		int gcd = 0;
		while (dist2 !=0 && dist != 0 ) {
			if(dist % dist2 == 0){
				gcd = dist2;
			}
			int aux = dist2; 
			dist2 = dist % dist2;
			dist = aux;
		}
		keyLength = gcd;
		System.out.println("gcd "+gcd);
		return keyLength;

	}

	public static void breakText() throws FileNotFoundException{
		//split into array with string length gcd
		String s = new Scanner(new File("file.txt")).useDelimiter("\\Z").next();
		char[] stuff = s.toCharArray();  
	
		List<Character> a = new ArrayList<Character>();
		List<Character> b = new ArrayList<Character>();
		List<Character> c = new ArrayList<Character>();
		List<Character> d = new ArrayList<Character>();
		List<Character> e = new ArrayList<Character>();

		int counter = 1;
		for (int i = 0; i < stuff.length; i++) {
		    if (counter == 1) {
		        a.add(stuff[i]); 
		        counter++;
		        continue;
		    }
		    if (counter == 2) {
		        b.add(stuff[i]); 
		        counter++;
		        continue;
		    }
		    if (counter == 3) {
		        c.add(stuff[i]); 
		        counter++; 
		        continue;
		    }
		    if (counter == 4) {
		        d.add(stuff[i]); 
		        counter++; 
		        continue;
		    }
		    if (counter == 5) {
		        e.add(stuff[i]); 
		        counter = 1; 
		        continue;
		    }
		}
		
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		System.out.println();
		Set<Character> unique = new HashSet<Character>(a);
		for (char key : unique) {
		    System.out.println(key + ": " + Collections.frequency(a, key));
		}
		
			
	}

	
}
