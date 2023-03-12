package stage1.week1;

/**
 * 线性查找法 O(n)
 */
@SuppressWarnings("all")
public class LinearSearch {

    private LinearSearch() {
    }

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            // 循环不变量: arr[0, i) 没有找到目标
            // 循环体维持循环不变量: 如果 data[i] 是目标, 就返回 i
            if (data[i].equals(target)) return i;
        }
        
        return -1;
    }
}
