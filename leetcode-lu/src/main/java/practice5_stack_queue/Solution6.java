package practice5_stack_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/sliding-window-maximum/">239 - 滑动窗口最大值</a>
 */
@SuppressWarnings("all")
public class Solution6 {

    // 大到小(存的是索引): 窗口缩小时, 哪些值会依次成为窗口内的最大值
    // 1, 3, -1, -3, 5, 3, 6, 7
    // [0 ... 0] 1
    // [0 ... 1] 3
    // [0 ... 2] 3, -1
    // [0 ... 3] 3, -1, -3
    // [0 ... 4] 5
    // [0 ... 5] 5, 3
    // [0 ... 6] 6
    // [0 ... 7] 7
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;
        if (k >= nums.length) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) max = Math.max(max, nums[i]);
            return new int[]{max};
        }

        int[] res = new int[nums.length - k + 1];
        int index = 0;

        // 从大到小的单调队列, 存储的是索引
        // 窗口缩小时, 队首的值会依次成为窗口内的最大值
        // 新元素 cur 入队时, 队尾中值 <= cur 值需要依次弹出
        Deque<Integer> deque = new LinkedList<>();
        for (int r = 0; r < nums.length; r++) {
            int cur = nums[r];
            while (!deque.isEmpty() && nums[deque.getLast()] <= cur) deque.removeLast();
            deque.addLast(r);

            if (deque.getFirst() < r - k + 1) deque.removeFirst();

            if (r >= k - 1) res[index++] = nums[deque.getFirst()];
        }

        return res;
    }
}
