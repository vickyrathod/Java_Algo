package org.example.tries.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Trie {
    public Map<Character, Trie> children = new HashMap<>();
    String compress = null;

    // Decide word ending
    boolean end = false;

    public Trie(boolean end) {
        this.end = end;
    }

    // To compress trie
    public Trie(String suffix) {
        this.end = true;
        this.compress = suffix;
    }

    public Optional<Trie> getNode(Character ch){
        return Optional.ofNullable(children.getOrDefault(ch, null));
    }

    public void addNode(Character ch, Trie t){
        children.put(ch, t);
    }

    public String getCompress() {
        return compress;
    }

    public void setCompress(String compress) {
        this.compress = compress;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
