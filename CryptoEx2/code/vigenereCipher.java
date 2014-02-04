import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class vigenereCipher { 
	private static int keyLength = 0;
	private static String x ="";
	static String KEY = "";
	static String pw ="";
	static int count = 0;
	
	public static void main(String [] Args) throws IOException{
		String maximum;
		LinkedHashMap<String, Double> freq =  new LinkedHashMap<String, Double>();
		//Encrytp and decrypt text
		getFileIn("vigTxt.txt");
		passWord();
		String encrypted = encrypt(pw,x);
		System.out.println("\nEncrypted Text :");
		System.out.println(encrypted);
		System.out.println("\nDecrypted Text :");
		System.out.println(decrypt(encrypted, pw));

		//Frequency analysis
		System.out.println();
		System.out.println("\nencrypted text letter frequency\n");
		freq(encrypted, 1, freq);
		LinkedHashMap<String, Double> sortedMap = sortMap(freq);
		printMap(sortedMap, false);
		maximum = getMax(freq);
		System.out.println("\nMost frequent letter :" + maximum);
		
		LinkedHashMap<String, Double> f =  new LinkedHashMap<String, Double>();
		//Get key and plain text
		getFileIn("file.txt");
		freq(x, 3, f);
		LinkedHashMap<String, Double> sortedMap2 = sortMap(f);
		printMap(sortedMap2, true);
		maximum = getMax(sortedMap2);
		System.out.println("\nMost frequent trigram :" + maximum);
		getKeyLength(maximum, x);
		breakText(x,0,"");
		String decrypted = (decrypt(x, KEY));
		System.out.println("\nDecryption Key : "+KEY);
		System.out.println(decrypted);
		
		getFileIn("ncl.txt");
		//passWord();
		pw = "ncl";
		encrypted = encrypt(pw,x);
		System.out.println("\n Newcastle University text, password ncl");
		System.out.println(encrypted);
	}

	public static String passWord(){

		Scanner scanner = new Scanner (System.in);
		System.out.print("Enter password");  
		pw = scanner.next();
		//change password so it is only lower case letters
		pw.toLowerCase().replaceAll("[^a-z]+", "");
		return pw;
	}

	public static String getFileIn(String fileIn) throws IOException{

		BufferedReader file = new BufferedReader(new FileReader(fileIn));
		String a = "";
		try{
			StringBuilder s = new StringBuilder();
			String line = file.readLine();

			while (line != null){
				s.append(line);
				s.append('\n');

				line = file.readLine();
			}
			a = s.toString().toLowerCase();
		}finally{file.close();}
		x = a;
		return x;
	}
	// takes user defined password and encrypts text

	public static String encrypt(String word, String s){

		char result;
		String encrypted = "";
		//String line = s.toLowerCase();
		int k = 0;
		for(int i = 0; i < s.length(); i++){
			int j = (int)s.charAt(i);
			if(!(j >= 97 && j <= 122)){
				encrypted += s.charAt(i);
			}else {
				//Ci = Ti + Ki (%m)
				result = (char)(((j + word.charAt(k)-2*97)% 26) + 97);
				encrypted += result;
				k = ++k % word.length();
			}
		}
		return encrypted;
	}

	// takes user defined password and encrypted text and decrypts
	public static String decrypt(String s, String word){

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

	// takes string and int and calcs frequency
	public static LinkedHashMap<String, Double> freq(String x,int size, LinkedHashMap<String, Double> freq) throws IOException{


		//LinkedHashMap<String, Double> freq =  new LinkedHashMap<String, Double>();
		count = 0;
		x = x.toLowerCase().replaceAll("[^a-z]+", "");
		switch (size){
		case 1:
			for(int i = 0; i < x.length()-1; i++){
				String c = x.substring(i, i+1);
				Double val = (Double) freq.get(new String(c));
				if(val != null){
					freq.put(c, new Double(val + 1));
					count++;
				}else{freq.put(c,1.0);}
			}
			break;
		case 2:
			for(int i = 0; i < x.length()-2; i++){
				String c = x.substring(i, i+2);
				Double val = (Double) freq.get(new String(c));
				if(val != null){
					freq.put(c, new Double(val + 1));
					count++;
				}else{freq.put(c,1.0);}
			}
			break;
		case 3:
			for(int i = 0; i < x.length()-3; i++){
				String c = x.substring(i, i+3);
				Double val = (Double) freq.get(new String(c));
				if(val != null){
					freq.put(c, new Double(val + 1));
					count++;
				}else{freq.put(c,1.0);}
			}
			break;
		}			

		//sortMap(freq);
		return freq;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashMap<String, Double> sortMap(LinkedHashMap freq){
		// take map in and return sorted highest value
		List list = new LinkedList(freq.entrySet());

		Collections.sort(list, new Comparator<Object>() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		LinkedHashMap sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry<String,Double> entry = (Entry<String, Double>) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	public static void printMap(LinkedHashMap<String, Double> map, boolean trim){

		Iterator<Map.Entry<String, Double>> a = map.entrySet().iterator(); 
		DecimalFormat dp = new DecimalFormat("#.##");
		if(!trim){
			while(a.hasNext()){
				String key = a.next().getKey().toString();
				double percent = ((((double)map.get(key)/count)*100));
				
				map.put(key, percent);
				System.out.println(key + " = " + dp.format(map.get(key)) +"%");
			}
		}else{
			while(a.hasNext()){
				String key = a.next().getKey().toString();
				double percent = ((((double)map.get(key)/count)*100));
				//DecimalFormat dp = new DecimalFormat("#.##");
				if( (double)map.get(key) > count*0.01){
					map.put(key, percent);
					System.out.println(key + " = " + dp.format(map.get(key)) +"%");
				}else{
					a.remove();	
				}
			}
		}
		System.out.println("letters counted = " + count);
		System.out.println();
	}

	public static String getMax(LinkedHashMap<String, Double> freq) throws IOException{

		String maximum ="";
		Entry<String,Double> max = null;
		//not quite right
		HashMap<String, Double> getMaxi = new HashMap<String,Double>();
		Map<String, Double> temp = new HashMap<String, Double>(freq);
		temp.keySet().removeAll(getMaxi.keySet());
		getMaxi.putAll(temp);

		for(Entry<String, Double> e : getMaxi.entrySet()) {
			if (max == null || e.getValue() > max.getValue()) {
				max = e;
			}
		}
		maximum = max.getKey(); 
		return maximum;

	}

	public static int getKeyLength(String maximum, String s) throws IOException{

		//String s = new Scanner(new File("file.txt")).useDelimiter("\\Z").next();

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

	public static String breakText(String s, int count, String key) throws IOException{
		
		char[] stuff = s.toCharArray();  
		String a1 = "";
		char m = 0;
		int counter = count;
		KEY = key;
		
		LinkedHashMap<String, Double> a =  new LinkedHashMap<String, Double>();
		
		if (counter < keyLength){
			for (int i = counter; i< stuff.length - keyLength; i += keyLength) {
				
					a1 += stuff[i];
					
				}
			counter++;
			freq(a1,1,a); 
			m = getMax(a).charAt(0);
			int f = m - 101 +97;
			KEY += (char)f;
			breakText(s, counter, KEY);
		}
		return KEY;
	}

}
