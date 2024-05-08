package stage2.week8.work;

import port.Queue;
import port.Stack;
import stage1.week3.queue.LoopQueue;
import stage1.week3.stack.ArrayStack;

/**
 * Binary Search Tree Not Recursion
 */
@SuppressWarnings("all")
public class BSTNR<E extends Comparable<E>>
{

    private class Node
    {
        public E    e;
        public Node left;
        public Node right;

        public Node(E e)
        {
            this.e     = e;
            this.left  = null;
            this.right = null;
        }
    }

    private Node root;
    private int  size;

    public BSTNR()
    {
        root = null;
        size = 0;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void add(E e)
    {
        if (root == null)
        {
            root = new Node(e);
            size++;
            return;
        }

        Node parent = root;
        while (true)
        {
            if (e.compareTo(parent.e) == 0) return;

            if (e.compareTo(parent.e) < 0)
            {
                if (parent.left == null)
                {
                    parent.left = new Node(e);
                    size++;
                    return;
                }
                parent = parent.left;
            }

            if (e.compareTo(parent.e) > 0)
            {
                if (parent.right == null)
                {
                    parent.right = new Node(e);
                    size++;
                    return;
                }
                parent = parent.right;
            }
        }
    }

    public boolean contains(E e)
    {
        Node cur = root;
        while (cur != null)
        {
            if (e.compareTo(cur.e) == 0) return true;
            if (e.compareTo(cur.e) < 0) cur = cur.left;
            else cur = cur.right;
        }
        return false;
    }

    public void preOrder()
    {
        Stack<Node> stack = new ArrayStack<>();
        stack.push(root);

        Node cur;
        while (!stack.isEmpty())
        {
            cur = stack.pop();
            System.out.println(cur.e);
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }

    public void levelOrder()
    {
        Queue<Node> queue = new LoopQueue<>();
        queue.enqueue(root);

        Node cur;
        while (!queue.isEmpty())
        {
            cur = queue.dequeue();
            System.out.println(cur.e);
            if (cur.left != null) queue.enqueue(cur.left);
            if (cur.right != null) queue.enqueue(cur.right);
        }
    }
}
