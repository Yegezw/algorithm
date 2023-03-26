package vs.stack;

import port.Stack;
import stage1.week3.stack.ArrayStack;
import stage1.week4.stack.LinkedListStack;

import java.util.Random;

public class ArrayStackVSLinkedListStack {

    /**
     * 测试使用 stack 来运行 opCount 个 push 和 pop 所需要的时间, 单位: s
     */
    public static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        // int opCount = 10000000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");

        System.out.println("在 opCount 少时, ArrayStack 扩容频繁, 因此 LinkedListStack 更占优势");
        System.out.println("在 opCount 多时, ArrayStack 扩容不再频繁, 而 LinkedListStack 却要不停的 new, 因此 ArrayStack 更占优势");
    }
}
