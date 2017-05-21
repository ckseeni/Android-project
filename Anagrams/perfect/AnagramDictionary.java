package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
	public String userInput = new String();
	public ArrayList<String> arry = new ArrayList<String>();
	public ArrayList<String> result = new ArrayList<String>();
	public ArrayList<String> randarr = new ArrayList<String>();
	public HashMap<String,ArrayList> hash = new HashMap<String,ArrayList>();
    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
			randarr.add(word);
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
    }

    public boolean isGoodWord(String word, String base) {
		ArrayList<String> arr = new ArrayList<String>();
		String it = new String();
		userInput = new String(word);
		char[] giv = userInput.toCharArray();
		Arrays.sort(giv);
		String key = new String(giv);
		char[] giv1 = base.toCharArray();
		Arrays.sort(giv1);
		String key1 = new String(giv1);
		if(hash.containsKey(key)) {
			arr = hash.get(key1);
			if(arr.contains(word))
				return true;
			Integer i=0;
			while(i<arr.size()){
				it = arr.get(i);
				boolean be = word.contains(it);
				if(be) {
					result.add(word);
					return true;
				}
				i++;
			}
			return false;

		}	
		else 
			return false;
		
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {

		char[] giv = word.toCharArray();
		Arrays.sort(giv);
		String key = new String(giv);
		result = hash.get(key);
		result.remove(word);
		return result;
    }

    public String pickGoodStarterWord() {
		while(true) {
			ArrayList<String> some = new ArrayList<String>();
			String ra = randarr.get(random.nextInt(randarr.size()));
			char[] giv = ra.toCharArray();
			Arrays.sort(giv);
			String key = new String(giv);
			some = hash.get(key);
			if((some.size())>=5) {
				return ra;
			}
		}

    }
}
