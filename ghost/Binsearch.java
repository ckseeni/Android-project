import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
public class Binsearch{
	public static void main(String args[]) throws Exception{
		File file = new File("words.txt");			
		FileReader filereader = new FileReader(file);
		BufferedReader bufferedreader = new BufferedReader(filereader);
		ArrayList arr = new ArrayList();
		String line;
		while((line=bufferedreader.readLine())!=null){
			String word = line.trim();
			arr.add(word);
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the word: ");
		String usrWord = in.nextLine(); 
		Integer min=0;
		Integer flag;
		Integer max=arr.size()-1;
		flag=binsearch(arr,usrWord,min,max);
		if(flag==-1)
			System.out.println("Negative");
		else
			System.out.println("Positive");
	}
	public static int binsearch(ArrayList arr, String usrWord, Integer min, Integer max){
		if(min>max)
			return -1;
		Integer mid = (max+min)/2;
		String target =  (String)arr.get(mid);
		if(target.equals(usrWord))
			return 1;
		else if(target.compareTo(usrWord)>0)
			return binsearch(arr,usrWord,min,mid-1);
		else
			return binsearch(arr,usrWord,mid+1,max);
	}
}
