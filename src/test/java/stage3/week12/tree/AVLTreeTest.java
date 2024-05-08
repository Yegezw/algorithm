package stage3.week12.tree;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class AVLTreeTest
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testAVLTree()
    {
        long startTime = System.nanoTime();

        AVLTree<String, Integer> avlTree = new AVLTree<>();
        for (String word : words)
        {
            if (avlTree.contains(word)) avlTree.set(word, avlTree.get(word) + 1);
            else avlTree.add(word, 1);
        }

        long   endTime = System.nanoTime();
        double time    = (endTime - startTime) / 1000000000.0;
        System.out.println("Total different words: " + avlTree.getSize());
        System.out.println("Frequency of Pride: " + avlTree.get("pride"));
        System.out.println("Frequency of Prejudice: " + avlTree.get("prejudice"));
        System.out.println("AVLTree: " + time + " s");

        for (String word : words)
        {
            avlTree.remove(word);
            if (!avlTree.isBST() || !avlTree.isBalanced()) throw new RuntimeException("Error");
        }

        System.out.println("is BST: " + avlTree.isBST());
        System.out.println("is Balanced: " + avlTree.isBalanced());
    }

    public static void main(String[] args)
    {
        testAVLTree();
    }
}
