package com.google.engedu.ghost;

import java.util.HashMap;
import java.util.Random;


public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }	

    public void add(String s) {

        for (int i=0;i<s.length();i++){
            TrieNode trieNode;
            char c = s.charAt(i);
            if(children.containsKey(c)){
                trieNode = children.get(c);
            }
            else{
                trieNode = new TrieNode();
                children.put(c,trieNode);
            }
            children = trieNode.children;
            if(i==s.length()-1)
                trieNode.isWord=true;
        }
    }

    public boolean isWord(String s) {
        TrieNode trieNode = new TrieNode();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(children.containsKey(c)) {
                trieNode = children.get(c);
                children = trieNode.children;
            }
            else
                break;
        }
        return trieNode.isWord;
    }

    public String getAnyWordStartingWith(String s) {
        TrieNode trieNode = new TrieNode();
        Random rand = new Random();
        String re= s;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(children.containsKey(c)){
                trieNode = children.get(c);
                children = trieNode.children;
            }
            else
                return null;
        }

        while(!(trieNode.isWord)) {
            char ch = (char) (rand.nextInt(26) + 97);
            while (!children.containsKey(ch)) {
                ch = (char) (rand.nextInt(26) + 97);
            }
            String string = Character.toString(ch);
            re = s+string;
            trieNode = children.get(ch);
            children = trieNode.children;
        }
        return  re;
    }

    public String getGoodWordStartingWith(String s) {
        return null;
    }
}
