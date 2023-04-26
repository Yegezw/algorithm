package problem.week4;

import other.pojo.ListNode;

/**
 * <a href="https://leetcode-cn.com/problems/remove-linked-list-elements/">203 - 移除链表元素</a>
 */
@SuppressWarnings("all")
public class RemoveElements {

    /**
     * 不使用 dummyHead
     */
    public static ListNode removeElements1(ListNode head, int val) {
        // 1.先删除符合条件的头结点
        while (head != null && head.val == val) head = head.next;
        if (head == null) return null;

        // 2.再删除其他节点
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return head;
    }

    /**
     * 使用 dummyHead
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }

        return dummyHead.next;
    }

    /**
     * 递归解决问题
     */
    public static ListNode removeElements3(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * <p>递归逻辑的查看(递归调试)
     * <p>刚进函数, 先打印 depthString 和 本次调用意图(包含传入的参数)
     * <p>每次返回前打印 depthString 和 "Return: " + ret
     * <p>每次调用递归函数得到结果后, 打印 depthString 和 结果(包含传入的参数)
     */
    private static ListNode removeElements4(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        // 1.递归的终结点(规模最小)
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }

        // 2.调用递归解决部分问题(规模更小)
        ListNode res = removeElements4(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        // 3.用第二步的结果来求解答案(基本问题)
        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }

        // 4.返回答案
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    /**
     * 生成深度字符串
     */
    private static String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        sb.append(depth).append("-");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode h = removeElements4(head, 6, 0);
        System.out.println(h);
    }
}
