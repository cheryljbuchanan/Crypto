import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class frequencyCW {

	static double count;
	public static void main(String [] args) throws IOException{

		LinkedHashMap<String, Double> mapFreq =  new LinkedHashMap<String, Double>();
		LinkedHashMap<String, Double> mapCrypt =  new LinkedHashMap<String, Double>();
		BufferedReader file = new BufferedReader(new FileReader("text.txt"));
		BufferedReader b = new BufferedReader(new FileReader("crypttxt.txt"));
		Scanner s = new Scanner(new FileReader("crypttxt.txt"));

		calcFreq(file,mapFreq);
		LinkedHashMap<String, Double> sortedMapFreq = sortMap(mapFreq);
		System.out.println("plain text frequency analysis :");
		printMap(sortedMapFreq);

		calcFreq(b,mapCrypt);
		LinkedHashMap<String, Double> sortedMapCrypt = sortMap(mapCrypt);
		System.out.println("Cipher text frequency analysis :");
		printMap(sortedMapCrypt);

		
		test(sortedMapFreq, sortedMapCrypt, s);
		decrypt();
	}

	public static LinkedHashMap calcFreq(BufferedReader file, LinkedHashMap map) throws IOException{
		try{
			StringBuilder s = new StringBuilder();
			String line = file.readLine();

			while (line != null){
				s.append(line);
				s.append('\n');

				line = file.readLine();
			}
			count = 0;
			String x = s.toString().toLowerCase().replaceAll("[^a-z]+", "");

			for(int i = 0; i < x.length()-1; i++){
				String c = x.substring(i, i+1);
				Double val = (Double) map.get(new String(c));
				if(val != null){
					map.put(c, new Double(val + 1));
					count++;
				}else{
					map.put(c,1.0);
				}
			}		
		}finally{file.close();}
		return map;
	}

	public static void printMap(LinkedHashMap map){

		Iterator<Map.Entry<String, Double>> a = map.entrySet().iterator(); 
		while(a.hasNext()){
			String key = a.next().getKey().toString();
			double percent = ((((double)map.get(key)/count)*100));
			DecimalFormat dp = new DecimalFormat("#.##");
			map.put(key, percent);
			System.out.println(key + " = " + dp.format(map.get(key)) +"%");
		}
		System.out.println("letters counted = " + count);
		System.out.println();
	}

	public static LinkedHashMap sortMap(LinkedHashMap map){
		// take map in and return sorted highest value
		List list = new LinkedList(map.entrySet());

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

	public static void test(LinkedHashMap mapFreq, LinkedHashMap mapCrypt, Scanner s){

		//char[] replaceWith = {'e','t','a','o','i','n','s','h','r','d','l','c','u','m','w','f','g','y','p','b','v','k','j','x','q','z'};
		//char[] toReplace = {'i','x','e','w','m','r','v','s','l','p','h','g','y','q','a','t','k','c','j','f','z','o','b','u','n','d'};
		char[] replaceWith = mapFreq.keySet().toString().toCharArray();
		char[] toReplace = mapCrypt.keySet().toString().toCharArray();
		
		//System.out.println("Test Text : ");

		s.useDelimiter(System.getProperty("line.separator"));
		while(s.hasNext()){
			String line = s.next().toLowerCase();
			//String line = "xli evx erh wgmirgi sj oiitmrk mrjsvqexmsr wigyvi";
			for(int i = 0; i < line.length(); i++){	
				int n = (int)line.charAt(i);
				if(!(n >= 97 && n <= 122)){
					System.out.print(line.charAt(i));
				}else {
					for(int j = 0; j < toReplace.length; j++){
						if (line.charAt(i)== toReplace[j]){
							System.out.print(replaceWith[j]);
							break;
						}
					}
				}
			}
			System.out.println();
		}
	}

	public static void decrypt() throws IOException{

		Scanner s = null;
		System.out.println();
		System.out.println("Decrypted Text : ");
		
		try {
			s = new Scanner(new File("crypttxt.txt"));
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		s.useDelimiter(System.getProperty("line.separator"));
		while(s.hasNext()){
			String line = s.next().toLowerCase();
			for(int i = 0; i < line.length(); i++){
				int j = (int)line.charAt(i);
				//not alpahbet
				if(!(j >= 97 && j <= 122)){
					System.out.print(line.charAt(i));
				}else if ( j <= (99)){
					int a = j+22;
					char b = (char)a;
					System.out.print(b);
				}else{
					int a = j-4;
					char b = (char)a;
					System.out.print(b);
				}
			}
			System.out.println();
		}
	}
}
