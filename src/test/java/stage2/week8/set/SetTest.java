package stage2.week8.set;

import other.util.Novel;
import port.Set;

import java.util.ArrayList;

/**
 * <p>word1: 傲慢与偏见
 * <p>word2: 双城记
 */
@SuppressWarnings("all")
public class SetTest {

    private static final ArrayList<String> words1 = Novel.words1List; // 傲慢与偏见
    private static final ArrayList<String> words2 = Novel.words2List; // 双城记

    public static void testBSTSet() {
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words1.size());
        Set<String> set1 = new BSTSet<>();
        for (String word : words1) set1.add(word);
        System.out.println("Total different words: " + set1.getSize());

        System.out.println();

        System.out.println("A tale of two cities");
        System.out.println("Total words: " + words2.size());
        Set<String> set2 = new BSTSet<>();
        for (String word : words2) set2.add(word);
        System.out.println("Total different words: " + set2.getSize());
    }

    public static void testLinkedListSet() {
        System.out.println("Pride and Prejudice");
        System.out.println("Total words: " + words1.size());
        Set<String> set1 = new LinkedListSet<>();
        for (String word : words1) set1.add(word);
        System.out.println("Total different words: " + set1.getSize());

        System.out.println();

        System.out.println("A tale of two cities");
        System.out.println("Total words: " + words2.size());
        Set<String> set2 = new LinkedListSet<>();
        for (String word : words2) set2.add(word);
        System.out.println("Total different words: " + set2.getSize());
    }

    public static void vs(Set<String> set, ArrayList<String> words) {
        long startTime = System.nanoTime();

        for (String word : words) set.add(word);

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + set.getSize());
        double time = (endTime - startTime) / 1000000000.0;
        String simpleName = set.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    public static void main(String[] args) {
        // testBSTSet();

        // testLinkedListSet();

        BSTSet<String> set1 = new BSTSet<>();
        LinkedListSet<String> set2 = new LinkedListSet<>();
        vs(set1, words2);
        System.out.println("-------------------");
        vs(set2, words2);
    }
}
