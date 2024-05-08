package stage3.week12.set;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class RBSetTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testRBSet()
    {
        long startTime = System.nanoTime();

        RBSet<String> rbSet = new RBSet<>();
        for (String word : words) rbSet.add(word);

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + rbSet.getSize());
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBSet: " + time + " s");
    }

    public static void main(String[] args)
    {
        testRBSet();
    }
}
