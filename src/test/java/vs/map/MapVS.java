package vs.map;

import other.util.Novel;
import port.Map;
import stage2.week8.map.BSTMap;
import stage2.week8.map.LinkedListMap;
import stage3.week12.map.AVLMap;
import stage3.week12.map.RBMap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * word: 傲慢与偏见
 */
public class MapVS
{

    private static final ArrayList<String> words = Novel.words1List; // 傲慢与偏见

    public static void vs(Map<String, Integer> map)
    {
        long startTime = System.nanoTime();

        for (String word : words)
        {
            if (map.contains(word)) map.set(word, map.get(word) + 1);
            else map.add(word, 1);
        }

        long endTime = System.nanoTime();
        System.out.println("Total words: " + words.size());
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of Pride: " + map.get("pride"));
        System.out.println("Frequency of Prejudice: " + map.get("prejudice"));
        double time       = (endTime - startTime) / 1000000000.0;
        String simpleName = map.getClass().getSimpleName();
        System.out.println(simpleName + ": " + time + " s");
    }

    public static void main(String[] args)
    {
        LinkedListMap<String, Integer> map1 = new LinkedListMap<>();
        BSTMap<String, Integer>        map2 = new BSTMap<>();
        AVLMap<String, Integer>        map3 = new AVLMap<>();
        RBMap<String, Integer>         map4 = new RBMap<>();

        Collections.sort(words); // 顺序添加单词后, BSTMap 将会退化为链表, 而 AVLMap 和 AVLMap 不会

        vs(map1);
        System.out.println("------------------");
        vs(map2);
        System.out.println("------------------");
        vs(map3);
        System.out.println("------------------");
        vs(map4);
    }
}
