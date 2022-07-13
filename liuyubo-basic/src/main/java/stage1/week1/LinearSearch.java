package stage1.week1;

/**
 * 线性查找法 O(n)
 */
@SuppressWarnings("all")
public class LinearSearch {

    private LinearSearch() {
    }

    public static <E> int search(E[] arr, E target) {
        for (int i = 0; i < arr.length; i++) {
            // arr[0, i) 没有找到目标
            // 如果 arr[i] 是目标, 就返回 i
            if (arr[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

}
