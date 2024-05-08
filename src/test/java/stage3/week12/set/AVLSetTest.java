package stage3.week12.set;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class AVLSetTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testAVLSet()
    {
        long startTime = System.nanoTime();

        AVLSet<String> avlSet = new AVLSet<>();
        for (String word : words) avlSet.add(word);

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + avlSet.getSize());
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLSet: " + time + " s");
    }

    public static void main(String[] args)
    {
        testAVLSet();
    }
}
