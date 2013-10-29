import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
//Author: Claire Douglas
//Date: Monday 8th October 2012
//Purpose: Count the letters in plaintext and decipher ciphertext
public class FrequencyAnalysis 
{
	public static void main(String [] args)
	{
		calculateLetterFrequency();
		decrypt();
	}
	public static void calculateLetterFrequency()
	{
		//Method to calculate the letter frequencies of a given text file which is read in using a Scanner
		int [] countLetters = new int[26];
		Scanner fileReader = null;
		try {
			fileReader = new Scanner(new File("text.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//int to count the total number of letters found
		int numberOfLetters = 0;
		while(fileReader.hasNext())
		{
			//Go through each word in the text file, using toLowerCase as we aren't interested in the case
			String word = fileReader.next().toLowerCase();
			for(int i = 0; i < word.length(); i++)
			{
				//For each letter in a word, get the ascii value
				int asciiValue = (int)word.charAt(i);
				//Check if the ascii value is equal to or between 97 (a) and 122 (z)
				if(asciiValue >= 97 && asciiValue <= 122)
				{
					//Increment the total number of letters by 1
					numberOfLetters++;
					//Add one to the relevant array position for the letter found
					countLetters[asciiValue - 97] = countLetters[asciiValue - 97] + 1;
				}
			}
		}
		//Print out each letter, it's frequency and percentage
		System.out.println("Frequency Analysis of the English text file:");
		for(int count = 0; count < countLetters.length; count++)
		{
			double percentage = (((double)countLetters[count]/(double)numberOfLetters)*100);
			DecimalFormat twoDecimalPlaces = new DecimalFormat("#.##");
			System.out.println("" + (char)(count + 97) + " " + countLetters[count] + " " + (twoDecimalPlaces.format(percentage)) + "%");
		}
		//Print out the total number of letters
		System.out.println("Total letters " + numberOfLetters);
	}
	public static void decrypt()
	{
		//Method to decrypt a given text file, read in using a Scanner
		System.out.println("\nDecryption of the ciphertext");
		//Array of the letters to be replaced in ciphertext
		char [] lettersToBeReplaced = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		//Array of letters that will be replaced in the ciphertext, to make it plaintext
		char [] letterToReplaceWith = {'w', 'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'};
		Scanner reader = null;
		try {
			reader = new Scanner(new File("crypttxt.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		reader.useDelimiter(System.getProperty("line.separator"));
		while(reader.hasNext())
		{
			//Look at each line of the text file, change it to lower case as we aren't interested in the different cases
			String line = reader.next().toLowerCase();
			for(int i = 0; i < line.length(); i++)
			{
				//Get each character in the line
				int n = (int)line.charAt(i);
				if(!(n >= 97 && n <= 122))
				{
					//If the character is not a lower case letter, just print out the character as it appeared in the ciphertext (punctuation and spaces)
					System.out.print(line.charAt(i));
				}else 
				{
					//If the character is a lower case letter, go through the lettersToBeReplaced array to identify which character was found
					for(int count = 0; count < lettersToBeReplaced.length; count++)
					{
						if(line.charAt(i) == lettersToBeReplaced[count])
						{
							//When the correct letter has been found, print out its corresponding letter from the array letterToReplaceWith
							System.out.print(letterToReplaceWith[count]);
							break;
						}
					}
				}
			}
			System.out.println();
		}
	}
}
