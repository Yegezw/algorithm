package problem.week11;

import java.util.TreeMap;

/**
 * <a href="https://leetcode-cn.com/problems/map-sum-pairs/">677 - 键值映射</a>
 */
@SuppressWarnings("all")
public class MapSum {

    private static class Node {
        public int val;
        public TreeMap<Character, Node> next;

        public Node(int val) {
            this.val = val;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private final Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String word, int val) {
        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) return 0;
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node) {
        int sum = node.val;
        if (node.next.isEmpty()) return sum;

        for (Node nextNode : node.next.values()) sum += sum(nextNode);
        return sum;
    }
}
