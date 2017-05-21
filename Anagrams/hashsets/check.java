import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashSet;
public class check{
	public static void main(String args[]) throws Exception{
		File file = new File("words.txt");
		FileReader filereader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(filereader);
		HashSet<String> arry = new HashSet<String>();
		String line;
        while((line = buffer.readLine()) != null) {
            String word = line.trim();
			arry.add(word);
        }
		Integer i=0;
		/*while(i<arry.size()){
		String lin=new String(arry.get(i));
		System.out.println(lin);
		i=i+1;*/
		for (String p:arry){
			System.out.println(p);
			i=i+1;
		}
		System.out.println(i);
	}
}
