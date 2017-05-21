package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;
    public Random rand = new Random();
    public Integer randint;
    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix==""){
            randint = rand.nextInt(words.size());
            return (words.get(randint));
        }
        else{
            int min,max,mid;
            min=0;
            max=words.size();
            while(min<=max){
                mid=(min+max)/2;
                String target = words.get(mid);
                if(target.contains(prefix))
                    return target;
                else if(target.compareTo(prefix)>0)
                    max=mid-1;
                else
                    min=mid+1;
            }
            return null;
        }
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}
