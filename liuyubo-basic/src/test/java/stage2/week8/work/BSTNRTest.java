package stage2.week8.work;

public class BSTNRTest {

    private static void test() {
        int[] arr = {5, 3, 6, 8, 4, 2};

        BSTNR<Integer> bstNR = new BSTNR<>();
        for (int i : arr) bstNR.add(i);
        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////

        bstNR.preOrder();   // 5 3 2 4 6 8
        bstNR.levelOrder(); // 5 3 6 2 4 8
        System.out.println(bstNR.contains(8));
        System.out.println(bstNR.contains(10));
    }

    public static void main(String[] args) {
        test();
    }
}
