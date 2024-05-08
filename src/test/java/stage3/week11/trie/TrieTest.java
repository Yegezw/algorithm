package stage3.week11.trie;

import other.util.Novel;
import stage2.week8.set.BSTSet;

import java.util.ArrayList;

public class TrieTest
{

    private static final ArrayList<String> words = Novel.words2List; // 双城记

    public static void testBSTSet()
    {
        BSTSet<String> set       = new BSTSet<>();
        long           startTime = System.nanoTime();

        for (String word : words) set.add(word);
        for (String word : words) set.contains(word);

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + set.getSize());
        System.out.println("BSTSet: " + time);
    }

    public static void testTrie()
    {
        Trie trie      = new Trie();
        long startTime = System.nanoTime();

        for (String word : words) trie.add(word);
        for (String word : words) trie.contains(word);

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + trie.getSize());
        System.out.println("Trie: " + time);
    }

    public static void main(String[] args)
    {
        System.out.println("A Tale of Two Cities");
        System.out.println();

        testBSTSet();
        System.out.println();
        testTrie();
    }
}
