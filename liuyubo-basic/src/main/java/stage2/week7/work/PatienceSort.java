package stage2.week7.work;

import stage2.week5.MergeSortPlus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 耐心排序
 */
public class PatienceSort {

    private PatienceSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        // 所有 pile.last 升序, pile 自身降序
        List<LinkedList<E>> piles = new ArrayList<>();

        for (E e : arr) {
            // 从 piles[0 ... size - 1] 中查找 >= e 的最小值
            int l = 0;
            int r = piles.size();
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (piles.get(mid).getLast().compareTo(e) >= 0) r = mid;
                else l = mid + 1;
            }
            if (r == piles.size()) piles.add(new LinkedList<>());

            piles.get(r).addLast(e);
        }

        int i = 0;
        for (LinkedList<E> pile : piles) {
            while (!pile.isEmpty()) arr[i++] = pile.removeLast();
        }

        MergeSortPlus.sort(arr);
    }
}
