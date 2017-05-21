import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Scanner;

public class check{
	public static void main(String args[]) throws Exception{
		File file = new File("word.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(filereader);
		HashMap<String,ArrayList> hash = new HashMap<String,ArrayList>();
		String line;
        while((line = buffer.readLine()) != null) {
            String word = line.trim();
			char[] giv = word.toCharArray();
			Arrays.sort(giv);
			String key = new String(giv);
			if(hash.containsKey(key)){
				ArrayList arry = hash.get(key);
				arry.add(line);
				hash.put(key,arry);
			}
			else{
				ArrayList<String> newar = new ArrayList<String>();
				newar.add(line);
				hash.put(key,newar);
			}
        }
		System.out.println(hash);
	}
}
