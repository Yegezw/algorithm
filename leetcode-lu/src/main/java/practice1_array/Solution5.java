package practice1_array;

/**
 * <a href="https://leetcode.cn/problems/spiral-matrix-ii/">59 - 螺旋矩阵 II</a>
 */
@SuppressWarnings("all")
public class Solution5 {

    public int[][] generateMatrix1(int n) {
        int[][] arr = new int[n][n];
        int num = 1;
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;

        while (num <= n * n) {
            for (int i = left; i <= right; i++) arr[top][i] = num++;    // 向右填充
            top++;
            for (int i = top; i <= bottom; i++) arr[i][right] = num++;  // 向下填充
            right--;
            for (int i = right; i >= left; i--) arr[bottom][i] = num++; // 向左填充
            bottom--;
            for (int i = bottom; i >= top; i--) arr[i][left] = num++;   // 向上填充
            left++;
        }

        return arr;
    }
}
