package practice5_stack_queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string/description/">1047 - 删除字符串中的所有相邻重复项</a>
 */
public class Solution4 {

    public String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();
        char[] arr = s.toCharArray();

        for (char c : arr) {
            if (deque.isEmpty()) deque.addLast(c);
            else {
                if (c != deque.getLast()) deque.addLast(c);
                else deque.removeLast();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) sb.append(deque.removeFirst());
        return sb.toString();
    }
}
