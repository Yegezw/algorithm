package vs.tree;

import stage2.week8.map.BSTMap;
import stage3.week12.tree.AVLTree;
import stage3.week12.tree.RBTree;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("all")
public class TreeVS {

    private static void testBSTMap(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        BSTMap<Integer, Integer> bstMap = new BSTMap<>();
        for (Integer data : testData) bstMap.add(data, null);

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BSTMap: " + time + " s");
    }

    private static void testAVLTree(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (Integer data : testData) avlTree.add(data, null);

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLTree: " + time + " s");
    }

    private static void testRBTree(ArrayList<Integer> testData) {
        long startTime = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer data : testData) rbTree.add(data, null);

        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time + " s");
    }

    /**
     * 测试随机数据
     */
    public static void testRandomData() {
        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) testData.add(random.nextInt(Integer.MAX_VALUE));

        testBSTMap(testData);
        testAVLTree(testData);
        testRBTree(testData);
    }

    /**
     * 测试有序数据
     */
    public static void testOrderedData() {
        int n = 20000000;

        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) testData.add(i);

        testAVLTree(testData);
        testRBTree(testData);
    }

    public static void main(String[] args) {
        testRandomData();

        testOrderedData();
    }
}
