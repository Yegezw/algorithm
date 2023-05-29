package chapter5.section4;

import chapter5.ListNode;

/**
 * <a href="https://leetcode.cn/problems/insertion-sort-list/">147 - 对链表进行插入排序</a>
 */
public class InsertionSortList {

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int k = arr[i];
            int j;
            for (j = i; j - 1 >= 0 && arr[j - 1] > k; j--) arr[j] = arr[j - 1];
            arr[j] = k;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int k = arr[a];
        arr[a] = arr[b];
        arr[b] = k;
    }

    public static ListNode insertionSortList(ListNode head) {
        
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 3, 7, 6, 5, 2, 8, 4};
        ListNode head = new ListNode(arr);

        ListNode node = insertionSortList(head);
        System.out.println(node);
    }
}
