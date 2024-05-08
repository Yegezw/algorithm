package stage2.week6.quicksort;

import other.helper.ArrayGenerator;
import other.helper.SortName;
import other.helper.SortingHelper;

public class QuickSortVS
{

    private static void vs()
    {
        int       n = 5000000;
        Integer[] arr1, arr2, arr3;

        System.out.println("RandomArray");
        arr1 = ArrayGenerator.generateRandomArray(n, n);
        arr2 = ArrayGenerator.generateRandomArray(n, n);
        arr3 = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest(SortName.QuickSort1, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
        SortingHelper.sortTest(SortName.QuickSort3, arr3);

        System.out.println("OrderedArray");
        arr1 = ArrayGenerator.generateOrderedArray(n);
        arr2 = ArrayGenerator.generateOrderedArray(n);
        arr3 = ArrayGenerator.generateOrderedArray(n);
        SortingHelper.sortTest(SortName.QuickSort1, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
        SortingHelper.sortTest(SortName.QuickSort3, arr3);

        System.out.println("EqualsArray");
        // arr1 = ArrayGenerator.generateRandomArray(n, 0);
        arr2 = ArrayGenerator.generateRandomArray(n, 0);
        arr3 = ArrayGenerator.generateRandomArray(n, 0);
        // SortingHelper.sortTest(SortName.QuickSort1, arr1);
        SortingHelper.sortTest(SortName.QuickSort2, arr2);
        SortingHelper.sortTest(SortName.QuickSort3, arr3);
    }

    public static void main(String[] args)
    {
        vs();
    }
}
