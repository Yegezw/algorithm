package chapter8.section8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/n-queens/">51 - N 皇后</a>
 */
public class SolveNQueens {

    private static boolean[] col;  // 纵向
    private static boolean[] dia1; // 对角线撇
    private static boolean[] dia2; // 对角线捺
    private static ArrayList<List<String>> res;

    public static List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        col = new boolean[n];          // 纵向
        dia1 = new boolean[2 * n - 1]; // 对角线撇
        dia2 = new boolean[2 * n - 1]; // 对角线捺

        LinkedList<Integer> row = new LinkedList<>();
        backtrack(n, 0, row);

        return res;
    }

    /**
     * 尝试在一个 n 皇后问题中, 摆放第 index 行的皇后位置
     */
    private static void backtrack(int n, int index, LinkedList<Integer> row) {
        if (index == n) {
            res.add(generateBoard(n, row));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 尝试将第 index 行的皇后摆放在第 i 列
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                // 做选择
                row.addLast(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;

                // 进入下一层回溯树
                backtrack(n, index + 1, row);

                // 取消选择
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
        }
    }

    private static List<String> generateBoard(int n, LinkedList<Integer> row) {
        ArrayList<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] charArray = new char[n];
            Arrays.fill(charArray, '.');
            charArray[row.get(i)] = 'Q';
            board.add(new String(charArray));
        }
        return board;
    }

    private static void printBoard(List<String> board) {
        for (String row : board) System.out.println(row);
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> res = solveNQueens(n);
        for (List<String> board : res) printBoard(board);
    }
}
