package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
	public ArrayList<String> arry = new ArrayList<String>();
	public String userInput = new String();
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
			arry.add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
		userInput = new String(word);
		if (arry.contains(word)){
        	return true;
		}
		else{
			return false;
		}
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
		char[] giv = word.toCharArray();
		Arrays.sort(giv);
		String sortedGivString = new String(giv);
		Integer i=0;
		while(i<arry.size()){
			String line=new String(arry.get(i));
			char[] inp = line.toCharArray();
			Arrays.sort(inp);
			String fromArry = new String(inp);
			if(sortedGivString.equals(fromArry)){
				result.add(line);
			}
			i=i+1;
		}
        return result;
    }

    public String pickGoodStarterWord() {
        return "post";
    }
}
