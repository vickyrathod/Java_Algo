package org.example.tries.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TrieOperation {

    public static void addStringToTrie(String str, Trie t) {
        // For loop will be more readable
        int i = 0;
        while(i < str.length()) {
            Character ch = new Character(str.charAt(i));
            Optional<Trie> temp = t.getNode(ch);

            if(temp.isPresent()) {
                if(temp.get().getCompress() != null) {
                    String nStr = temp.get().getCompress();
                    temp.get().setCompress(null);
                    temp.get().setEnd(false);
                    addStringToTrie(nStr, temp.get());
                }
                t = temp.get();
                i ++;
                continue;
            }
            t.addNode(ch, new Trie(str.substring(i + 1)));
            return;
        }
        t.setEnd(true);
    }

    public static void getUniquePrifix(Trie trie, Map<String, String> ans, String stringTill) {
        if(trie.isEnd()) {
            ans.put(stringTill + trie.compress, stringTill.toString());
        }

        trie.children.forEach((ch, tr)-> {
            if(tr == null)
                return;
            getUniquePrifix(tr, ans, stringTill + ch);
        });
    }
}
