package vs.set;

import other.util.Novel;
import port.Set;
import stage2.week8.set.BSTSet;
import stage2.week8.set.LinkedListSet;
import stage3.week12.set.AVLSet;
import stage3.week12.set.RBSet;

import java.util.ArrayList;
import java.util.Collections;

/**
 * word: 傲慢与偏见
 */
public class SetVS {

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void vs(Set<String> set) {
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
        LinkedListSet<String> set1 = new LinkedListSet<>();
        BSTSet<String> set2 = new BSTSet<>();
        AVLSet<String> set3 = new AVLSet<>();
        RBSet<String> set4 = new RBSet<>();

        Collections.sort(words); // 顺序添加单词后, BSTSet 将会退化为链表, 而 AVLSet 和 RBSet 不会

        vs(set1);
        System.out.println("------------------");
        vs(set2);
        System.out.println("------------------");
        vs(set3);
        System.out.println("------------------");
        vs(set4);
    }
}
