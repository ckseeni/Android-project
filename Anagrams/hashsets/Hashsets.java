import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
public class Hashsets{
	public static void main(String args[]) throws Exception{
		File file = new File("words.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(filereader);
		HashSet arry = new HashSet();
		String line;
        while((line = buffer.readLine()) != null) {
            String word = line.trim();
			arry.add(word);
        }
		String givenToUser = "post";
		char[] giv = givenToUser.toCharArray();
		Arrays.sort(giv);
		String sortedGivString = new String(giv);
		Scanner scan = new Scanner(System.in);
		while(true){
		System.out.println("Enter anagram:");
		String input = scan.nextLine();
		if (arry.contains(input)){
			char[] inp = input.toCharArray();
			Arrays.sort(inp);
			String userInput = new String(inp);
			if(sortedGivString.equals(userInput)){
				System.out.println("Correct");
			}
			else{
				System.out.println("A valid word..But not an anagram");
			}
		}
		else{
			System.out.println("The entered word is not a valid word!!");
		}
		}
	}
}
