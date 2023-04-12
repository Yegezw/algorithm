package stage3.week12.map;

import other.util.Novel;

import java.util.ArrayList;

/**
 * word: 傲慢与偏见
 */
public class RBMapTest {

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void testRBMap() {
        long startTime = System.nanoTime();

        RBMap<String, Integer> rbMap = new RBMap<>();
        for (String word : words) {
            if (rbMap.contains(word)) rbMap.set(word, rbMap.get(word) + 1);
            else rbMap.add(word, 1);
        }

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + rbMap.getSize());
        System.out.println("Frequency of Pride: " + rbMap.get("pride"));
        System.out.println("Frequency of Prejudice: " + rbMap.get("prejudice"));
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBMap: " + time + " s");
    }

    public static void main(String[] args) {
        testRBMap();
    }
}
