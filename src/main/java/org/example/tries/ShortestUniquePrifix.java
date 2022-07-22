package org.example.tries;

import org.example.tries.util.Trie;
import org.example.tries.util.TrieOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShortestUniquePrifix {

    public static ArrayList<String> prefix(ArrayList<String> A) {
        //create trie
        Trie trie = new Trie(false);

        for(String st : A) {
            TrieOperation.addStringToTrie(st, trie);
        }
        Map<String, String> ansMap = new HashMap<>();
        TrieOperation.getUniquePrifix(trie, ansMap, "");
        ArrayList<String> ans = new ArrayList<>();

        for(String st : A) {
            ans.add(ansMap.get(st));
        }

        return ans;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> input = new ArrayList<>();
        for(int i = 0; i < n; i ++)
            input.add(sc.next());
        prefix(input);
    }
}
