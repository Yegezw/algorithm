package stage2.week8.bst;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class BSTTest
{

    @Test
    void testBST1()
    {
        int[] arr = {5, 3, 6, 8, 4, 2};

        BST<Integer> bst = new BST<>();
        for (int i : arr) bst.add(i);
        System.out.println(bst);
        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////

        bst.preOrder();   // 5 3 2 4 6 8
        bst.inOrder();    // 2 3 4 5 6 8
        bst.postOrder();  // 2 4 3 8 6 5
        bst.levelOrder(); // 5 3 6 2 4 8

        bst.remove(3);
        bst.levelOrder(); // 5 4 6 2 8
    }

    @Test
    void testBST2()
    {
        BST<Integer> bst    = new BST<>();
        Random       random = new Random();
        testRemoveMin(bst, random);
        testRemoveMax(bst, random);
    }

    @Test
    void testBST3()
    {
        BST<Integer> bst = new BST<>();
        for (int i = 1; i < 10; i += 2) bst.add(i); // 1 3 5 7 9
        System.out.println("1 3 5 7 9");

        System.out.print("floor : ");
        for (int i = 0; i <= 10; i++) System.out.print(bst.floor(i) + " ");
        System.out.println();

        System.out.print("ceil  : ");
        for (int i = 0; i <= 10; i++) System.out.print(bst.ceil(i) + " ");
        System.out.println();
    }

    void testRemoveMin(BST<Integer> bst, Random random)
    {
        int n = 1000;
        for (int i = 0; i < n; i++) bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) nums.add(bst.removeMin()); // nums[] 应该是升序的
        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++)
        {
            if (nums.get(i - 1) > nums.get(i))
            {
                throw new IllegalArgumentException("removeMin Error");
            }
        }
        System.out.println("removeMin test completed");
    }

    void testRemoveMax(BST<Integer> bst, Random random)
    {
        int n = 1000;
        for (int i = 0; i < n; i++) bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) nums.add(bst.removeMax()); // nums[] 应该是降序的
        System.out.println(nums);

        for (int i = 1; i < nums.size(); i++)
        {
            if (nums.get(i - 1) < nums.get(i))
            {
                throw new IllegalArgumentException("removeMax Error");
            }
        }
        System.out.println("removeMax test completed");
    }
}
