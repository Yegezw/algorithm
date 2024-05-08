package stage3.week11.segmentTree;

public class SegmentTreeTest
{

    private static void test()
    {
        Integer[]            nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> tree = new SegmentTree<>(nums, Integer::sum);
        // System.out.println(tree);

        System.out.println(tree.query(0, 2)); // 1

        tree.set(1, 2);

        System.out.println(tree.query(0, 2)); // 3
        System.out.println(tree.query(3, 5)); // -4
        System.out.println(tree.query(0, 5)); // -1
    }

    public static void main(String[] args)
    {
        test();
    }
}
