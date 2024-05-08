package stage3.week12.tree;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class RBTreeTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testRBTree()
    {
        long startTime = System.nanoTime();

        RBTree<String, Integer> rbTree = new RBTree<>();
        for (String word : words)
        {
            if (rbTree.contains(word)) rbTree.set(word, rbTree.get(word) + 1);
            else rbTree.add(word, 1);
        }
        for (String word : words) rbTree.contains(word);

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + rbTree.getSize());
        System.out.println("Frequency of Pride: " + rbTree.get("pride"));
        System.out.println("Frequency of Prejudice: " + rbTree.get("prejudice"));
        System.out.println("RBTree: " + time + " s");
    }

    public static void main(String[] args)
    {
        testRBTree();
    }
}
