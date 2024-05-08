package stage3.week13.hash;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class HashTableTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testHashTable()
    {
        long startTime = System.nanoTime();

        HashTable<String, Integer> hashTable = new HashTable<>();
        for (String word : words)
        {
            if (hashTable.contains(word)) hashTable.set(word, hashTable.get(word) + 1);
            else hashTable.add(word, 1);
        }
        for (String word : words) hashTable.contains(word);

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + hashTable.getSize());
        System.out.println("Frequency of Pride: " + hashTable.get("pride"));
        System.out.println("Frequency of Prejudice: " + hashTable.get("prejudice"));
        System.out.println("HashTable: " + time + " s");
    }

    public static void main(String[] args)
    {
        testHashTable();
    }
}
