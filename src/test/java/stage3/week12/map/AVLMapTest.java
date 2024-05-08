package stage3.week12.map;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class AVLMapTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testAVLMap()
    {
        long startTime = System.nanoTime();

        AVLMap<String, Integer> avlMap = new AVLMap<>();
        for (String word : words)
        {
            if (avlMap.contains(word)) avlMap.set(word, avlMap.get(word) + 1);
            else avlMap.add(word, 1);
        }

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + avlMap.getSize());
        System.out.println("Frequency of Pride: " + avlMap.get("pride"));
        System.out.println("Frequency of Prejudice: " + avlMap.get("prejudice"));
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLMap: " + time + " s");
    }

    public static void main(String[] args)
    {
        testAVLMap();
    }
}
