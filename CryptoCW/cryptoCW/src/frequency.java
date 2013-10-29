import java.io.*;
import java.util.*;
import java.util.Map.Entry;

// sort this into methods etc

//create 2d array, get frequency of both files and put in order side by side.
//use buffered reader again to change letters over and then output to file.

public class frequency {

	//private static int count = 0;
	private static String x ="";

	public static void main(String args[]) throws IOException{

		TreeMap<String,Double> map = new TreeMap<String,Double>();
		TreeMap<String,Double> crpt = new TreeMap<String,Double>();

		BufferedReader b = new BufferedReader(new FileReader("text.txt"));
		BufferedReader a = new BufferedReader(new FileReader("crypttxt.txt"));

		//Scanner scan = new Scanner(new FileReader("crypttxt.txt"));

		checkFrequency(b,map);
		//checkFrequency(a,crpt);

		//sortHashMapByValuesD(map);
		//sortHashMapByValuesD(crpt);

		/*printMap(map);
		printMap(crpt);*/


	}
	public static TreeMap<String, Double> checkFrequency(BufferedReader b, TreeMap<String, Double> map) throws IOException{
		try{
			StringBuilder s = new StringBuilder();
			String line = b.readLine();

			while (line != null){
				s.append(line);
				s.append('\n');

				line = b.readLine();
			}

			x = s.toString().toLowerCase().replaceAll("[^a-z]+", "");

			/*	for( int i = 0; i < x.length(); i++){
				count += 1;
				String c = x.valueOf(i);
				Double val = (Double) m.get(new String(c));
				if(val != null){
					m.put(c, new Double(val + 1));
				}else{
					m.put(c,1.0);
				}
			}*/
			

			for(int i = 0; i < x.length()-3; i++){
				String c = x.substring(i, i+3);
				Double val = (Double) map.get(new String(c));
				if(val != null){
					map.put(c, new Double(val + 1));
				}else{
					map.put(c,1.0);
				}
			}
			Iterator<Map.Entry<String,Double>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
			    Map.Entry<String,Double> entry = iter.next();
			    if(entry.getValue() <= 2000){
			    	System.out.println("test");
			        iter.remove();
			        
			    }
			}
			System.out.println(map);
			return map;
		}finally{b.close();}
	}

	public static TreeMap<String, Double> replace(TreeMap map){
		return map;

	}
}

